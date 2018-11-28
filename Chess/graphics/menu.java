package graphics;

import GameLogic.Actions;
import GameLogic.MenuActions;
import javax.accessibility.AccessibleContext;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class menu {
	public static JMenuBar MainMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("File");
		menu.setMnemonic(65);
		menu.getAccessibleContext().setAccessibleDescription("Creates, saves, and opens games.");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("New Game", 78);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(78, 2));
		menuItem.addActionListener(new Actions());
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Close");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(115, 8));
		menuItem.addActionListener(new Actions());
		menu.add(menuItem);

		menu = new JMenu("Game Mode");
		menu.addMenuListener(new MenuActions(2));

		menu.setMnemonic(71);
		menu.getAccessibleContext().setAccessibleDescription("Changes the game mode");
		menuBar.add(menu);

		ButtonGroup group = new ButtonGroup();

		JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem("Standard Chess");
		rbMenuItem.setSelected(true);

		rbMenuItem.addActionListener(new Actions());
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Backwards Chess");
		rbMenuItem.setSelected(false);
		rbMenuItem.addActionListener(new Actions());

		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Displacement Chess");
		rbMenuItem.setSelected(false);
		rbMenuItem.addActionListener(new Actions());

		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Horde Chess");
		rbMenuItem.setSelected(false);
		rbMenuItem.addActionListener(new Actions());

		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Pawns Game");
		rbMenuItem.setSelected(false);
		rbMenuItem.addActionListener(new Actions());

		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Peasant's Revolt");
		rbMenuItem.setSelected(false);
		rbMenuItem.addActionListener(new Actions());

		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Weak!");
		rbMenuItem.setSelected(false);
		rbMenuItem.addActionListener(new Actions());

		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Keylay's Castle");
		rbMenuItem.setSelected(false);
		rbMenuItem.addActionListener(new Actions());

		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Serpant-Old Lady");
		rbMenuItem.setSelected(false);
		rbMenuItem.addActionListener(new Actions());

		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		menu = new JMenu("Online");
		menu.addMenuListener(new MenuActions());
		menu.setMnemonic(79);
		menu.getAccessibleContext().setAccessibleDescription("Online Options");
		menuBar.add(menu);

		menuItem = new JMenuItem("Host Game", 72);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(72, 2));
		menuItem.addActionListener(new Actions());
		menu.add(menuItem);

		menuItem = new JMenuItem("Join Game", 74);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(74, 2));
		menuItem.addActionListener(new Actions());
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Disconnect", 74);

		menuItem.addActionListener(new Actions());
		menu.add(menuItem);

		menu = new JMenu("Help");
		menu.setMnemonic(72);
		menu.getAccessibleContext().setAccessibleDescription("Online Options");
		menuBar.add(menu);

		menuItem = new JMenuItem("Display IP", 73);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(72, 2));
		menuItem.addActionListener(new Actions());
		menu.add(menuItem);

		menuItem = new JMenuItem("About", 65);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(74, 2));
		menuItem.addActionListener(new Actions());
		menu.add(menuItem);

		return menuBar;
	}
}
