package edu.umb.cs.cs680;
import java.util.*;

public class Mkdir implements Command{
	private FileSystem fs;
	private String new_dir;

	public Mkdir(FileSystem fs, String new_dir){
		this.fs = fs;
		this.new_dir = new_dir;
	}
	public void execute(){
		Date date = new Date();
		Directory d = new Directory(fs.getCurrent(), this.new_dir, "meg", date);
		fs.getCurrent().appendChild(d);
	}
	public String toString()
	{
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("mkdir "+this.new_dir+"\n");
		return stringbuffer.toString();
	}
}