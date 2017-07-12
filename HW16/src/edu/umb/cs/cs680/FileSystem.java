package edu.umb.cs.cs680;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class FileSystem {
	private static FileSystem instance = new FileSystem();
	private Directory root;
	//root = new Directory(null,"root", "own");
	private FSElement child; 
	private Directory current;
	private Directory parent;
	private Comparator<FSElement> comparator;
	private FileSystem(){}
	public static FileSystem getFileSystem() {
		return instance;
	}	
	public ArrayList<FSElement> sort(Directory dir, Comparator<FSElement> comparator){
		this.current = dir;
		this.comparator = comparator;
		Collections.sort(getChildren(), this.comparator);
		for(FSElement fse : getChildren())
			System.out.println(fse.getName());
		return null;
	}
	public void setRoot(Directory root){
		this.root = root;
	}
	public Directory getRoot(){
		return this.root;
	}
	public void setCurrent(Directory current){
		this.current = current;
	}
	public Directory getCurrent(){
		return this.current;
	}

	public void addChild(Directory current,FSElement child){
		this.current = current;
		this.child = child;
		parent.addChild(child, getInsertionLocation(parent , child));
	}
	private int getInsertionLocation(Directory dir, FSElement element) {
		return 0;
	}
	public ArrayList<FSElement> getChildren() {
		return this.current.getChildren();
	}
	
	
	public void showAllElements(){
		System.out.println("Parent:"+ root.getParent() +"\t"+"	Directory:	"+ root.getName());								
		this.root.showAllElements();
	}

}
