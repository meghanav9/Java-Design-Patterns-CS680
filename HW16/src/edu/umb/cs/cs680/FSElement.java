package edu.umb.cs.cs680;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public abstract class FSElement {
		protected String name;
		protected String owner;
		protected Date created = new Date();
		protected Date lastModified = new Date();
		protected int size;
		protected Directory parent = null;

		FSElement(Directory parent, String name, String owner, Date created){
			this.parent = parent;
			this.name = name;
			this.owner = owner;
			this.created = created;
			this.lastModified = created;			
		}
		public void setParent(Directory parent) {
			this.parent = parent;
		}
		public Directory getParent(){
			return this.parent;	
		}
		public boolean isFile(){			
			return this instanceof File;
		}
		public boolean isLink(){
			return this instanceof Link;
		}
		public abstract boolean isLeaf();
		
		public String getInfo(){
			StringBuffer buffer = new StringBuffer();
			buffer.append(this.name);
			FSElement fse = this.parent;
			while(fse!=null)
			{
				buffer.insert(0, fse.getName()+"/");
				fse=fse.parent;
			}
			return buffer.toString();
//			return buffer.toString().substring(1);
		}
		public int getsize(){
			return this.size;
		}
		public String getowner(){
			return this.owner;
		}
		public void setowner(String owner){
			this.owner = owner;
		}
		public void setlastModified(Date lastModified){
			this.lastModified = lastModified;
		}
		
		public Date getlastModified(){
			return this.lastModified;
		}
		public String getName(){
			return this.name;
		}
		public void setName(String name){
			this.name = name;
		}	
		public Date getcreated(){
			return this.created;
		}
		public abstract  FSElement getTarget() ;
		
		public abstract void accept(FSVisitor v); 
		
		public abstract int getDiskUtil() ;
}
