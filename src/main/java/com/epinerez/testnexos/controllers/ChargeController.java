package com.epinerez.testnexos.controllers;


import com.epinerez.testnexos.models.Charge;
import com.epinerez.testnexos.service.imple.ChargeServiceImple;
import com.epinerez.testnexos.service.imple.UserServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/api/charge")
public class ChargeController {

    @Autowired
    private ChargeServiceImple chargeServiceImple;
    @Autowired
    private UserServiceImple userServiceImple;



    @PostMapping
    public ResponseEntity<Charge>save(@RequestBody Charge charge){

        try{
            return new ResponseEntity<>(chargeServiceImple.save(charge), HttpStatus.CREATED);
        }catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Charge>> findAll(){
        try{
            return new ResponseEntity(chargeServiceImple.findAll(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
