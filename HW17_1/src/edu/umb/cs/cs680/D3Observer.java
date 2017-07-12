package edu.umb.cs.cs680;
public class D3Observer implements StockEvObserver{
	StockEvent st;
	@Override
	public void updateStock(StockEvent st) {
		this.st = st;
		System.out.println("3D represenation: ");
		System.out.println(st.getTicker());
		System.out.println(st.getQuote());
	}
   

}