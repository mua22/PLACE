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
public class ServerDispatcher implements Runnable{
    private Thread dispatcherThread;

    public ServerDispatcher() {
        dispatcherThread = new Thread(this);
    }
    public void start(){
        this.dispatcherThread.start();
    }
    @Override
    public void run() {
        
    }
    
}
