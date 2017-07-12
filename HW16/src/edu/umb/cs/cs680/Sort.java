package edu.umb.cs.cs680;
import java.util.Comparator;
import java.util.Collections;

public class Sort implements Command{
	
	private Comparator<FSElement> comparator;
	private FileSystem fs;
	
	public Sort(FileSystem fs,Comparator<FSElement> comparator) {
		this.fs=fs;
		this.comparator = comparator;
	}
	@Override
	public void execute() {
		Collections.sort(fs.getChildren(), this.comparator);
		for(FSElement fe : fs.getChildren())
			System.out.println(fe.getName());
	}
	public String toString()
	{
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("sort "+this.fs.getCurrent().getName()+"\n\r");
		return stringbuffer.toString();
	}

}