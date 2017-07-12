package edu.umb.cs.cs680;
import java.util.ArrayList;
import java.util.Date;

public class Ln implements Command {
	private FileSystem fs;
	private String linkName;
	public String path;
	public ArrayList<String> pathentered;

	public Ln(FileSystem fs, String linkName) {
		this.fs = fs;
		this.linkName = linkName;
		this.pathentered = new ArrayList<String>();
	}

	public void execute() {
		Date date = new Date();
		if (this.path.equals("..")) {
			if (fs.getCurrent().getParent() != null) {
				Link newlink = new Link(fs.getCurrent(), this.linkName, "", date); 
				fs.getCurrent().appendChild(newlink);
				newlink.linkto(fs.getCurrent().getParent());	
			}
			return;
		}
		if (this.path.equals("")) {
			Link newlink = new Link(fs.getCurrent(), this.linkName, "", date);
			fs.getCurrent().appendChild(newlink);
			newlink.linkto(fs.getRoot());
			return;
		}
		if (this.path.equals(".")) {
			Link newlink = new Link(fs.getCurrent(), this.linkName, "", date);
			fs.getCurrent().appendChild(newlink);
			newlink.linkto(fs.getCurrent());
			return;
		}
		Directory current = fs.getCurrent();
		FSElement fe = null;
		if (path.startsWith("/")){
			current = fs.getRoot();
		}
		for (int i = 0; i < pathentered.size(); i++){
			String name = pathentered.get(i);
			boolean exist = false;
			for (FSElement child : current.getChildren()){
				if (exist) {
					break;
				}
				if (child.getName().equals(name)){
					if (i == pathentered.size() - 1) {
						fe = child;
						exist = true;
					} else {
						switch (child.getClass().getSimpleName()) {
						case "Directory": {
							current = (Directory) child;
							exist = true;
							break;
						}
						case "Link": {
							FSElement target = child.getTarget();
							if (target instanceof Directory) {
								current = (Directory) target;
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
					}
				}
			}
			if (!exist){
				break;
			}
		}
		if (fe != null){
			Link newlink =new Link(fs.getCurrent(), this.linkName, "", date);
			fs.getCurrent().appendChild(newlink);
			newlink.linkto(fe);
		} else {
			System.out.println("File or directory does not exist");
		}
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("ln " + this.linkName + "\n");
		return stringbuffer.toString();
	}
}