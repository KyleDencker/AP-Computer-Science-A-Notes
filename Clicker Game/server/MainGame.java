import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.JComponent;

public class MainGame extends JComponent  {
	private ArrayList<Player> playerList;
	private String serverID;
	public MainGame() {
		playerList = new ArrayList<Player>();
		//playerList.add(new Player("testing"));
		//playerList.add(new Player("testing 1"));
		//playerList.add(new Player("testing 2"));
		//playerList.add(new Player("testing 3"));

		try {
			InetAddress thisIp = InetAddress.getLocalHost();
			serverID = thisIp.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void giveChoice(String playerName, String choice) {
		for (Player play : playerList) {
			if (play.getName().equals(playerName)) {
				play.setChoice(Integer.parseInt(choice));
				repaint();
				return;
			}
		}
		playerList.add(new Player(playerName));
		giveChoice(playerName, choice);
	}
	
	public void updateScore(int correct) {
		for (Player play : playerList) {
			play.checkAnswer(correct);
		}
		Collections.sort(playerList);
		repaint();
	}
	
	public void paint(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 75)); 
		g.drawString("Server IP: " + serverID, 50, 70);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 36));
		
		for (int i=0; i<playerList.size(); i++) {
			if (!playerList.get(i).isSet()) {
				g.setColor(Color.red);
			} else {
				g.setColor(Color.green);
			}
			g.fillRect(0, 120+i*36, 500, 36);
			g.setColor(Color.white);
			g.drawString(i +1+ ".) "+playerList.get(i).toString(), 20, 150+i*36);
		}


	}
	
}
