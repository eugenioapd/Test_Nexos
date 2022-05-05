package com.epinerez.testnexos.service;

import com.epinerez.testnexos.models.Product;
import com.epinerez.testnexos.models.User;

import java.util.List;

public interface IUserService {


    public List<User> findAll();

    public User findById(Integer id);


}
