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
	private File d ;
	private File e ;
	private File f;
	private Date date;
	@Before
	public void TreeStructure(){
		tmp = FileSystem.getFileSystem();
		date = new Date();
		root=tmp.getroot();
	    system = new Directory(root, "system","root");
	    home = new Directory(root, "home","root");
	    a = new File(system,"a","system", 10);
	    b = new File(system,"b","system", 10);
	    c = new File(system,"c","system", 10);
	    d = new File(home,"d","home", 10);   
	    pictures = new Directory(home, "pictures","home");
	    e = new File(pictures,"e","pictures", 10);
	    f = new File(pictures,"f","pictures", 10);
        
	    root.appendChild(system);
	    root.appendChild(home);
        system.appendChild(a);
        system.appendChild(b);
        system.appendChild(c);
        
        home.appendChild(pictures);
        home.appendChild(d);
        
        pictures.appendChild(e);
        pictures.appendChild(f);
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
	    system.setname("system");
	    assertThat(system.getname(),is("system"));
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
	public void getSize_home_Test(){
        assertThat(home.getsize(),is(30));
	}

}
