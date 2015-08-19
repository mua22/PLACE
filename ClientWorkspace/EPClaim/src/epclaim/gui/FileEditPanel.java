package epclaim.gui;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.io.File;
import javax.swing.JPanel;

import epclaim.utils.FileUtility;

public class FileEditPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 569667126972906267L;

	/**
	 * Create the panel.
	 */
	private File file;
	private TextArea textArea;
	public FileEditPanel(File file) {
		this.file = file;
		textArea = new TextArea(FileUtility.fileToString(this.file));
		this.setLayout(new BorderLayout());
		this.add(textArea,BorderLayout.CENTER);
	}
	public FileEditPanel(String str){
		this(new File(str));
	}
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		FileEditPanel newEditPanel =  (FileEditPanel)arg0;
		return this.file.equals(newEditPanel.file);
	}
	
}
