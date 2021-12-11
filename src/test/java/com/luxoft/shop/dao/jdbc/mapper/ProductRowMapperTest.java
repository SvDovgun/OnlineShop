package com.luxoft.shop.dao.jdbc.mapper;

import com.luxoft.shop.entity.Product;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductRowMapperTest {

    @Test
    public void testMapRow() throws SQLException {
        // preparation
        ProductRowMapper productRowMapper = new ProductRowMapper();
        Timestamp timestamp = Timestamp.valueOf("2021-01-05 21:11:33");
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getInt("id")).thenReturn(1);
        when(resultSetMock.getString("name")).thenReturn("RICE");
        when(resultSetMock.getDouble("price")).thenReturn(100.00);
        when(resultSetMock.getString("notes")).thenReturn("notes for ricess");
        when(resultSetMock.getTimestamp("creationdate")).thenReturn(timestamp);

        // when
        Product actual = productRowMapper.mapRow(resultSetMock);

        //then
        assertEquals(1, actual.getId());
        assertEquals("RICE", actual.getName());
        assertEquals(100.00, actual.getPrice());
        assertEquals("notes for ricess", actual.getNotes());
        assertEquals(timestamp, actual.getCreationDate());


    }



}