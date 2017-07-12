package edu.umb.cs.cs680;

public class CountingVisitor implements FSVisitor{
	int dirNum =0;
	int fileNum =0;
	int linkNum =0;
	@Override
	public void visit(Link link) {
		this.linkNum++;
	}
	
	@Override
	public void visit(Directory dir) {
		this.dirNum++;	
	}
	
	@Override
	public void visit(File file) {
		this.fileNum++;	
	}
	
	public int getDirNum(){
		return this.dirNum;
	}
	
	public int getLinkNum(){
		return this.linkNum;
	}
	
	public int getFileNum(){
		return this.fileNum;
	}
}
