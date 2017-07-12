package edu.umb.cs.cs680;
import java.util.Date;

public abstract class FSElement {
		private String name;
		private String owner;
		private Date created = new Date();
		private Date lastModified;
		private int size;
		private Directory parent = null;

		FSElement(Directory parent, String name, String owner, int size){
			this.parent = parent;
			this.name = name;
			this.owner = owner;
			this.created = new Date();
			this.lastModified = new Date();
			this.size = size;			
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
		public String getname(){
			return this.name;
		}
		public void setname(String name){
			this.name = name;
		}
		public Date getcreated(){
			return this.created;
		}
		
}
