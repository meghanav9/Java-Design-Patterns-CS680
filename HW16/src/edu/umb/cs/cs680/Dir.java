package edu.umb.cs.cs680;
import java.util.ArrayList;

public class Dir implements Command{
	public ArrayList<String> dirname;
	public String path;
	private FileSystem fs;
	
	public Dir(FileSystem fs){
		this.dirname = new ArrayList<String>();
		this.fs = fs;
	}

	public void execute(){
		if (this.path.equals("..")){
			if (fs.getCurrent().getParent() != null){
				for (FSElement fse : fs.getCurrent().getParent().getChildren())
					System.out.println(fse.toString());
			}
			return;
		}
		if (this.path.equals(".")||this.path.equals("")){
			for (FSElement fse : fs.getChildren())
				System.out.println(fse.toString());
			return;
		}
		Directory current = fs.getCurrent();
		FSElement fse = null;
		if (path.startsWith("/")){
			current = fs.getRoot();
		}	
		for (int i = 0; i < dirname.size(); i++){
			String name = dirname.get(i);
			boolean exist = false;
			for (FSElement child : current.getChildren()){
				if (exist) {
					break;
				}
				if (child.getName().equals(name)) {
					if (i == dirname.size() - 1) {
						fse = child;
						exist = true;
						if (fse != null){
							if (fse instanceof Directory){
								if(((Directory) fse).getChildren().size()>0)
								{
									for (FSElement element : ((Directory) fse).getChildren())
										System.out.println(element.toString());
								}
								else{
									System.out.println("Such dir or file doesn't exist in this directory.");
								}
							} else {
								System.out.println(fse.toString());
							}
						}	
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
		if (fse == null){
			System.out.println("Cannot find the dir or file");
		}
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("dir " + this.path + "\n");
		return stringbuffer.toString();
	}
}