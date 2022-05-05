package com.epinerez.testnexos.service.imple;

import com.epinerez.testnexos.models.Product;
import com.epinerez.testnexos.repositories.IProductDao;
import com.epinerez.testnexos.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImple implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public Product save(Product product) {
        return productDao.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product findById(Integer productId) {
        if(productDao.existsById(productId)){
            return productDao.findById(productId).get();
        }
        return null;
    }

    @Override
    public Product findByNameProduct(String nameProduct) {
        return productDao.findByNameProduct(nameProduct);
    }

    @Override
    public void delete(Integer productId) {
       productDao.deleteById(productId);
    }

    @Override
    public List<Product> findByDates(Date date_start, Date date_end) {
        return productDao.findByDates(date_start,date_end);
    }
}
