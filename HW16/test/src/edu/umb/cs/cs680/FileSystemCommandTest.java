package edu.umb.cs.cs680;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;
import org.junit.Before;

import java.util.Date;



public class FileSystemCommandTest {

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
	private CommandHistory history ;
	private String path;
	private Shell shell;
	@Before
	public void FileSystemCommandTest()
	{
		date = new Date();
		tmp = FileSystem.getFileSystem();
		tmp.setRoot(new Directory(null, "root", "own", date));
		root=tmp.getRoot();
		tmp.setCurrent(tmp.getRoot());
	    system = new Directory(tmp.getRoot(), "system","root", date);
	    home = new Directory(tmp.getRoot(), "home","root", date);
	    a = new File(system,"a","system",date, 10);
	    b = new File(system,"b","system",date, 10);
	    c = new File(system,"c","system", date,10);
	    pictures = new Directory(home, "pictures","home", date);
	    x = new Link(home, "x", "home", date);
	    
	    d = new File(home,"d","home",date, 10);
	    y = new Link(pictures,"y","picture", date);
	    e = new File(pictures,"e","pictures",date, 60);
	    f = new File(pictures,"f","pictures", date, 10);
        
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
        
        history = new CommandHistory();
        this.shell = new Shell();
		this.path = "/home/pictures";

	}
	@Test
	public void Cd_Test() {
		Cd cd = new Cd(tmp);
		cd.path=this.path;
		cd.pathentered = shell.pathSplit(cd.path);
		cd.execute();
		this.history.push(cd);
		assertThat(tmp.getCurrent().getName(), is("pictures"));
	}
	@Test
	public void Cd2_Test() {
		Cd cd = new Cd(tmp);
		cd.path="";
		cd.pathentered = shell.pathSplit(cd.path);
		cd.execute();
		this.history.push(cd);
		assertThat(tmp.getCurrent().getName(), is("root"));
	}
	@Test
	public void Mv_Test(){
		int size = tmp.getChildren().size();
		Mv mv = new Mv(tmp, "home", "system");
		mv.execute();
		this.history.push(mv);
		assertThat(tmp.getChildren().size(), is(size-1));
		
	}
	@Test
	public void dir_Test() {
		int size = tmp.getChildren().size();
		Dir dir = new Dir(tmp);
		dir.path="";
		dir.dirname = shell.pathSplit(dir.path);
		dir.execute();
		this.history.push(dir);
		assertThat(tmp.getChildren().size(), is(size));
	}
	@Test
	public void Cp_Test(){
		Cp cp = new Cp(tmp, "home", "system");
		cp.execute();
		this.history.push(cp);
		assertThat(tmp.getCurrent().getName(), is("root"));
		
	}
	@Test
	public void Pwd_Test(){
	Pwd pwd = new Pwd(tmp);
	   pwd.execute();
	   this.history.push(pwd);
	   assertThat(tmp.getCurrent().getName(), is("root"));
	}
	@Test
	public void Ls_Test(){
		int size = tmp.getChildren().size();
		Ls ls = new Ls(tmp);
		ls.execute();
		this.history.push(ls);
		assertThat(tmp.getChildren().size(), is(size));
	}

