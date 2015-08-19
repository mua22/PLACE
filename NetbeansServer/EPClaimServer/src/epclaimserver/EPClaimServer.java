/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epclaimserver;

import epclaimserver.gui.ServerGUIJframeForm;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

/**
 *
 * @author Usman
 */
public class EPClaimServer extends Observable implements Runnable{
    private final int PORT = 18524;
    ServerSocket serverSocket;
    private ServerDispatcher serverDispatcher;
    private ArrayList<Node> clientNodes;
    private ArrayList<Observer> observers;
    boolean running = true;
    private Thread thread;
    public EPClaimServer() {
        clientNodes = new ArrayList<Node>();
        observers = new ArrayList<Observer>();
        serverDispatcher = new ServerDispatcher();
        serverDispatcher.start();
        thread = new Thread(this);
        //clientNodes.add(new Node("Dummy Node"));
        try {
            serverSocket = new ServerSocket(this.PORT);
            System.out.println("Server Started on port: "+PORT);
        } catch (IOException ex) {
            Logger.getLogger(EPClaimServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getServerAddress(){
        return serverSocket.getInetAddress().getHostName()+":"+serverSocket.getLocalPort();
    }

    public ArrayList<Node> getClientNodes() {
        return clientNodes;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //EPClaimServer server = new EPClaimServer();
        //server.startServer();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EPClaimServer server = new EPClaimServer();
                //server.startServer();
                new ServerGUIJframeForm(server).setVisible(true);
            }
        });
    }

    @Override
    public void run() {
        this.running = true;
        int index=1;
        
        while(running){
            //Node node1 = new Node("Dummy ");
            //System.out.println("Adding Node" + node1);
            //clientNodes.add(node1);
            //setChanged();
            //notifyObservers(node1);
            //.out.println(this.);
            //ServerGUIJframeForm.treeModel.insertNodeInto(new DefaultMutableTreeNode(node1), null, index);
//            try {
//                Thread.sleep(4000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(EPClaimServer.class.getName()).log(Level.SEVERE, null, ex);
//            }
            try {
                Socket socket = serverSocket.accept();
                Node node = new Node(socket);
                clientNodes.add(node);
                System.out.println("Adding Node" + node);
                setChanged();
                notifyObservers(node);
                NodeListener nodeListener = new NodeListener(node, serverDispatcher);
                NodeSender nodeSender = new NodeSender(node, serverDispatcher);
                node.setNodeListener(nodeListener);
                node.setNodeSender(nodeSender);
                nodeListener.start();
                nodeSender.start();
                //clientNodes.add(new Node(("Node "+index)));
                
                
            } catch (IOException ex) {
                Logger.getLogger(EPClaimServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void stopServer(){
        this.running = false;
    }
    public void startServer(){
        this.running = true;
        thread.start();
    }

    @Override
    public String toString() {
        return this.getServerAddress();
    }
    
    
}
