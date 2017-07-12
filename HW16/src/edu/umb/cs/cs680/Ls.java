package edu.umb.cs.cs680;
public class Ls implements Command {
	private FileSystem fs;
	public Ls(FileSystem fs) {
		this.fs = fs;
	}
	public void execute() {
		for (FSElement fse : fs.getChildren())
			System.out.println(fse.getName()+"-----------"+fse.getClass().getSimpleName());
	}
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("ls "+this.fs.getCurrent().getName()+"\n\r");
		return buffer.toString();
	}
}