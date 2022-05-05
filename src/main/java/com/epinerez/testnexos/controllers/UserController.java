package com.epinerez.testnexos.controllers;

import com.epinerez.testnexos.models.Product;
import com.epinerez.testnexos.models.User;
import com.epinerez.testnexos.service.imple.UserServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user" )
public class UserController {

    @Autowired
    private UserServiceImple userServiceImple;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        try{
            return new ResponseEntity(userServiceImple.findAll(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
