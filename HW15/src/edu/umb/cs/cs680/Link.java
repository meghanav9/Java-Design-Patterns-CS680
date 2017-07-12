package edu.umb.cs.cs680;


public class Link extends FSElement{
	FSElement target;
	Link(Directory parent, String name, FSElement target) {
		super(parent, name);
		this.target = target;
	}
	
	public void accept(FSVisitor v) {
	    v.visit(this);
	  }
	
	public int getDiskUtil(){
		return 0;
	}
}
