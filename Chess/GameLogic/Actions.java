 package GameLogic;
 
 import NET.Client;
 import NET.Server;
 import graphics.PopUps;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.io.IOException;
 
 
 
 
 
 
 
 
 
 public class Actions
   implements ActionListener
 {
   public void actionPerformed(ActionEvent e)
   {
     if (e.getActionCommand().equals("New Game")) {
       try {
         PopUps.newGame();
       }
       catch (IOException e1) {
         e1.printStackTrace();
       }
     } else if (e.getActionCommand().equals("Open Game")) {
       PopUps.openGame();
     } else if (e.getActionCommand().equals("Save Game")) {
       PopUps.saveGame();
     } else if (e.getActionCommand().equals("Close")) {
       Chess.game.close();
     } else if (e.getActionCommand().equals("Standard Chess")) {
       try {
         PopUps.GameMode(1);
       }
       catch (IOException e1) {
         e1.printStackTrace();
       }
     } else if (e.getActionCommand().equals("Serpant-Old Lady")) {
       try {
         PopUps.GameMode(2);
       }
       catch (IOException e1) {
         e1.printStackTrace();
       }
     } else if (e.getActionCommand().equals("Keylay's Castle")) {
       try {
         PopUps.GameMode(3);
       }
       catch (IOException e1) {
         e1.printStackTrace();
       }
     } else if (e.getActionCommand().equals("Chess960")) {
       try {
         PopUps.GameMode(4);
       }
       catch (IOException e1) {
         e1.printStackTrace();
       }
     } else if (e.getActionCommand().equals("Displacement Chess")) {
       try {
         PopUps.GameMode(5);
       }
       catch (IOException e1) {
         e1.printStackTrace();
       }
     } else if (e.getActionCommand().equals("Backwards Chess")) {
       try {
         PopUps.GameMode(6);
       }
       catch (IOException e1) {
         e1.printStackTrace();
       }
     } else if (e.getActionCommand().equals("Host Game"))
     {
       GameBoard.server = new Server();
     }
     else if (e.getActionCommand().equals("Join Game")) {
       GameBoard.client = new Client();
     } else if (e.getActionCommand().equals("Display IP")) {
       PopUps.displayIP();
     }
   }
 }


