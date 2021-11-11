package com.estock.market.models;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stockTo")
@JsonIgnoreProperties(value = { "createdAt" }, allowGetters = true)
public class StockTo {
	// a. Company Code b. Company Name c. Company CEO d. Company Turnover e.
	// Company Website f. Stock Exchange it is enlisted in (BSE, NSE etc)
	@Id
	private String companyCode;
	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true)
	private String companyName;

	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true)
	private String companyTurnover;
	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true)
	private String companyCEO;
	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true)
	private String companyWebsite;
	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true)
	private String stockPrice;

	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true)
	private String stockExchange;

	private Boolean completed = false;

	private Date createdAt = new Date();

	private Date startDate;

	private Date endDate;

	public StockTo() {
		super();
	}

	public StockTo(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(String stockPrice) {
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

	public String getCompanyTurnover() {
		return companyTurnover;
	}

	public void setCompanyTurnover(String companyTurnover) {
		this.companyTurnover = companyTurnover;
	}

	public String getCompanyCEO() {
		return companyCEO;
	}

	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

	@Override
	public String toString() {
		return String.format("Todo[companyCode=%s, companyName='%s', completed='%s']", companyCode, companyName,
				completed);
	}
}