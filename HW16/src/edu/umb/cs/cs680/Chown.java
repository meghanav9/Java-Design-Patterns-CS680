package edu.umb.cs.cs680;
import java.util.ArrayList;

public class Chown implements Command {
	public String path;
	private FileSystem fs;
	private String owner;
	public ArrayList<String> pathentered;

	public Chown(FileSystem fs, String owner) {
		this.fs = fs;
		this.owner = owner;
		this.pathentered = new ArrayList<String>();
	}

	public void execute() {
		if (this.path.equals(".")||this.path.equals("")) {
			fs.getCurrent().setowner(this.owner);
			return;
		}
		if (this.path.equals("..")) {
			if (fs.getCurrent().getParent() != null) {
				fs.getCurrent().getParent().setowner(this.owner);
			}
			return;
		}
		Directory current = fs.getCurrent();
		FSElement fe = null;
		if (path.startsWith("/"))
			current = fs.getRoot();
		boolean exist = false;
		for (int i = 0; i < pathentered.size(); i++) {
			String name = pathentered.get(i);
			
			for (FSElement child : current.getChildren()) {
				if (exist) {
					break;
				}
				if (child.getName().equals(name)) {
					if (i == pathentered.size() - 1) {
						fe = child;
						exist = true;
					} 
				}
			}
			if (!exist) {
				System.out.println("File or directory does not exist");
			}
		}
		if(exist){
		fe.setowner(this.owner);
		}
	}

	public String toString()
	{
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("chown "+this.owner+" "+this.path+"\n\r");
		return stringbuffer.toString();
	}
}