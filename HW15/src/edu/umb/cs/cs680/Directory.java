package edu.umb.cs.cs680;
import java.util.ArrayList;
public class Directory extends FSElement{
	FSElement childrenn;
	private ArrayList<FSElement> children;
	Directory(Directory parent, String name) {
		super(parent, name);
		this.children = new ArrayList<>();
	}
	public void appendChild(FSElement fs){
		this.children.add(fs);
	}	
	public ArrayList<FSElement> getChildren(){
		return this.children;
	}
	public void accept(FSVisitor v) {
	    v.visit(this);
		for(FSElement e: children){
			e.accept(v);
		}
	  }
	public int getDiskUtil(){
		return 0;
	}
}
