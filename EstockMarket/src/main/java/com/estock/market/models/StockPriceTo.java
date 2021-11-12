package com.estock.market.models;

import java.sql.Time;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection = "stockPriceTo")
@JsonIgnoreProperties(value = { "createdAt" }, allowGetters = true)
public class StockPriceTo {

	// a. Company Code b. Company Name c. Company CEO d. Company Turnover e.
	// Company Website f. Stock Exchange it is enlisted in (BSE, NSE etc)
//	@Id
//	@
//	private long Id;
	
	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true)
	private String companyCode;
	
	@NotBlank
	@Indexed(unique = true)
	private double  stockPrice;


	private Date createdAt = new Date();

	private Date startDate;
	
	private String time;

	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}



	private Date endDate;

	public StockPriceTo() {
		super();
	}


	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}




	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public double  getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(double  stockPrice) {
		this.stockPrice = stockPrice;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	


}
