package edu.umb.cs.cs680;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;

import org.junit.Test;

public class ObserverObservableTest {
	@Test
	public void changeQuoteTest(){
      	HashMap<String,Float> m1 = new HashMap<String,Float>();
		m1.put("One",32F);
		m1.put("Two",32.2F);	
		StockEventObservable stockobservable = new StockEventObservable();
		PiechartObserver pieobserver = new PiechartObserver();
		D3Observer d3observer = new D3Observer();
		TableObserver tableobserver = new TableObserver();
		stockobservable.addEventListener(pieobserver);
		stockobservable.addEventListener(d3observer);
		stockobservable.addEventListener(tableobserver);
		float rand = (float) (Math.random());
		stockobservable.Map(m1);
		stockobservable.changeQuote("One", rand + m1.get("One"));
		StockEvent se = new StockEvent(pieobserver,"One", rand + m1.get("One"));
		StockEvent mar =(StockEvent) se;
		assertThat(mar.getTicker(), is("One"));
		assertThat(mar.getQuote(), is(rand + m1.get("One")));
	}
	@Test
	public void StockquoteObservableTest(){
		HashMap<String,Float> m1 = new HashMap<String,Float>();
		m1.put("xyz",32F);
		m1.put("xz",32.2F);
		StockEventObservable ov = new StockEventObservable();
		ov.Map(m1);
		assertThat(ov.getMap(), is(m1));
	}
	
	@Test
	public void StockEventMethodsTest(){
		StockEventObservable stockeventob = new StockEventObservable();
		HashMap<String,Float> m1 = new HashMap<String,Float>();
		m1.put("One",32F);
		stockeventob.Map(m1);
		PiechartObserver po = new PiechartObserver();
		StockEvent se = new StockEvent(po,"One",32F);
		assertThat(se.getTicker(),is("One"));
	}
//	@Test
//	public void UpdateStockPiechartTest(){
//			
//			PiechartObserver to = new PiechartObserver();
//			StockEvent ne =  new StockEvent(to,"One",32F);
//			to.updateStock(ne);
//			StockEvent mar = (StockEvent) ne;
//			assertThat(mar.getTicker(), is(ne.getTicker()));
//			assertThat(mar.getQuote(), is(ne.getQuote()));
//		
//	}
//	@Test
//	public void UpdateStock3DTest(){
//			
//			D3Observer to = new D3Observer();
//			StockEvent ne =  new StockEvent(to,"One",32F);
//			to.updateStock(ne);
//			StockEvent mar = (StockEvent) ne;
//			assertThat(mar.getTicker(), is(ne.getTicker()));
//			assertThat(mar.getQuote(), is(ne.getQuote()));
//		
//	}
//	@Test
//	public void UpdateStockTableTest(){
//			TableObserver to = new TableObserver();
//			StockEvent ne =  new StockEvent(to,"One",32F);
//			to.updateStock(ne);
//			StockEvent mar = (StockEvent) ne;
//			assertThat(mar.getTicker(), is(ne.getTicker()));
//			assertThat(mar.getQuote(), is(ne.getQuote()));
//		
//	}
}
		