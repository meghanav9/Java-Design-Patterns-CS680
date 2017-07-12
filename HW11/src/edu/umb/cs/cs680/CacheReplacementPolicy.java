package edu.umb.cs.cs680;
import java.nio.file.Path;
import java.util.LinkedHashMap;


public interface CacheReplacementPolicy{

	void replace(LinkedHashMap<Path, String> cache, Path targetFile);

}

