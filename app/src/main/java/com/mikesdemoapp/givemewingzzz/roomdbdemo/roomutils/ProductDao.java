package com.mikesdemoapp.givemewingzzz.roomdbdemo.roomutils;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mikesdemoapp.givemewingzzz.roomdbdemo.model.Product;

import java.util.List;

/**
 * Project - RoomDbDemo
 * In Package - com.mikesdemoapp.givemewingzzz.roomdbdemo.roomutils
 * Created by GiveMeWingzzz on 11/18/2017.
 */

@Dao
public interface ProductDao {

    RoomDbQueryBuilder<RoomDBBuilder> roomDbQueryBuilder = new RoomDbQueryBuilder<>();

    String SELECT_ALL_PRODUCT = RoomDbQueryBuilder.newInstance().buildSelectAllQuery(Product.class);

    String SELECT_ALL_FROM_PRODUCT = "SELECT * FROM" + RoomDbUtils.Constants.SPACE + RoomDbUtils.Constants.DBTableConst.PRODUCT;
    String SELECT_ALL_FROM_PRODUCT_DETAILS = "SELECT * FROM" + RoomDbUtils.Constants.SPACE + RoomDbUtils.Constants.DBTableConst.PRODUCT_DETAILS;
    String FIND_FROM_PRODUCT_WHERE_NAME_VALUE_IS_EQUAL_NAME = "SELECT * FROM" + RoomDbUtils.Constants.SPACE + RoomDbUtils.Constants.DBTableConst.PRODUCT + RoomDbUtils.Constants.SPACE + "WHERE name LIKE :name LIMIT 1";

    @Query(SELECT_ALL_FROM_PRODUCT)
    List<Product> getAllProductList();
//
//    @Query(SELECT_ALL_FROM_PRODUCT)
//    List<Product> getAllProductList(T className);
//
//    @Query(SELECT_ALL_FROM_PRODUCT_DETAILS)
//    List<ProductDetails> getProductDetails();
//
//    @Query(FIND_FROM_PRODUCT_WHERE_NAME_VALUE_IS_EQUAL_NAME)
//    Product findByName(String name);

    @Insert
    void insertAll(List<Product> products);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);
}