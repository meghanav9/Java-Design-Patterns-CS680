package edu.umb.cs.cs680;


public class FileSystem {
	private static FileSystem instance = new FileSystem();
	private Directory root = new Directory(null,"root", "own");
	
	private FileSystem(){}
	public static FileSystem getFileSystem() {
		return instance;
	}	
	public void showAllElements(){
		System.out.println("Owner :"+ root.getowner() +"\t"+"	Directory:	"+ root.getname());								
		this.root.showAllElements();
	}
	public Directory getroot(){
		return this.root;
	}
}
