package com.luxoft.shop.service;

import com.luxoft.shop.entity.User;

import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class SecurityService {
    private UserService userService;


    private Map<String, User> userTokens = Collections.synchronizedMap(new HashMap<>());

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    public boolean isAuthenticated(String token){
        if ( userTokens.containsKey(token)) {
            return true;
        }
        return false;
    }


    public String login(User user) {
        if (checkUserCredential(user)) {
            return generateToken(user);
        }
        return null;
    }

    private String generateToken(User user) {
        String token = UUID.randomUUID().toString();
        User thisUser = userService.findUserByName(user.getName());
        userTokens.put(token, thisUser);
        return token;
    }

    private boolean checkUserCredential(User user) {
        boolean indicator = false;
        User dbUser = userService.findUserByName(user.getName());
        if (!Objects.equals(dbUser, null)) {
            String hashedPassword = getHashedPassword(dbUser.getSalt(), user.getPassword());
            indicator = Objects.equals(hashedPassword, dbUser.getPassword());
        }
        return indicator;
    }

    public User register(User user) {
        String salt = generateSalt(10);
        System.out.println("Salt = " + salt);
        user.setSalt(salt);

        if (!userService.isUserExist(user.getName())) {
            String hashedPassword = getHashedPassword(user.getSalt(), user.getPassword());
            user.setPassword(hashedPassword);
            userService.add(user);
        } else {
            throw new RuntimeException();
        }
        return user;
    }

    private String getHashedPassword(String salt, String password)  {
        String hashedPassword = DigestUtils.md5Hex(salt + password);
        return hashedPassword;
    }


    private String generateSalt(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        //System.out.println(generatedString);
        return generatedString;
    }

    public static String getToken(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public boolean removeToken(String token) {
        if (userTokens.containsKey(token)) {
            userTokens.remove(token);
            return true;
        }
        return false;
    }


}
