package com.epinerez.testnexos.repositories;

import com.epinerez.testnexos.models.Charge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IChargeDao extends JpaRepository<Charge, Integer> {
}
