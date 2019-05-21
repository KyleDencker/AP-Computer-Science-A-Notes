import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox.KeySelectionManager;

public class KeyBoardInput implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {

		if (e.getKeyChar() == 'a') {
			CapitalizeServer.myGame.updateScore(1);
		} else if (e.getKeyChar() == 'b') {
			CapitalizeServer.myGame.updateScore(2);
		} else if (e.getKeyChar() == 'c') {
			CapitalizeServer.myGame.updateScore(3);
		} else if (e.getKeyChar() == 'd') {
			CapitalizeServer.myGame.updateScore(4);
		} else if (e.getKeyChar() == 'e') {
			CapitalizeServer.myGame.updateScore(5);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
