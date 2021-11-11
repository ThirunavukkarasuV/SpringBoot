package com.estock.market.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.estock.market.models.StockPriceTo;

@Repository
public interface EstockMarketPriceRepository extends MongoRepository<StockPriceTo, String> {

	@Query("{companyCode : ?0 }")   
	//, startDate :{$gte: ?1, $lte: ?2 }
	public List<StockPriceTo> getObjectByDate( String companyCode,Date from, Date to);

	@Query("{'startDate' : { $gte: ?0, $lte: ?1 } }")                 
	public List<StockPriceTo> getObjectByDate1(Date from, Date to);
	
	@Query(value="{companyCode : ?0}", delete = true)
	public void deleteById(String companyCode);
	
	@Aggregation(pipeline = { "{$group: { _companyCode: '', total: {$max: $stockPrice }}}" })
	public double max();

	@Aggregation(pipeline = { "{$group: { _companyCode: '', total: {$min: $stockPrice }}}" })
	public double min();
	

}