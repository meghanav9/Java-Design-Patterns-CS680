package edu.umb.cs.cs680;
import java.util.Observable;
import java.util.Observer;


public class PiechartObserver implements Observer{
	 private StockQuoteObservable ov1 = null;
	 //public StockEvent mar = null;
	 public PiechartObserver(StockQuoteObservable ov1)
	 {
	      this.ov1 = ov1;
	 }
	 public StockQuoteObservable getPiechart(){
		 return ov1;
		 
	 }
	 @Override
	 public void update(Observable o, Object  arg) {		 
		if(o == ov1){
			System.out.println("Pie chart represenation: ");
			StockEvent mar = (StockEvent) arg;
			System.out.println(mar.getTicker());
			System.out.println(mar.getQuote());
		}

	}
   

}
