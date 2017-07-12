package edu.umb.cs.cs680;
import java.util.ArrayList;
import java.util.Iterator;

public class Directory extends FSElement{
	private int size=0;
	private ArrayList<FSElement> children;
	int size_arr[] = new int[5];
	FileSystem filesystem;
	FSElement childrenn;
	Directory(Directory parent,String name, String owner) {
		super(parent , name, owner,0);
		this.children = new ArrayList<>();
		this.size = 0;
	}
	public void appendChild(FSElement fs){
		children.add(fs);
	}
	
	public ArrayList<FSElement> getChildren(){
		return this.children;
	}


	public void showAllElements() {
		Iterator<FSElement> itr =this.getChildren().iterator();
		while(itr.hasNext()){
			FSElement elem = itr.next();
			if(!elem.isFile()){
				System.out.println("Owner :"+ elem.getowner() +"\t"+"	Directory:	"+ elem.getname());								
				if(!elem.isFile()){
					((Directory) elem).showAllElements();
				}
			}
			else{
				System.out.println("Owner :"+ elem.getowner() +"  "+"	File:	"+"\t"+ elem.getname());					
			}
		}
	}
	
	@Override
	public int getsize(){
		Iterator<FSElement> itr =this.getChildren().iterator();	
		while(itr.hasNext()){
			FSElement elem = itr.next();
			if(!elem.isFile()){								
				if(!elem.isFile()){
					size = size + elem.getsize();
					((Directory) elem).getsize();
				}
			}
			else{
				size = size + elem.getsize();
			}
		}
		return size;
	}
	
}