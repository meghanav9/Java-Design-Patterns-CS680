package edu.umb.cs.cs680;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class FIFO extends FileCache {

	@Override
	protected void replace(LinkedHashMap<Path, String> cache, Path File) {
		System.out.println("Performaning FIFO replacement policy on "+File);
		 Path val = cache.keySet().iterator().next();
		 cache.remove(val);
		 cache.put(File, "Content of File6");
		 System.out.println("After FIFO replacement policy: Content in HashMap ");
		 for(Entry<Path, String> m:cache.entrySet()){  
			 System.out.println("File name: "+ "'"+m.getKey()+"'"+" and Content: "+"'"+m.getValue()+"'");  
		}  
	}
}

