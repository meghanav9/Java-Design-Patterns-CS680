package edu.umb.cs.cs680;


public class File extends FSElement{
	private int size;
	File(Directory parent, String name, int size) {
		super(parent, name);
		this.size = size;
	}
	public int getDiskUtil(){
		return getSize();
	}
	public int getSize(){
		return this.size;
	}
	public void accept(FSVisitor v) {
	    v.visit(this);

	  }

}
