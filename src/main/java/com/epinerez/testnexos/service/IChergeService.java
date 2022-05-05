package com.epinerez.testnexos.service;

import com.epinerez.testnexos.models.Charge;

import java.util.List;

public interface IChergeService {

    public Charge save(Charge charge);

    public List<Charge> findAll();


}
