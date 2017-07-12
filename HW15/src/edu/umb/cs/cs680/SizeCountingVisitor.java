package edu.umb.cs.cs680;


public class SizeCountingVisitor implements FSVisitor{
	int totalSize =0;
	@Override
	public void visit(Link link) {
		this.totalSize += link.getDiskUtil();
	
	}
	
	@Override
	public void visit(Directory dir) {
		this.totalSize +=  dir.getDiskUtil();
	}
	
	@Override
	public void visit(File file) {
		this.totalSize += file.getDiskUtil();
	}
	
	public int getTotalSize(){	
		return this.totalSize;
	}
	
}
