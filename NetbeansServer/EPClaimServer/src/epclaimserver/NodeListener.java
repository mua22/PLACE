/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epclaimserver;

/**
 *
 * @author Usman
 */
public class NodeListener implements Runnable{
    private Node node;
    private ServerDispatcher dispatcher;
    private Thread nodeListenerThread;
    public NodeListener(Node node, ServerDispatcher dispatcher) {
        this.node = node;
        this.dispatcher = dispatcher;
        nodeListenerThread = new Thread(this);
    }

    public synchronized void start() {
        nodeListenerThread.start();
    }
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
