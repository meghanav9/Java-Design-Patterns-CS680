package edu.umb.cs.cs680;


public abstract class FSElement {
	private String name;
	private Directory parent = null;
	private int size;

	FSElement(Directory parent, String name){
		this.parent = parent;
		this.name = name;		
	}
	
	public void setParent(Directory parent) {
		this.parent = parent;
	}

	public Directory getParent(){
		return this.parent;	
	}
	
	public String getName(){
		return this.name;
	}
	
	public abstract void accept(FSVisitor v); 
	
	public abstract int getDiskUtil() ;
	
}
