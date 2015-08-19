package epclaim.gui;

import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeModelListener;
import java.io.File;


/** Example of a simple static TreeModel. It contains a
    (java.io.File) directory structure.
    (C) 2001 Christian Kaufhold (ch-kaufhold@gmx.de)
*/

public class FileTreeModel
    implements TreeModel
{
	protected TreeFile root;
	  public FileTreeModel(TreeFile root) { this.root = root; }
	  public Object getRoot() { return root; }

	  // Tell JTree whether an object in the tree is a leaf
	  public boolean isLeaf(Object node) {  return ((TreeFile)node).isFile(); }
	  public int getChildCount(Object parent) {
		    String[] children = ((TreeFile)parent).list();
		    if (children == null) return 0;
		    return children.length;
		  }

		  // Fetch any numbered child of a node for the JTree.
		  // Our model returns File objects for all nodes in the tree.  The
		  // JTree displays these by calling the File.toString() method.
		  public Object getChild(Object parent, int index) {
			  
		    String[] children = ((File)parent).list();
		    if ((children == null) || (index >= children.length)) return null;
		    
		    return new TreeFile((TreeFile) parent, children[index]);
		  }
		// Figure out a child's position in its parent node.
		  public int getIndexOfChild(Object parent, Object child) {
		    String[] children = ((TreeFile)parent).list();
		    if (children == null) return -1;
		    String childname = ((TreeFile)child).getName();
		    for(int i = 0; i < children.length; i++) {
		      if (childname.equals(children[i])) return i;
		    }
		    return -1;
		  }
		  public void valueForPathChanged(TreePath path, Object newvalue) {}

		  // Since this is not an editable tree model, we never fire any events,
		  // so we don't actually have to keep track of interested listeners
		  public void addTreeModelListener(TreeModelListener l) {}
		  public void removeTreeModelListener(TreeModelListener l) {}
}