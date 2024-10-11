package com.example.SpringAppV3.repository;

import com.example.SpringAppV3.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {

}