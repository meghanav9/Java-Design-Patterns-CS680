package edu.umb.cs.cs680;
public class History implements Command{
	private CommandHistory cmdhist;

	public History(CommandHistory cmdhist){
		this.cmdhist = cmdhist;
	}
	public void execute(){
		System.out.println(cmdhist);	
	}
	public String toString()
	{
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("history \n");
		return stringbuffer.toString();
	}
}