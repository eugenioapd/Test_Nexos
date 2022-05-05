package com.epinerez.testnexos.service;


import com.epinerez.testnexos.models.Product;

import java.util.Date;
import java.util.List;

public interface IProductService {

    public Product save(Product product);

    public List<Product> findAll();

    public Product findById(Integer productId);

    public Product findByNameProduct(String nameProduct);

    public void delete (Integer productId);

    public List<Product> findByDates(Date date_start, Date date_end);

}
