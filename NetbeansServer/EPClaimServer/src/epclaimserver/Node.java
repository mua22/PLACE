/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epclaimserver;

import java.net.Socket;

/**
 *
 * @author Usman
 */
public class Node {
    private String name;
    private Socket socket;
    private NodeListener nodeListener;
    private NodeSender nodeSender;
    public Node(String name) {
        this.name = name;
    }

    public Node(Socket socket) {
        this.socket = socket;
        this.name = socket.getInetAddress().getHostName()+socket.getLocalPort();
    }
    
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Node{" + "name=" + name + '}';
    }

    @Override
    public boolean equals(Object obj) {
        Node node = (Node)obj;
        if(node.getName()==this.getName())
            return true;
        else return false;
    }
    public boolean isConnected(){
    if(socket.isConnected()){
       return true; 
    } else {
        return false;
    }
}
    public void setName(String name) {
        this.name = name;
    }

    public void setNodeListener(NodeListener nodeListener) {
        this.nodeListener = nodeListener;
    }

    public void setNodeSender(NodeSender nodeSender) {
        this.nodeSender = nodeSender;
    }
    
}
