package edu.umb.cs.cs680;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class FileSystemTest {
	private FileSystem tmp;
	private Directory root;
	private Directory system;
	private Directory home;
	private File a;
	private File b;
	private File c;
	private Directory pictures;
	private Link x;
	private File d ;
	private Link y ;
	private File e ;
	private File f;
	private Date date;
	@Before
	public void TreeStructure(){
		tmp = FileSystem.getFileSystem();
		date = new Date();
		root=tmp.getRoot();
	    system = new Directory(root, "system","root", date);
	    home = new Directory(root, "home","root", date);
	    a = new File(system,"a","system",date, 10);
	    b = new File(system,"b","system",date, 10);
	    c = new File(system,"c","system", date,10);
	    d = new File(home,"d","home",date, 10);
	    x = new Link(home, "x", "home", date);   
	    pictures = new Directory(home, "pictures","home", date);
	    y = new Link(pictures,"y","pictures", date);
	    e = new File(pictures,"e","pictures",date, 10);
	    f = new File(pictures,"f","pictures", date,10);
        
	    root.appendChild(system);
	    root.appendChild(home);
        system.appendChild(a);
        system.appendChild(b);
        system.appendChild(c);
        
        home.appendChild(pictures);
        home.appendChild(x);
        home.appendChild(d);
        
        pictures.appendChild(y);
        pictures.appendChild(e);
        pictures.appendChild(f);
        
        x.linkto(system);
        y.linkto(e);
	}
	@Test
	public void showAllElementsTest(){
		tmp.showAllElements();
	}
	@Test
	public void FileSystemInstanceTest() {
		FileSystem instance = FileSystem.getFileSystem();
		assertThat(FileSystem.getFileSystem(),is(instance));
	}
	@Test
	public void getcreatedTest(){
		assertThat(system.getcreated(),is(date));
	}
	@Test
	public void getParentTest(){
	    system.setParent(root);
	    assertThat(system.getParent(),is(root));
	}
	@Test
	public void getnameTest(){
	    system.setName("system");
	    assertThat(system.getName(),is("system"));
	}
	@Test
	public void getOwnerTest(){
	    system.setowner("root");
	    assertThat(system.getowner(),is("root"));
	}
	@Test
	public void getLastModifiedTest(){
			
	    system.setlastModified(date);
	 	assertThat(system.getlastModified(),is(date));
	}
	@Test
	public void getSize_x_Test(){
		assertThat(x.getsize(),is(0));
	}
	@Test
	public void getSize_y_Test(){
		assertThat(x.getsize(),is(0));
	}
	@Test
	public void getTargetSize_x_Test(){
		assertThat(x.getTargetSize(),is(30));
	}
	@Test
	public void getTargetSize_y_Test(){
		assertThat(y.getTargetSize(),is(10));
        
	}
	@Test
	public void getSize_home_Test(){
        assertThat(home.getsize(),is(30));
	}
	@Test
	public void linktolinkTest(){
		Link newlink = new Link(pictures,"newLink","pictures", date);
		newlink.linkto(y);
		assertThat(newlink.getTargetSize(),is(0));
		
	}

}
