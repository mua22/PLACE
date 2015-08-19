package epclaim.gui;

import java.io.File;
import java.net.URI;

public class TreeFile extends File{

	public TreeFile(File parent, String child) {
		super(parent, child);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		//System.out.println("Calling To String");
		return this.getName();
	}

	public TreeFile(String parent, String child) {
		super(parent, child);
		// TODO Auto-generated constructor stub
	}

	public TreeFile(String pathname) {
		super(pathname);
		// TODO Auto-generated constructor stub
	}

	public TreeFile(URI uri) {
		super(uri);
		// TODO Auto-generated constructor stub
	}

}
