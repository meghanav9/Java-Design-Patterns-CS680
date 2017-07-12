package edu.umb.cs.cs680;

public class PiechartObserver implements StockEvObserver {
	StockEvent st;
	@Override
	public void updateStock(StockEvent st) {
			this.st = st;
			System.out.println("Pie chart represenation: ");
			System.out.println(st.getTicker());
			System.out.println(st.getQuote());
	}
}
