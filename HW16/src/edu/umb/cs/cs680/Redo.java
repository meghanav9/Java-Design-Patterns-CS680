package edu.umb.cs.cs680;
public class Redo implements Command {
	private CommandHistory cmdhist;
	private Command lastCommand;
	public Redo(CommandHistory cmdhist) {
		this.cmdhist = cmdhist;
	}
	public void execute() {
		lastCommand=cmdhist.pop();
		lastCommand.execute();
	}
}