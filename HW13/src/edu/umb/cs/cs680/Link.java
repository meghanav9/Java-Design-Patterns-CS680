package edu.umb.cs.cs680;
import java.util.ArrayList;
import java.util.Iterator;


public class Link extends FSElement{
	private int size=0;
	FSElement target;
	private ArrayList<FSElement> refer;
	
	Link(Directory parent, String name, String owner) {
		super(parent, name, owner, 0);
		this.refer = new ArrayList<>();
		this.size = 0;
	}
	public void linkto(FSElement fs){
		refer.add(fs);
	}
	
	public ArrayList<FSElement> getChildren(){
		return this.refer;
	}

	public int getTargetSize(){
		
		Iterator<FSElement> itr =this.getChildren().iterator();	
		while(itr.hasNext()){
			FSElement elem = itr.next();
			if(elem.isLink()){
				size = size + elem.getsize();
				((Link) elem).getTargetSize();
			}
			else if(!elem.isFile()){								
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
