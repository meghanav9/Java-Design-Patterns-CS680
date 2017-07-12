package edu.umb.cs.cs680;

import java.util.EventObject;

public class StockEvent extends EventObject {
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
	private String ticker;
	private float quote;

	StockEvent(Object source,String ticker, float quote){
		super(source);
		this.ticker = ticker;
		this.quote = quote;

	}
	public  String getTicker(){
		return ticker;
	}
	public float getQuote(){
		return quote;
	}
}
