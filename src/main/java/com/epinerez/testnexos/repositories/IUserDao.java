package com.epinerez.testnexos.repositories;

import com.epinerez.testnexos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<User, Integer> {
}
