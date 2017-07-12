package edu.umb.cs.cs680;
import java.util.Observable;
import java.util.Observer;

public class TableObserver implements Observer{

		 private StockQuoteObservable ov = null;
		 public TableObserver(StockQuoteObservable ov)
		 {
		      this.ov = ov;
		 }
		 public StockQuoteObservable getTablechart(){
				return ov;
				 
		}
		 @Override
		 public void update(Observable o, Object  arg) {		 
			if(o == ov){
				System.out.println("Table chart represenation: ");
				StockEvent mar = (StockEvent) arg;
				System.out.println(mar.getTicker());
				System.out.println(mar.getQuote());
		}
		}
		
}


