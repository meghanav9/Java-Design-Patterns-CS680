package edu.umb.cs.cs680;
import java.util.ArrayList;

public class Cd implements Command {
	private FileSystem fs;
	public String path;
	public ArrayList<String> pathentered;

	public Cd(FileSystem fs) {
		this.fs = fs;
		this.pathentered = new ArrayList<String>();
	}
	public void execute() {
		if((path.startsWith("/")&& path.length()==1)||this.path.equals("")){
			fs.setCurrent(fs.getRoot());
			return;
		}
		if (this.path.equals("..")) {
			if (fs.getCurrent().getParent() != null) {
				fs.setCurrent(fs.getCurrent().getParent());
			}
			return;
		}
		if (this.path.equals(".")) {
			return;			
		}
		Directory current = fs.getCurrent();
		if (path.startsWith("/")){
			current = fs.getRoot();
		}
		for (String name : pathentered) {
			boolean exist = false;
			for (FSElement child : current.getChildren()) {
				if (exist) {
					break;				
				}
				if (child.getName().equals(name)) {
					switch (child.getClass().getSimpleName()) {
						case "Directory": {
							current = (Directory) child;
							exist = true;
							break;
						}
						case "Link": {
							FSElement linkto = child.getTarget();
							if (linkto instanceof Directory) {
								current = (Directory) linkto;
								exist = true;
								break;
							} else {
								continue;
							}
						}
						default: {
							continue;
						}
					}
				} else {
					continue;
				}
			}
			if (!exist) {
				System.out.println("Directory doesn't exist.");
			}
		}
		fs.setCurrent(current);
	}

	public String toString()
	{
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("cd "+this.path+"\n");
		return stringbuffer.toString();
	}
}