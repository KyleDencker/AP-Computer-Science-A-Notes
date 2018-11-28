package GameLogic;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;

public class MenuActions implements javax.swing.event.MenuListener {
	private int menu;

	public MenuActions() {
		this.menu = 1;
	}

	public MenuActions(int menu) {
		this.menu = menu;
	}

	public void menuCanceled(MenuEvent e) {
	}

	public void menuDeselected(MenuEvent e) {
	}

	public void menuSelected(MenuEvent e) {
		JMenu temp = (JMenu) e.getSource();

		switch (this.menu) {
		case 1:
			for (int i = 0; i < temp.getItemCount() - 2; i++) {
				if (GameBoard.isOnline) {
					temp.getItem(i).setEnabled(false);
				} else {
					temp.getItem(i).setEnabled(true);
				}
			}
			if (GameBoard.isOnline) {
				temp.getItem(3).setEnabled(true);
			} else {
				temp.getItem(3).setEnabled(false);
			}
			break;

		case 2:
			for (int i = 0; i < temp.getItemCount(); i++) {
				if (NET.Client.connected) {
					temp.getItem(i).setEnabled(false);
				} else {
					temp.getItem(i).setEnabled(true);
				}
			}
		}

		temp.repaint();
	}
}
