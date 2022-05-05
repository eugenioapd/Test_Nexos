package com.epinerez.testnexos.service.imple;

import com.epinerez.testnexos.models.Charge;
import com.epinerez.testnexos.repositories.IChargeDao;
import com.epinerez.testnexos.service.IChergeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargeServiceImple implements IChergeService {

    @Autowired
    private IChargeDao chargeDao;


    @Override
    public Charge save(Charge charge) {
        return chargeDao.save(charge);
    }

    @Override
    public List<Charge> findAll() {
        return chargeDao.findAll();
    }


}
