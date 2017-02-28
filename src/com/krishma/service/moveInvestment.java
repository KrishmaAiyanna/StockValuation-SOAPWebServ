package com.krishma.service;

import net.webservicex.CurrencyConvertor;
import net.webservicex.CurrencyConvertorSoap;
import net.webservicex.StockQuote;
import net.webservicex.StockQuoteSoap;

@javax.jws.WebService
public class moveInvestment {

	@javax.jws.WebMethod
	public double investmentValue(String symbol)
	{
		double toCurrency = 0;
		StockQuote stockQuote = new StockQuote();
		StockQuoteSoap stockQuoteSoap = stockQuote.getStockQuoteSoap();
		   
		CurrencyConvertor currencyConvertor = new CurrencyConvertor();
		CurrencyConvertorSoap currencyConvertorSoap = currencyConvertor.getCurrencyConvertorSoap();
		
		return toCurrency;    
	}
}
