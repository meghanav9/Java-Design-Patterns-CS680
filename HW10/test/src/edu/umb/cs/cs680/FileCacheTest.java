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
	FileCache tmp;
	LinkedHashMap<java.nio.file.Path, String> cache;
	@Before
	public void Files(){
		cache = new LinkedHashMap<java.nio.file.Path, String>(5);
		{
			cache.put(Paths.get("File1.txt"),"Content in File1");
		    cache.put(Paths.get("File2.txt"),"Content in File2");
		    cache.put(Paths.get("File3.txt"),"Content in File3");
		    cache.put(Paths.get("File4.txt"),"Content in File4");
		    tmp = FileCache.getInstance(); 
		    tmp.Cache(cache);
		}
	}
	@Test
	public void FileCacheInstanceTest() {
		assertThat(FileCache.getInstance(),is(tmp));
	}
	@Test
	public void CacheHashMaptest(){
		    assertThat(tmp.getCache(), is(cache));
	}
	@Test
	public void fetch_WhenFileFoundinCache_Test(){
		System.out.println("\n***Test case when file found***");
		    assertThat(tmp.fetch(Paths.get("File2.txt")), is("Content in File2"));	    
		    System.out.println("\n");
	}	
	@Test
	public void fetch_WhenFileNotFoundotincache_Test(){
		System.out.println("Original Content: ");
		for(Entry<Path, String> m:cache.entrySet()){  
		   System.out.println("File name: "+ "'"+m.getKey()+"'"+" and Content: "+"'"+m.getValue()+"'");  
		}  
		System.out.println("\n***Test case when cache is not full***");
	   	assertThat(tmp.fetch(Paths.get("File5.txt")), is("Content in File5"));
		
		System.out.println("\nOriginal Content: Before FIFO replacement happens  ");
		for(Entry<Path, String> m:cache.entrySet()){  
		   System.out.println("File name: "+ "'"+m.getKey()+"'"+" and Content: "+"'"+m.getValue()+"'");  
		}  
	    System.out.println("\n***Test case when FIFO replacement happens***");
	   	assertThat(tmp.fetch(Paths.get("File6.txt")), is("Content in File6"));
	   	System.out.println("\n");
	}	

}
