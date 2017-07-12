package edu.umb.cs.cs680;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;


public class FileCacheTest {
	private FIFO fifo ;
	private LinkedHashMap<java.nio.file.Path,String> cache;
	private Path targetFile;
	@Before
	public void Initialization(){
		cache = new LinkedHashMap<java.nio.file.Path, String>(5);
		{
			cache.put(Paths.get("File1.txt"),"Content in File1");
		    cache.put(Paths.get("File2.txt"),"Content in File2");
		    cache.put(Paths.get("File3.txt"),"Content in File3");
		    cache.put(Paths.get("File4.txt"),"Content in File4");
		    fifo = new FIFO();
		    fifo.Cache(cache);
		}
	}
	@Test
	public void CacheHashMaptest(){
	    assertThat(fifo.getCache(), is(cache));
	}

	@Test
	public void fetch_WhenFileFoundinCache_Test(){
		System.out.println("\n***Test case when file found***");
		targetFile = Paths.get("File2.txt");
		fifo.display(cache, targetFile);
		assertThat(fifo.fetch(targetFile), is("Content in File2"));	 
		System.out.println("\n");
	}
	@Test
	public void fetch_WhenFileNotFoundinCache_Test(){
			System.out.println("Original Content: ");
			for(Entry<Path, String> m:cache.entrySet()){  
			   System.out.println("File name: "+ "'"+m.getKey()+"'"+" and Content: "+"'"+m.getValue()+"'");  
			}  
			System.out.println("\n***Test case when cache is not full***");
			targetFile = Paths.get("File5.txt");
		    assertThat(fifo.fetch(targetFile), is("Content of File5"));

			System.out.println("\nOriginal Content: Before FIFO replacement happens  ");
			for(Entry<Path, String> m:cache.entrySet()){  
			   System.out.println("File name: "+ "'"+m.getKey()+"'"+" and Content: "+"'"+m.getValue()+"'");  
			}  
		    System.out.println("\n***Test case when FIFO replacement happens***");
		    assertThat(fifo.fetch(Paths.get("File6.txt")), is("Content of File6"));
		    System.out.println("\n");
	}	
}
