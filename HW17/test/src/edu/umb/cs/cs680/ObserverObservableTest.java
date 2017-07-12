package edu.umb.cs.cs680;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;

import org.junit.Test;

public class ObserverObservableTest {

	@Test
	public void StockquoteObservableTest(){
		HashMap<String,Float> m1 = new HashMap<String,Float>();
		m1.put("xyz",32F);
		m1.put("xz",32.2F);
		StockQuoteObservable ov = new StockQuoteObservable();
		ov.Map(m1);
		assertThat(ov.getMap(), is(m1));
	}
	
	@Test
	public void StockEventMethodsTest(){
		HashMap<String,Float> m1 = new HashMap<String,Float>();
		m1.put("xyz",32F);
		m1.put("xz",32.2F);
		StockQuoteObservable ov = new StockQuoteObservable();
		float mar = (int) (Math.random() + 1);
		StockEvent se = new StockEvent("xyz", mar);
		ov.Map(m1);
		assertThat(se.getTicker(), is("xyz"));
		assertThat(se.getQuote(), is(mar ));
	}
	@Test
	public void AllConstructorsTest(){
		StockQuoteObservable ov = new StockQuoteObservable();
		PiechartObserver po = new PiechartObserver(ov);
		TableObserver to1 = new TableObserver(ov);
		D3Observer to2 = new D3Observer(ov);
		//po.update(ov, 123);
		assertThat(po.getPiechart(), is(ov));
		assertThat(to1.getTablechart(), is(ov));
		assertThat(to2.get3Dchart(), is(ov));
	}	

	@Test
	public void changeQuoteTest(){
		HashMap<String,Float> m1 = new HashMap<String,Float>();
		m1.put("xyz",32F);
		m1.put("xz",32.2F);
		StockQuoteObservable ov = new StockQuoteObservable();
		int mar1 = (int) (Math.random() + 1);
		ov.Map(m1);
		ov.changeQuote("xyz", mar1 + m1.get("xyz"));
		StockEvent se = new StockEvent("xyz", mar1 + m1.get("xyz"));
		StockEvent mar =(StockEvent) se;
		assertThat(mar.getTicker(), is("xyz"));
		assertThat(mar.getQuote(), is(mar1 + m1.get("xyz")));
	}
	@Test
	public void UpdateAllTest1(){
			StockEvent ne =  new StockEvent("xyz", 12);
			StockEvent se =  new StockEvent("xyz", 12);
			PiechartObserver to = new PiechartObserver(null);
			to.update(null, (StockEvent) se);
			StockEvent mar = (StockEvent) se;
			assertThat(mar.getTicker(), is(ne.getTicker()));
			assertThat(mar.getQuote(), is(ne.getQuote()));
		
	}
	@Test
	public void UpdateAllTest2(){
		StockEvent ne =  new StockEvent("xyz", 12);
		StockEvent se =  new StockEvent("xyz", 12);
		TableObserver to1 = new TableObserver(null);
		to1.update(null, (StockEvent) se);
		StockEvent mar = (StockEvent) se;
		assertThat(mar.getTicker(), is(ne.getTicker()));
		assertThat(mar.getQuote(), is(ne.getQuote()));
	
	}
	@Test
	public void UpdateAllTest3(){
		StockEvent ne =  new StockEvent("xyz", 12);
		StockEvent se =  new StockEvent("xyz", 12);
		D3Observer to2 = new D3Observer(null);
		to2.update(null, (StockEvent) se);
		StockEvent mar = (StockEvent) se;
		assertThat(mar.getTicker(), is(ne.getTicker()));
		assertThat(mar.getQuote(), is(ne.getQuote()));
	
	}

}
		