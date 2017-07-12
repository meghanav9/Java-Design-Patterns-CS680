package edu.umb.cs.cs680;
import java.util.Observable;
import java.util.Observer;

public class D3Observer implements Observer{
	 private StockQuoteObservable ov = null;
	 //public StockEvent mar = null;
	 public D3Observer(StockQuoteObservable ov)
	 {
	      this.ov = ov;
	 }
	 public StockQuoteObservable get3Dchart(){
		return ov;
		 
	 }
	 @Override
	 public void update(Observable o, Object  arg) {		 
		if(o == ov){
			System.out.println("3D Observer represenation: ");
			StockEvent mar = (StockEvent) arg;
			System.out.println(mar.getTicker());
			System.out.println(mar.getQuote());
		}	
	}
}
