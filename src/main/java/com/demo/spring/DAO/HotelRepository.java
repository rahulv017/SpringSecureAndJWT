package com.demo.spring.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.spring.Model.Hotel;

@Repository
public interface HotelRepository extends MongoRepository<Hotel,Integer>{

}
