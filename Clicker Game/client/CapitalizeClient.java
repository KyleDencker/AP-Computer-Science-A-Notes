import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CapitalizeClient {
	public static MainGame myGame;
    public static void main(String[] args) throws Exception {
    	
    	// Start up UI
    	String ip = JOptionPane.showInputDialog(null, "Enter the IP address you would like to connect to: ",
				"Connect to User", 1);
    	String username = JOptionPane.showInputDialog(null, "Please enter the display name: ",
				"Connect to User", 1);

    	// Main Program UI
    	JFrame frame = new JFrame("AP Study Game");
		frame.setSize(500, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myGame = new MainGame(username,ip);
		frame.add(myGame);
		frame.setVisible(true);
    	
    	
    }
}