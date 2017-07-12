package edu.umb.cs.cs680;

public interface StockEvObserver extends java.util.EventListener{
	void updateStock(StockEvent st);

}
