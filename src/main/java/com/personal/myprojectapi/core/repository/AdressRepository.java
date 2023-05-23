package com.personal.myprojectapi.core.repository;

import com.personal.myprojectapi.core.entity.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository <Adress, String> {
}
