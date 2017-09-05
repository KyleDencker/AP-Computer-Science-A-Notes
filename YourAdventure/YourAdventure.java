import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class YourAdventure extends JComponent implements MouseListener {

	public static String nameOfApp = "Your Adventure!";
	
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	
	public static void main(String[] args) {
		JFrame frame = new JFrame(nameOfApp);
		frame.getDefaultCloseOperation();
		frame.add(new YourAdventure());
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.RED);
	}

}
