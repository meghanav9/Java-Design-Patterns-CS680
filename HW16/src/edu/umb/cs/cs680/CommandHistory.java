package edu.umb.cs.cs680;


import java.util.ArrayList;


public class CommandHistory{
	private ArrayList<Command> command;
	
	public CommandHistory(){
		this.command = new ArrayList<Command>();
	}
	public void push(Command command){
		this.command.add(command);
	}
	public Command pop(){
		if (this.command.size() > 0) {
			int cmdsize = command.size()-1;
			return command.get(cmdsize);
		}
		return null;
	}
	public ArrayList<Command> getCommandHistory()
	{
		return this.command;
	}
	public String toString()
	{
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("----Command history ----\n");
		for(Command c : this.command)
		{
			stringbuffer.append(c);
		}
		stringbuffer.append("------------------------");
		return stringbuffer.toString();
	}
}