package edu.umb.cs.cs680;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

public class FileCacheMain {

	public static void main(String[] args) {
	
		LinkedHashMap<java.nio.file.Path,String> cache = new LinkedHashMap<java.nio.file.Path, String>(5);
		{
			cache.put(Paths.get("File1.txt"),"Content in File1");
		    cache.put(Paths.get("File2.txt"),"Content in File2");
		    cache.put(Paths.get("File3.txt"),"Content in File3");
		    cache.put(Paths.get("File4.txt"),"Content in File4");
		  // cache.put("File5.txt","Content in File5");
		}
		Path targetFile = Paths.get("File6.txt");
		
		FIFO fifo = new FIFO();
		fifo.Cache(cache);
		fifo.display(cache, targetFile);
	}

}