	@Test
	public void Mkdir_Test() {
		int size = tmp.getChildren().size();
		Mkdir mkdir = new Mkdir(tmp,"newdir");
		mkdir.execute();
		this.history.push(mkdir);
		assertThat(tmp.getChildren().size(), is(size+1));
	}
	@Test
	public void Rmdir_Test() {
		int size = tmp.getChildren().size();
		Rmdir rmdir = new Rmdir(tmp,"system");
		rmdir.execute();
		this.history.push(rmdir);
		assertThat(tmp.getChildren().size(), is(size-1));
	}
	@Test
	public void HistoryRedo_Test(){
		 History hist = new History(this.history);
         hist.execute();
         this.history.push(hist);
         Redo redo = new Redo(this.history);
         redo.execute();
         String lastCommand=(this.history.pop()).toString();
         assertThat(lastCommand,is("history \n"));
	}
	@Test
	public void Ln_Test() {
		int c = tmp.getChildren().size();
		Ln ln = new Ln(tmp,"newlink1");
		ln.path = "/home";
		ln.pathentered = shell.pathSplit(ln.path);
		ln.execute();
		this.history.push(ln);
		assertThat(tmp.getChildren().size(), is(c+1));
	}
	@Test
	public void Chown_Test() {
		Chown chown = new Chown(tmp,"newown");
		chown.path = "";
		chown.pathentered = shell.pathSplit(chown.path);
		chown.execute();
		this.history.push(chown);
		assertThat(tmp.getCurrent().getowner(), is("newown"));
	}
	@Test
	public void sort_Test(){
		int size = tmp.getChildren().size();
		Comparator<FSElement> compare =new AlphabeticalComparator();
		Comparator<FSElement> compare1 =new ReverseAlphabeticalComparator();
   	   Sort sort = new Sort(tmp,compare);
   	   sort.execute();
   	   this.history.push(sort);
   	   assertThat(tmp.getChildren().size(), is(size));
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
	public void getParentTest1(){
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
		assertThat(y.getTargetSize(),is(60));
        
	}
	@Test
	public void getSize_home_Test(){
        assertThat(home.getsize(),is(80));
	}
	@Test
	public void linktolinkTest(){
		Link newlink = new Link(pictures,"newLink","pictures",date);
		newlink.linkto(y);
		assertThat(newlink.getTargetSize(),is(0));
		
	}
	
	@Test
	public void CountingVisitorsforDirectoryTest(){
		CountingVisitor visitor = new CountingVisitor();
		tmp.getRoot().accept(visitor);
		visitor.getFileNum();
		visitor.getLinkNum();
		assertThat(visitor.getDirNum(), is(4));  
	}
	@Test
	public void CountingVisitorsforFileTest(){
		CountingVisitor visitor = new CountingVisitor();
		tmp.getRoot().accept(visitor);
		visitor.getFileNum();
		visitor.getLinkNum(); 
		assertThat(visitor.getFileNum(), is(6));  
	}
	@Test
	public void CountingVisitorsforLinkTest(){
		CountingVisitor visitor = new CountingVisitor();
		tmp.getRoot().accept(visitor);
		visitor.getFileNum();
		visitor.getLinkNum();
		assertThat(visitor.getLinkNum(), is(2)); 
	}
	@Test
	public void SizeCountingVisitorTest_onDirectory(){

		SizeCountingVisitor visitor1 = new SizeCountingVisitor();
		tmp.getRoot().accept( visitor1 );
		assertThat(visitor1.getTotalSize(), is(110));		
	}
	@Test
	public void SizeCountingVisitorTest_onLink(){

		SizeCountingVisitor visitor1 = new SizeCountingVisitor();
		x.accept( visitor1 );
		assertThat(visitor1.getTotalSize(), is(0));		
	}
	@Test
	public void SizeCountingVisitorTest_onFile(){

		SizeCountingVisitor visitor1 = new SizeCountingVisitor();
		a.accept( visitor1 );
		assertThat(visitor1.getTotalSize(), is(10));		
	}
	@Test
	public void FileSearchVisitorTest_onDirectory(){
		
		FileSearchVisitor visitor3 = new FileSearchVisitor(".txt");
		tmp.getRoot().accept( visitor3 );
		assertThat(visitor3.getFoundFiles().size(),is(0));
	}
	@Test
	public void FileSearchVisitorTest_onLink(){
		
		FileSearchVisitor visitor3 = new FileSearchVisitor(".txt");
		x.accept( visitor3 );
		assertThat(visitor3.getFoundFiles().size(),is(0));
	}
	@Test
	public void FileSearchVisitorTest_onFile(){
		
		FileSearchVisitor visitor3 = new FileSearchVisitor(".txt");
		a.accept( visitor3 );
		assertThat(visitor3.getFoundFiles().size(),is(0));
	}	
	@Test
	public void getChildTest(){
		assertThat(home.getChildren().size(), is(3));
	}
	@Test
	public void getParentTest(){
		home.setParent(tmp.getRoot());
		assertThat(home.getParent(), is(tmp.getRoot()));
	}
}