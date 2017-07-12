package edu.umb.cs.cs680;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FSVisitorTest {
	private Directory rootDir;
	private Directory Dir1;
	private File f1;
	private Link link1;
	@Before
	public void FileSystem(){
		rootDir = new Directory(null, "root");
		Dir1 = new Directory(rootDir, "system");
		Directory Dir2 = new Directory(rootDir, "home");
		f1 = new File(Dir1, "a.txt",10);
		File f2 = new File(Dir1, "b.txt",10);
		File f3 = new File(Dir1, "c.txt",10);

		rootDir.appendChild(Dir1);
		rootDir.appendChild(Dir2);
		Dir1.appendChild(f1);
		Dir1.appendChild(f3);
		Dir1.appendChild(f2);
		
		File f4 = new File(Dir2,"d.doc", 10);
		Dir2.appendChild(f4);
		link1 = new Link(Dir2, "x", Dir1);
		Dir2.appendChild(link1);
		
		Directory Dir3 = new Directory(Dir2, "pictures");
		Dir2.appendChild(Dir3);
		File f5 = new File(Dir3,"e.doc", 10);
		File f6 = new File(Dir3,"f.doc", 10);
		Dir3.appendChild(f5);
		Dir3.appendChild(f6);
		Link link2 = new Link(Dir3, "y", f5);
		Dir3.appendChild(link2);
	}
	@Test
	public void CountingVisitorsforDirectoryTest(){
		CountingVisitor visitor = new CountingVisitor();
		rootDir.accept(visitor);
		visitor.getFileNum();
		visitor.getLinkNum();
		assertThat(visitor.getDirNum(), is(4));  
	}
	@Test
	public void CountingVisitorsforFileTest(){
		CountingVisitor visitor = new CountingVisitor();
		rootDir.accept(visitor);
		visitor.getFileNum();
		visitor.getLinkNum(); 
		assertThat(visitor.getFileNum(), is(6));  
	}
	@Test
	public void CountingVisitorsforLinkTest(){
		CountingVisitor visitor = new CountingVisitor();
		rootDir.accept(visitor);
		visitor.getFileNum();
		visitor.getLinkNum();
		assertThat(visitor.getLinkNum(), is(2)); 
	}
	@Test
	public void SizeCountingVisitorTest_onDirectory(){

		SizeCountingVisitor visitor1 = new SizeCountingVisitor();
		rootDir.accept( visitor1 );
		assertThat(visitor1.getTotalSize(), is(60));		
	}
	@Test
	public void SizeCountingVisitorTest_onLink(){

		SizeCountingVisitor visitor1 = new SizeCountingVisitor();
		link1.accept( visitor1 );
		assertThat(visitor1.getTotalSize(), is(0));		
	}
	@Test
	public void SizeCountingVisitorTest_onFile(){

		SizeCountingVisitor visitor1 = new SizeCountingVisitor();
		f1.accept( visitor1 );
		assertThat(visitor1.getTotalSize(), is(10));		
	}
	@Test
	public void FileSearchVisitorTest_onDirectory(){
		
		FileSearchVisitor visitor3 = new FileSearchVisitor(".txt");
		rootDir.accept( visitor3 );
		assertThat(visitor3.getFoundFiles().size(),is(3));
	}
	@Test
	public void FileSearchVisitorTest_onLink(){
		
		FileSearchVisitor visitor3 = new FileSearchVisitor(".txt");
		link1.accept( visitor3 );
		assertThat(visitor3.getFoundFiles().size(),is(0));
	}
	@Test
	public void FileSearchVisitorTest_onFile(){
		
		FileSearchVisitor visitor3 = new FileSearchVisitor(".txt");
		f1.accept( visitor3 );
		assertThat(visitor3.getFoundFiles().size(),is(1));
	}	
	@Test
	public void getChildTest(){
		assertThat(Dir1.getChildren().size(), is(3));
	}
	@Test
	public void getParentTest(){
		Dir1.setParent(rootDir);
		assertThat(Dir1.getParent(), is(rootDir));
	}
}
