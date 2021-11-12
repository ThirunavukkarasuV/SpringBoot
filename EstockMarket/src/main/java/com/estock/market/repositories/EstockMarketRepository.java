package com.estock.market.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.estock.market.models.StockTo;

@Repository
public interface EstockMarketRepository extends MongoRepository<StockTo, String> {

}