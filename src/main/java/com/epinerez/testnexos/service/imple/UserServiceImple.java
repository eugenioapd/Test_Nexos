package com.epinerez.testnexos.service.imple;

import com.epinerez.testnexos.models.User;
import com.epinerez.testnexos.repositories.IUserDao;
import com.epinerez.testnexos.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImple implements IUserService {

    @Autowired
    private IUserDao iUserDao;

    @Override
    public List<User> findAll() {
        return iUserDao.findAll();
    }

    @Override
    public User findById(Integer id) {
        if(iUserDao.existsById(id)){
            return iUserDao.findById(id).get();
        }
        return null;
    }
}
