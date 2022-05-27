package Client;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {
	public static void main(String[] args) {
		SharedClient share = new SharedClient();
		
		try {
			Socket socket;
			socket = new Socket("localhost", 10010);
			Loops loop = new Loops(share, socket);
			loop.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
