package epclaim.communication;

import java.io.*;
import java.net.*;

public class Client extends Thread{
	private final static int SERVER_PORT = 18524;
	public static final  String SERVER_HOSTNAME = "localhost";
	Socket socket;
	@Override
	public void run() {
		BufferedReader in = null;
        PrintWriter out = null;
		try {
			//socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
			in = new BufferedReader(
			    new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(
			    new OutputStreamWriter(socket.getOutputStream()));
			System.out.println("Connected to server " +
			   SERVER_HOSTNAME + ":" + SERVER_PORT);
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.run();
	}
	
	public Client(Socket socket) {
		super();
		this.socket = socket;
	}

	public static void main(String args[]){
		try {
			Client client1 = new Client(new Socket(SERVER_HOSTNAME, SERVER_PORT));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Client client2 = new Client(new Socket(SERVER_HOSTNAME, SERVER_PORT));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//client1.start();
		//client2.start();
	}
}
