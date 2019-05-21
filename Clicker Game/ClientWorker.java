import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientWorker implements Runnable {

	private Socket client;
	
	public ClientWorker(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String line;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("in or out failed");
			System.exit(-1);
		}
		while (true) {
			try {
				line = in.readLine();
				System.out.println("Got Message: " + line);
				// send data back to the client
				out.println(line);
			} catch (IOException e) {
				System.out.println("Read failed");
				System.exit(-1);
			}
			
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	

}
