package edu.umb.cs.cs680;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Observable;
import java.util.HashMap;
public class StockEventObservable extends Observable{
	private HashMap<String,Float> m1= new HashMap<String, Float>();
	private ArrayList<StockEvObserver> observers= new ArrayList<StockEvObserver>();
	public StockEventObservable(){};
	public void addEventListener(EventListener el){
		observers.add((StockEvObserver) el);
	}
	
	public void Map(HashMap<String, Float> m1){
		this.m1 = m1;
	}
	public HashMap<String,Float> getMap(){
		
		return m1;
	}
	public void notifyObservers(StockEvent ev){
		for(StockEvObserver observer : this.observers){
			observer.updateStock(ev);
		}
	}

	public void changeQuote(String t, float q){
		setChanged();
		this.notifyObservers(new StockEvent(this, t, q));
	}
}
