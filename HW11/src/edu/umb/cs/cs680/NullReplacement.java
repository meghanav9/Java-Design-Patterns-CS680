package edu.umb.cs.cs680;
import java.nio.file.Path;
import java.util.LinkedHashMap;


public class NullReplacement implements CacheReplacementPolicy{
	private static final NullReplacement instance = new NullReplacement();

	private NullReplacement() { }

	public static NullReplacement getInstance(){
		return instance;
	}
	@Override
	public void replace(LinkedHashMap<Path, String> cache, Path File){
		
		System.out.println("Null replacement performing dynamic FIFO implementation. ");
		CacheReplacementPolicy replacementPolicy = FIFO.getInstance();
		replacementPolicy.replace(cache, File);
	}

}
