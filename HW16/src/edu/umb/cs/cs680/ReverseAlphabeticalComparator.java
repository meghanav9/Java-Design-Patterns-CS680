package edu.umb.cs.cs680;
import java.util.Comparator;

public class ReverseAlphabeticalComparator implements Comparator<FSElement>{

	@Override
	public int compare(FSElement fse1, FSElement fse2) {
		return fse2.getName().toLowerCase().compareTo(fse1.getName().toLowerCase());
	}

}