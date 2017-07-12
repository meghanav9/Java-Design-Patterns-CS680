package edu.umb.cs.cs680;
public class Pwd implements Command {
	private FileSystem fs;
	public Pwd(FileSystem fs) {
		this.fs = fs;
	}
	public void execute() {
		System.out.println(fs.getCurrent().toString());
	}
	public String toString()
	{
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("pwd "+this.fs.getCurrent().getName()+"\n\r");
		return stringbuffer.toString();
	}
}