import java.net.Socket;

/**
 * A TCP Client
 * 
 * @author Uğurkan Hoşgör
 *
 */

public class ClientTCP extends Thread{

	public boolean reconnectOnError = true;
	
	private final boolean DEBUG = false;
	
	public static boolean connected = false;
	
	private int clientID;

	private final int serverSecret = 1337;
	private boolean compressData;
	private boolean encryptData;
	
	String ip = "";
	int port = 0;
	
	
	public ClientTCP(String ip, int port) {
		this.ip = ip;
		this.port = port;
		startClient();
	}
	
	private void startClient() {
		start();
	}
	
	@Override
	public void run() {
		while(reconnectOnError) {
			try {
				Thread.sleep(100);
				System.out.println("Client trying to connect...");

				Socket socket = new Socket(ip, port);

				clientID = socket.getInputStream().read(); //get an id

				int protocol = socket.getInputStream().read();
				
				//set protocol
				if (protocol == 0) {compressData = false; encryptData = false;}
				else if (protocol == 1) {compressData = true; encryptData = false;}
				else if (protocol == 2) {compressData = false; encryptData = true;}
				else if (protocol == 3) {compressData = true; encryptData = true;}
				
				
				System.out.println("Connection established! - My ID: " + clientID);
				connected = true;
				ReceiverTCP receiver =  new ReceiverTCP(socket, clientID, serverSecret, compressData, encryptData);
				SenderTCP sender =  new SenderTCP(socket, clientID, serverSecret, compressData, encryptData);
				
				receiver.setName("Receiver-" + clientID);
				sender.setName("Sender-" + clientID);
				
				//They will finish if connection fails
				receiver.join();
				connected = false;
				
			} catch (Exception e) {if (DEBUG) e.printStackTrace(); else System.err.println("Client couldn't connect.");}
			
			//Delay for reconnecting..
			try {Thread.sleep(100);} catch (Exception e) {e.printStackTrace();}

		}
	}
	
	
}
