/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epclaimserver.gui;
import epclaimserver.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Usman
 */
public class ClientTreeModel extends DefaultTreeModel implements TreeModel,Observer{
    
    //private DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root Node");
    private EPClaimServer server;
    private DefaultMutableTreeNode parent;
    public ClientTreeModel(EPClaimServer server) {
        super(new DefaultMutableTreeNode(server));
        parent = (DefaultMutableTreeNode)this.getRoot();
        this.server = server;
        
        
    }
//
//  
//    
//    @Override
//    public Object getRoot() {
//        return super.getRoot();
//    }
//
//    @Override
//    public Object getChild(Object parent, int index) {
//        //if(parent.getClass().getName()=="epclaimserver.EPClaimServer"){}
//        System.out.println("getChild "+parent+" index:" +index);
//        //this.reload();
//        if(index<server.getClientNodes().size())
//        return server.getClientNodes().get(index);
//        else return null;
//    }
//
//    @Override
//    public int getChildCount(Object parent) {
//        //System.out.println(parent.getClass().getName());
//        if(parent.getClass().getName()=="epclaimserver.EPClaimServer"){
//            System.out.println("Server in child Count "+server.getClientNodes().size());
//            
//            return server.getClientNodes().size();
//        }
//        
//        else return 0;
//    }
//
//    @Override
//    public boolean isLeaf(Object node) {
//        
//        if(node.getClass().getName()=="epclaimserver.Node")
//            return true;
//        else return false;
//    }
//
//    @Override
//    public void valueForPathChanged(TreePath path, Object newValue) {
//         //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public int getIndexOfChild(Object parent, Object child) {
//         //To change body of generated methods, choose  Tools | Templates.
//        int index = server.getClientNodes().indexOf((Node)child);
//        return index;
//    }
//
//    @Override
//    public void addTreeModelListener(TreeModelListener l) {
//         //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void removeTreeModelListener(TreeModelListener l) {
//         //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void insertNodeInto(MutableTreeNode newChild, MutableTreeNode parent, int index) {
//        super.insertNodeInto(newChild, parent, parent.getChildCount()); //To change body of generated methods, choose Tools | Templates.
//        System.out.println("Called ");
//    }
    
    public void addNode(Node node){
        this.insertNodeInto(new DefaultMutableTreeNode(node.toString()), parent, parent.getChildCount());
    }

    @Override
    public void update(Observable o, Object arg) {
        //System.out.println("Observerd");
        this.addNode((Node)arg);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
