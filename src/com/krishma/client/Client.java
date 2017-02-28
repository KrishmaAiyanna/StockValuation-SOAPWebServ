package com.krishma.client;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import net.webservicex.Currency;
import net.webservicex.CurrencyConvertor;
import net.webservicex.CurrencyConvertorSoap;
import net.webservicex.StockQuote;
import net.webservicex.StockQuoteSoap;

public class Client {

	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("Please enter the stock name inorder to proceed");
		}

		String symbol[];
		symbol = new String[] { "AAPL", "BA", "HD", "KO", "IBM" };

		/*
		 * System.out.println( "Enter the stock name: "); Scanner sc = new
		 * Scanner(System.in); String symbol = sc.next(); System.out.println(
		 * "Enter the currency to convert: "); String toCurrency = sc.next();
		 * System.out.println( "Stock " +symbol +" to be converted in " +
		 * toCurrency ); sc.close();
		 */
		System.out.println("Enter the currency to convert: ");
		Scanner sc = new Scanner(System.in);
		String toCurrency = sc.next();

		StockQuote stockQuote = new StockQuote();
		StockQuoteSoap stockQuoteSoap = stockQuote.getStockQuoteSoap();

		CurrencyConvertor currencyConvertor = new CurrencyConvertor();
		CurrencyConvertorSoap currencyConvertorSoap = currencyConvertor.getCurrencyConvertorSoap();

		int k;
		for (k = 0; k < 5; k++) {
			// String pattern =
			// "(.<Last>)(\\d+)(<\Last>.)";
		}
		
		String stock0 = stockQuoteSoap.getQuote(symbol[0]);
		String stock1 = stockQuoteSoap.getQuote(symbol[1]);
		String stock2 = stockQuoteSoap.getQuote(symbol[2]);
		String stock3 = stockQuoteSoap.getQuote(symbol[3]);
		String stock4 = stockQuoteSoap.getQuote(symbol[4]);

		System.out.println("Stock quote of " + symbol[0] + " is: " + stock0 );
		System.out.println("Stock quote of " + symbol[1] + " is: " + stockQuoteSoap.getQuote(symbol[1]));
		System.out.println("Stock quote of " + symbol[2] + " is: " + stockQuoteSoap.getQuote(symbol[2]));
		System.out.println("Stock quote of " + symbol[3] + " is: " + stockQuoteSoap.getQuote(symbol[3]));
		System.out.println("Stock quote of " + symbol[4] + " is: " + stockQuoteSoap.getQuote(symbol[4]));
		System.out.println("The conversion from USD to " + toCurrency + " is: "
				+ currencyConvertorSoap.conversionRate(Currency.fromValue(toCurrency), Currency.USD));
		try {
			//String value = getValueFromNode(stockQuoteSoap.getQuote(symbol[0]), "PreviousClose");
			System.out.println(getValueFromNode(stock0, "Last"));
			System.out.println(getValueFromNode(stock0, "PreviousClose"));
			
			Double conversionRate = currencyConvertorSoap.conversionRate(Currency.fromValue(toCurrency), Currency.USD);
			Double lastValue = Double.parseDouble(getValueFromNode(stock0, "Last"));
			Double lastValueAfterConversion = ((1/conversionRate)*lastValue);
			
			Double previousValue = Double.parseDouble(getValueFromNode(stock0, "PreviousClose"));
			Double previousValueAfterConversion = ((1/conversionRate)*previousValue);
			
			System.out.println("lastValue "+lastValue);
			System.out.println("lastValueAfterConversion "+lastValueAfterConversion);
			
			System.out.println("previousValue "+previousValue);
			System.out.println("previousValueAfterConversion "+previousValueAfterConversion);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getValueFromNode(String xmlData, String node)
			throws ParserConfigurationException, SAXException, IOException {
		String value = null;
		if (node != null && !node.isEmpty()) {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			InputStream inputStream = new ByteArrayInputStream(xmlData.getBytes("UTF-8"));
			Document doc = documentBuilder.parse(inputStream);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("Stock");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					value = eElement.getElementsByTagName(node).item(0).getTextContent();
				}
			}
		}
		return value;
	}
}
