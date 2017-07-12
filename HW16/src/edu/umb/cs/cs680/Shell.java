package edu.umb.cs.cs680;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;


public class Shell{
    public enum CommandNames{
    	CD,DIR,CHOWN, QUIT, HISTORY, SORT, PWD,LS, NOVALUE, MKDIR, RMDIR, REDO, LN, CP, MV;
 	  public static CommandNames tocommand(String str)
 	    {
 	        try {
 	            return valueOf(str);
 	        } 
 	        catch (Exception ex) {
 	            return NOVALUE;
 	        }
 	    }   
    	}
	public ArrayList<String> pathSplit(String path1){
		ArrayList<String> result = new ArrayList<>();
		if (path1.startsWith("/")) {
			path1 = path1.substring(1);
		}
		result.addAll(Arrays.asList(path1.split("/")));
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		Shell shell = new Shell();
		Date date = new Date();
		FileSystem tmp = FileSystem.getFileSystem();
		tmp.setRoot(new Directory(null, "root", "own", date));
		Directory root=tmp.getRoot();
		tmp.setCurrent(tmp.getRoot());
		Directory system = new Directory(tmp.getRoot(), "system","root", date);
		Directory home = new Directory(tmp.getRoot(), "home","root", date);
		File a = new File(system,"a","system",date, 10);
		File b = new File(system,"b","system",date, 10);
		File c = new File(system,"c","system", date,10);
		Directory pictures = new Directory(home, "pictures","home", date);
		Link x = new Link(home, "x", "home", date);

		File d = new File(home,"d","home",date, 10);
		Link y = new Link(pictures,"y","picture", date);
		File e = new File(pictures,"e","pictures",date, 60);
		File f = new File(pictures,"f","pictures", date, 10);

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


		SizeCountingVisitor visitor1 = new SizeCountingVisitor();
		SizeCountingVisitor visitor2 = new SizeCountingVisitor();
		
		x.accept( visitor2 );
		visitor2.getTotalSize();
		root.accept( visitor1 );
		visitor1.getTotalSize();

		FileSearchVisitor visitor3 = new FileSearchVisitor(".txt");
		root.accept( visitor3 );
		visitor3.getFoundFiles().size();
        
        
	       CommandHistory cmdhist = new CommandHistory();
	       System.out.print(tmp.getCurrent().getInfo() + ">");
	       while (true) {
	       BufferedReader in = new BufferedReader(new InputStreamReader (System.in)) ;
	       String commandLine = in.readLine();
		String[] splitArray = commandLine.split("\\s+");
		ArrayList<String> commandname = new ArrayList<String>(Arrays.asList(splitArray));  	

	       switch(CommandNames.tocommand(commandname.get(0).toUpperCase())){
			case REDO: {
				Redo redo = new Redo(cmdhist);
				redo.execute();
				break;
			}
		       case CD:{
			   Cd cd = new Cd(tmp);
					if (commandname.size() > 1) {
						cd.path = commandname.get(1);
					} else {
						cd.path = "";
					}
					cd.pathentered = shell.pathSplit(cd.path);			
					cd.execute();
					cmdhist.push(cd);
					break;
		       }
			case CP: {
				if (commandname.size() < 3) {
					break;
				} 
				Cp cpp = new Cp(tmp,commandname.get(1), commandname.get(2));
				cpp.execute();
				cmdhist.push(cpp);
				break;

			}
			case MV: {
				if (commandname.size() < 3) {
					break;
				} 
				Mv mv = new Mv(tmp,commandname.get(1), commandname.get(2));
				mv.execute();
				cmdhist.push(mv);
				break;

			}
			case DIR: {
				Dir dir = new Dir(tmp);
				if (commandname.size() > 1) {
					dir.path = commandname.get(1);
				} else {
					dir.path = "";
				}
				dir.dirname = shell.pathSplit(dir.path);
				dir.execute();
				cmdhist.push(dir);
				break;
			}
		       case QUIT:{
					return;
				}
		       case HISTORY:{
			   History hist = new History(cmdhist);
			   hist.execute();
			   cmdhist.push(hist);
			   break;
		       }
		       case SORT:{
			   Comparator<FSElement> compare =new AlphabeticalComparator();
				if(commandname.size()>1) {
					switch(commandname.get(1)){
							case "-a":
							{
								compare = new AlphabeticalComparator();
								break;
							}
							case "-r":
							{
								compare = new ReverseAlphabeticalComparator();
								break;
							}
					   default:
					   {
						   break;
					   }
				   }
				}
			   Sort sort = new Sort(tmp,compare);
			   sort.execute();
			   cmdhist.push(sort);
			   break;
		       }
			case CHOWN: {
				if (commandname.size() < 2) {
					break;
				} 
				Chown chown = new Chown(tmp,commandname.get(1));
				if (commandname.size() > 2) {
					chown.path = commandname.get(2);
				} else {
					chown.path = "";
				}
				chown.pathentered = shell.pathSplit(chown.path);
				chown.execute();
				cmdhist.push(chown);
				break;
			}
		       case PWD:
		       {
			   Pwd pwd = new Pwd(tmp);
			   pwd.execute();
			   cmdhist.push(pwd);
			   break;
		       }
		       case LS: {
				Ls ls = new Ls(tmp);
				ls.execute();
				cmdhist.push(ls);
				break;
			}
			case MKDIR: {
				if (commandname.size() < 2) {
					break;
				} 
				Mkdir mkdir = new Mkdir(tmp,commandname.get(1));
				mkdir.execute();
				cmdhist.push(mkdir);
				break;
			}
			case RMDIR: {
				if (commandname.size() < 2) {
					break;
				} 
				Rmdir rmdir = new Rmdir(tmp,commandname.get(1));
				rmdir.execute();
				cmdhist.push(rmdir);
				break;
			}
			case LN: {
				if (commandname.size() < 2) {
					break;
				} 
				Ln ln = new Ln(tmp,commandname.get(1));
				if (commandname.size() > 2) {
					ln.path = commandname.get(2);
				} else {
					ln.path = "";
				}
				ln.pathentered = shell.pathSplit(ln.path);
				ln.execute();
				cmdhist.push(ln);
				break;
			}

		       default:
			   break;
		       }	
	       	       System.out.print(tmp.getCurrent().getInfo() + ">");
	       }
      
	}
}

