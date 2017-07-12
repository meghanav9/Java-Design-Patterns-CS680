package edu.umb.cs.cs680;
import java.util.Observable;
import java.util.HashMap;
public class StockQuoteObservable extends Observable{

	private HashMap<String,Float> m1;
	public void Map(HashMap<String, Float> m1){
		this.m1 = m1;
	}
	public HashMap<String,Float> getMap(){
		return m1;
	}
	public void changeQuote(String t, float q){
		m1.put(t, q);
		setChanged();
		notifyObservers(new StockEvent(t,q));

	}
}
