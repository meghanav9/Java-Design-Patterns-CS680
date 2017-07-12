package edu.umb.cs.cs680;

public class StockEvent {
	private String ticker;
	private float quote;

	StockEvent(String ticker, float quote){
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
