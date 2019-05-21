import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JComponent;

public class MainGame extends JComponent implements MouseListener {
	String username, ip;
	Socket socket;

	public MainGame(String username, String ip)  {
		this.username = username;
		this.ip = ip;

		try {
			socket = new Socket(ip, 59898);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Connected");
		addMouseListener(this);
	}

	public void sendMessage(int choice) throws IOException {
		Scanner in = new Scanner(socket.getInputStream());
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(username+":"+choice);
	}

	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, 100, 100);
		g.setColor(Color.yellow);
		g.fillRect(100, 0, 100, 100);
		g.setColor(Color.green);
		g.fillRect(200, 0, 100, 100);
		g.setColor(Color.blue);
		g.fillRect(300, 0, 100, 100);
		g.setColor(Color.orange);
		g.fillRect(400, 0, 100, 100);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			if (e.getX() < 100)

				sendMessage(1);

			else if (e.getX() < 200)
				sendMessage(2);
			else if (e.getX() < 300)
				sendMessage(3);
			else if (e.getX() < 400)
				sendMessage(4);
			else if (e.getX() < 500)
				sendMessage(5);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Did not send message");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
