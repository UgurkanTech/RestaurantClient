import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ReceiverTCP extends Thread{
		private Socket socket;
		private InputStream input;
		private DataInputStream dataInput;
		private int clientID;
		
		private boolean compressData;
		private boolean encryptData;
		private final boolean DEBUG = false;
		
		private int encryptKey;
		
		public int getClientID() {
			return clientID;
		}
		
		public ReceiverTCP(Socket s, int clientID, int serverSecret, boolean compress, boolean encrypt) {
			this.socket = s;
			this.clientID = clientID;
			this.compressData = compress;
			this.encryptData = encrypt;
			encryptKey = clientID * serverSecret;
			start();
		}
		@Override
		public void run() {
			try {
				input = socket.getInputStream();
				dataInput = new DataInputStream(input);
			} catch (Exception e) {e.printStackTrace();}
			//Data loop
			while (true) {
				try {
					int bufferSize = dataInput.readInt();
					byte[] buffer = new byte[bufferSize];
					dataInput.read(buffer);
					
					if (encryptData) {buffer = RMSUtils.AES.decrypt(buffer, encryptKey + "");}
					
					if (compressData) {buffer = RMSUtils.Compressor.Uncompress(buffer);}
					
					String text = new String(buffer);
					
					//System.out.println("Client " + clientID + " says: " + text + " - bytes: " + bufferSize);
					CommandExecutioner.execute(text);
					
				} catch (IOException e1) {
					if (DEBUG) e1.printStackTrace(); else System.err.println("Data reading failed! - Client: " + clientID);
					break;
				}
				//try {Thread.sleep(1000);} catch (Exception e) {e.printStackTrace();}
			}
		}
	}