package edu.umb.cs.cs680;
import java.nio.file.Path;
import java.util.LinkedHashMap;

public abstract class FileCache {
	private LinkedHashMap<Path, String> cache;
	
	 public final void display(LinkedHashMap<Path, String> cache2, Path targetFile) {
	     this.fetch(targetFile);
	 }
	public void Cache(LinkedHashMap<Path, String> cache2){
				this.cache = cache2;
	}	 
	public LinkedHashMap<Path, String> getCache(){
			return cache;
	}
	
	 public String fetch(Path targetFile) { 
		 if(this.cache.containsKey(targetFile)){
				System.out.println("TargetFile '"+targetFile+ "' found in the cache and its content is "
						+ " ' " + cache.get(targetFile) + " ' ");
			}
			else{
				if(cache.size() <5){
					this.cache.put(targetFile, "Content of File5");
					System.out.println("cache is not full,So Inserted the new file '"+targetFile+"' and the file content '"+
							cache.get(targetFile)+"'");
					
				}
				else{
					replace(this.cache, targetFile);
				}
			}
			return cache.get(targetFile);
		}
	
	 protected abstract void replace(LinkedHashMap<Path, String> cache2, Path targetFile);
}
