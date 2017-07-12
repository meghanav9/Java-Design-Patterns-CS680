package edu.umb.cs.cs680;

public class Rmdir implements Command{
	private FileSystem fs;
	private String dirname;
	public Rmdir(FileSystem fs,String dirname){
		this.fs = fs;
		this.dirname=dirname;
	}

	public void execute(){
		FSElement fse = null;
		for (FSElement element : fs.getCurrent().getChildren()){
			if (element.getName().equals(this.dirname)) {
				fse = element;
				fs.getCurrent().getChildren().remove(fse);
				break;
			}
		}
		if (fse == null){
			System.out.println("Directory doesn't exist");
		}
	}
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("rmdir "+this.dirname+"\n");
		return buffer.toString();
	}
}	