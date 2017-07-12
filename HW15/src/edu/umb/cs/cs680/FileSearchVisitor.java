package edu.umb.cs.cs680;
import java.util.ArrayList;
public class FileSearchVisitor implements FSVisitor{
	private String extension;
	private ArrayList<File> foundFiles;
	
	FileSearchVisitor(String extension) {
		this.extension = extension;
		this.foundFiles = new ArrayList<>();
	}
	
	@Override
	public void visit(Link link) {
		return;	
	}
	
	@Override
	public void visit(Directory dir) {
		return;	
	}
	
	@Override
	public void visit(File file) {
		if(file.getName().contains(extension)){		
			foundFiles.add(file);
		}
	}
	
	
	public ArrayList<File> getFoundFiles(){
		return this.foundFiles;
	}
}
