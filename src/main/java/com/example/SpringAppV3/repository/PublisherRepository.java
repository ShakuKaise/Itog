package com.example.SpringAppV3.repository;

import com.example.SpringAppV3.model.BookModel;
import com.example.SpringAppV3.model.PublisherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<PublisherModel, Long> {

}