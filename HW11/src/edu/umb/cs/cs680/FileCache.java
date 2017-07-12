package edu.umb.cs.cs680;
import java.nio.file.Path;
import java.util.LinkedHashMap;
public class FileCache extends HTTPD{
	private static FileCache instance= new FileCache();
	private LinkedHashMap<Path, String> cache;
	CacheReplacementPolicy replacementPolicy;

	private FileCache(){};
	public static FileCache getInstance(){
		return instance;
	}
	public void Cache(LinkedHashMap<Path, String> cache2){
		this.cache = cache2;
	}

	public LinkedHashMap<Path, String> getCache(){
		return cache;
	}
	public String fetch(Path targetFile){
		if(this.cache.containsKey(targetFile)){
			System.out.println("TargetFile '"+targetFile+ "' found in the cache and its content is "
					+ " ' " + cache.get(targetFile) + " ' ");
			return cache.get(targetFile);
		}
		else{
			if(cache.size() <5){
				cache.put(targetFile, "Content in File5");
				System.out.println("cache is not full,So Inserted the new file '"+targetFile+"' and the file content '"+
						cache.get(targetFile)+"'");
			}	
			else{
				replace(targetFile);
			}
		}
		return cache.get(targetFile);
	}
	public static CacheReplacementPolicy setCacheReplacementPolicy(){
		return FIFO.getInstance();
	}
	public void replace(Path targetFile){
		CacheReplacementPolicy replacementPolicy = NullReplacement.getInstance();
		replacementPolicy.replace(this.cache, targetFile);
		
	}
}


