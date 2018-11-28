 package graphics;
 
 import GameLogic.Actions;
 import java.awt.BorderLayout;
 import java.awt.Container;
 import javax.accessibility.AccessibleContext;
 import javax.swing.ButtonGroup;
 import javax.swing.JMenu;
 import javax.swing.JMenuBar;
 import javax.swing.JMenuItem;
 import javax.swing.JPanel;
 import javax.swing.JRadioButtonMenuItem;
 import javax.swing.JScrollPane;
 import javax.swing.JTextArea;
 import javax.swing.KeyStroke;
 
 
 public class menu
 {
   public static JMenuBar MainMenuBar()
   {
     JMenuBar menuBar = new JMenuBar();
     
 
 
 
     JMenu menu = new JMenu("File");
     menu.setMnemonic(65);
     menu.getAccessibleContext().setAccessibleDescription(
       "Creates, saves, and opens games.");
     menuBar.add(menu);
     
     JMenuItem menuItem = new JMenuItem("New Game", 78);
     menuItem.setAccelerator(KeyStroke.getKeyStroke(
       78, 2));
     menuItem.addActionListener(new Actions());
     menu.add(menuItem);
     
     menuItem = new JMenuItem("Open Game", 79);
     menuItem.setAccelerator(KeyStroke.getKeyStroke(79, 2));
     menuItem.addActionListener(new Actions());
     menu.add(menuItem);
     
 
     menuItem = new JMenuItem("Save Game", 83);
     menuItem.setAccelerator(KeyStroke.getKeyStroke(83, 2));
     menuItem.addActionListener(new Actions());
     menu.add(menuItem);
     
     menu.addSeparator();
     
     menuItem = new JMenuItem("Close");
     menuItem.setAccelerator(KeyStroke.getKeyStroke(115, 8));
     menuItem.addActionListener(new Actions());
     menu.add(menuItem);
     
 
     menu = new JMenu("Game Mode");
     menu.setMnemonic(71);
     menu.getAccessibleContext().setAccessibleDescription(
       "Changes the game mode");
     menuBar.add(menu);
     
 
     ButtonGroup group = new ButtonGroup();
     
     JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem("Standard Chess");
     rbMenuItem.setSelected(true);
     
     rbMenuItem.addActionListener(new Actions());
     group.add(rbMenuItem);
     menu.add(rbMenuItem);
     
     menu.addSeparator();
     
     rbMenuItem = new JRadioButtonMenuItem("Backwards Chess");
     rbMenuItem.setSelected(false);
     rbMenuItem.addActionListener(new Actions());
     
     group.add(rbMenuItem);
     menu.add(rbMenuItem);
     
     rbMenuItem = new JRadioButtonMenuItem("Chess960");
     rbMenuItem.setSelected(false);
     rbMenuItem.addActionListener(new Actions());
     
     group.add(rbMenuItem);
     menu.add(rbMenuItem);
     
     rbMenuItem = new JRadioButtonMenuItem("Displacement Chess");
     rbMenuItem.setSelected(false);
     rbMenuItem.addActionListener(new Actions());
     
     group.add(rbMenuItem);
     menu.add(rbMenuItem);
     
     menu.addSeparator();
     
     rbMenuItem = new JRadioButtonMenuItem("Keylay's Castle");
     rbMenuItem.setSelected(false);
     rbMenuItem.addActionListener(new Actions());
     
     group.add(rbMenuItem);
     menu.add(rbMenuItem);
     
     menu.addSeparator();
     
     rbMenuItem = new JRadioButtonMenuItem("Serpant-Old Lady");
     rbMenuItem.setSelected(false);
     rbMenuItem.addActionListener(new Actions());
     
     group.add(rbMenuItem);
     menu.add(rbMenuItem);
     
     menu = new JMenu("Online");
     menu.setMnemonic(79);
     menu.getAccessibleContext().setAccessibleDescription(
       "Online Options");
     menuBar.add(menu);
     
     menuItem = new JMenuItem("Host Game", 72);
     menuItem.setAccelerator(KeyStroke.getKeyStroke(
       72, 2));
     menuItem.addActionListener(new Actions());
     menu.add(menuItem);
     
     menuItem = new JMenuItem("Join Game", 74);
     menuItem.setAccelerator(KeyStroke.getKeyStroke(74, 2));
     menuItem.addActionListener(new Actions());
     menu.add(menuItem);
     
     menu = new JMenu("Help");
     menu.setMnemonic(72);
     menu.getAccessibleContext().setAccessibleDescription(
       "Online Options");
     menuBar.add(menu);
     
     menuItem = new JMenuItem("Display IP", 73);
     menuItem.setAccelerator(KeyStroke.getKeyStroke(
       72, 2));
     menuItem.addActionListener(new Actions());
     menu.add(menuItem);
     
     menuItem = new JMenuItem("About", 65);
     menuItem.setAccelerator(KeyStroke.getKeyStroke(74, 2));
     
     menu.add(menuItem);
     
     return menuBar;
   }
   
   public static Container contentContainer()
   {
     JPanel contentPane = new JPanel(new BorderLayout());
     contentPane.setOpaque(true);
     
 
     JTextArea output = new JTextArea(5, 30);
     output.setEditable(false);
     JScrollPane scrollPane = new JScrollPane(output);
     
 
     contentPane.add(scrollPane, "Center");
     
     return contentPane;
   }
 }


