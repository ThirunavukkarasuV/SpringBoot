package com.estock.market.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estock.market.models.StockPriceTo;
import com.estock.market.models.StockTo;
import com.estock.market.repositories.EstockMarketPriceRepository;
import com.estock.market.repositories.EstockMarketRepository;

@RestController
@RequestMapping("/api/v/1.0/market/stock")
@CrossOrigin("*")
public class EstockMarketController {

    @Autowired
    EstockMarketRepository stockMarketRepository;
    
    @Autowired
    EstockMarketPriceRepository stockMarketPriceRepository;

    @GetMapping("/company/getall")
    public List<StockTo> getAllStockMarkets() {
        Sort sortByCreatedAtDesc = Sort.by(Sort.Direction.DESC, "createdAt");
        return stockMarketRepository.findAll(sortByCreatedAtDesc);
    }

    @PostMapping("/company/register")
    public StockTo createStockMarket(@Valid @RequestBody StockTo todo) throws Exception {
        todo.setCompleted(false);
        Exception e = null;
        Optional<StockTo> s= stockMarketRepository.findById(todo.getCompanyCode());
        System.out.println(!s.isPresent());
        Integer turnover=Integer.parseInt(todo.getCompanyTurnover());
        
        if(!s.isPresent()&&turnover>=100000000 ){
        return stockMarketRepository.save(todo);
        }else{
        	throw e;
        }
    }

    @GetMapping(value="/company/info/{companycode}")
    public ResponseEntity<StockTo> getStockMarketByCompanycode(@PathVariable("companycode") String companycode) {
        return stockMarketRepository.findById(companycode)
                .map(todo -> ResponseEntity.ok().body(todo))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/company/info/{companycode}")
    public ResponseEntity<StockTo> updateStockMarket(@PathVariable("companycode") String companycode,
                                           @Valid @RequestBody StockTo stockTo) {
        return stockMarketRepository.findById(companycode)
                .map(todoData -> {
                    todoData.setCompanyName(stockTo.getCompanyName());
                    todoData.setCompleted(stockTo.getCompleted());
                    StockTo updatedTodo = stockMarketRepository.save(todoData);
                    return ResponseEntity.ok().body(updatedTodo);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value="/company/delete/{companycode}")
    public ResponseEntity<?> deleteStockMarket(@PathVariable("companycode") String companycode) {
        return stockMarketRepository.findById(companycode)
                .map(todo -> {
                	stockMarketPriceRepository.deleteById(companycode);
                	stockMarketRepository.deleteById(companycode);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    
    
    @GetMapping(value="/company/info/{companycode}/{startdate}/{enddate}")
	public List<StockPriceTo> getStockMarketByCompanycodePrice(
			@PathVariable(required = false, name = "companycode") String companycode,
			@PathVariable("startdate") String startdate, @PathVariable("enddate") String enddate) throws ParseException {
		if (companycode.equals("All")) {
			Sort sortByCreatedAtDesc = Sort.by(Sort.Direction.DESC, "createdAt");
			List<StockPriceTo> rs=stockMarketPriceRepository.findAll(sortByCreatedAtDesc);
			return stockMarketPriceRepository.findAll(sortByCreatedAtDesc);
		} else {

			// Setting the pattern
			SimpleDateFormat sm = new SimpleDateFormat("mm-dd-yyyy");
	        Date d = sm.parse(startdate);
	        Date d1 = sm.parse(enddate);
			String strDate = sm.format(d);
			String enDate = sm.format(d1);
			Date sdt = sm.parse(strDate);
			Date edt = sm.parse(enDate);
			return stockMarketPriceRepository.getObjectByDate(companycode,sdt,edt);
		}
	}
    
    
    @PostMapping("/company/register/companycode")
    public StockPriceTo createStockMarketPrice(@Valid @RequestBody StockPriceTo todos) throws Exception {
        Exception e = null;
        Optional<StockTo> s= stockMarketRepository.findById(todos.getCompanyCode());
        if(s.isPresent()){
//        	String sdate=todos.getStartDate();
//             DateFormat date = new SimpleDateFormat("mm/dd/yyyy");
//             DateFormat time = new SimpleDateFormat("hh:mm:ss a");
//           
//
//             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//
//             LocalDateTime formatDateTime = LocalDateTime.parse(sdate, formatter);
//
//             System.out.println("Before : " + sdate);
//
//             System.out.println("After : " + formatDateTime);
//
//             System.out.println("After : " + formatDateTime.format(formatter));
//             String s3=date.format(sdate);
//             todos.setStartDate(s3);
//             String s2=time.format(sdate);
//             todos.setTime(s2);
        	
        return stockMarketPriceRepository.save(todos);
        }else{
        	throw e;
        }
    }
    
    
}