package com.epinerez.testnexos.repositories;

import com.epinerez.testnexos.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface IProductDao extends JpaRepository<Product, Integer> {

    @Query("from Product p where p.nameProduct = :nameProduct")
    public Product findByNameProduct(String nameProduct);

    @Query("from Product p where p.date>=:date_start and p.date<=:date_end")
    public List<Product> findByDates(Date date_start, Date date_end);


}
