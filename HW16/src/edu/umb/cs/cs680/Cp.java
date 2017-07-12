package edu.umb.cs.cs680;

public class Cp  implements Command {

	String path;
	private FileSystem fs;
	private String sourcedir;
	private String destdir;
	private FSElement fse = null;
	private FSElement fse1 = null;
	public Cp(FileSystem fs, String sourcedir, String destdir) {
		this.fs=fs;
		this.sourcedir =sourcedir;
		this.destdir = destdir;
		
	}
	public void execute() {
		Directory current = fs.getCurrent();
		
		boolean exist = false;
		Directory root = fs.getRoot();
		for (FSElement child : current.getChildren()){
			if(exist){
				break;
			}
			if (child.getName().equals(sourcedir)) {
				fse=child;
				if(fse.getParent().getName().equals(destdir)){
					System.out.println("Source file already exist");
					return;
				}
				for (FSElement childdest : root.getChildren()){
					fse1 = childdest;
					if (fse1 != null){
						if(fse1.getName().equals(destdir)){
							if(alreadyexist(fse1)){return;}
							exist= true;
							Directory destfile = (Directory) fse1;
							destfile.appendChild(fse);
							System.out.println("One file copied");
							break;
						}
						else {
							if (fse1 instanceof Directory){
								if(((Directory) fse1).getChildren().size()>0)
								{
									for (FSElement element : ((Directory) fse1).getChildren()){
										if(element.getName().equals(destdir)){
											if(alreadyexist(element)){return;}
											if(element instanceof Directory){
												exist= true;
												Directory destfile = (Directory) element;
												destfile.appendChild(fse);
												System.out.println("One file copied");
											}
											else{
												System.out.println("Destination directory not found");
											}
										}
									}	
								}
								else{
									System.out.println("Such dir or file doesn't exist in this directory.");
								}
							}
						}
						
					}	
					
				}	
			}
		}
		if (fse == null||exist==false){
			System.out.println("Cannot find the dir or file specified");
		}
	}
	
	private boolean alreadyexist(FSElement compar) {
		if(fse.getParent().getName().equals(compar.getParent().getName())&&
				fse.getName().equals(compar.getName())){
			System.out.println("Source file already exist");
			return true;
		}
		return false;
		
	}
	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("cp " + this.sourcedir + " " + this.destdir + "\n");
		return stringbuffer.toString();
	}
}
