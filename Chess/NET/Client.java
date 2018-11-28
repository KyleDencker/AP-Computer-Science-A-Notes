 package NET;
 
 import GameLogic.Chess;
 import GameLogic.GameBoard;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 import java.io.PrintWriter;
 import java.net.Socket;
 import java.net.UnknownHostException;
 import java.util.Scanner;
 import javax.swing.JOptionPane;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class Client
   implements Runnable
 {
   private Socket kkSocket = null;
   private Thread t;
   public static boolean connected = false;
   
   public Client() {
     try {
       String ip = JOptionPane.showInputDialog(null, "Enter the IP address you would like to connect to: ", 
         "localhost", 1);
       if (ip != null) {
         this.kkSocket = new Socket(ip, 5239);
         BufferedReader in = new BufferedReader(new InputStreamReader(this.kkSocket.getInputStream()));
         Scanner scan = new Scanner(in.readLine());
         if (!scan.nextLine().equals(Chess.verison)) {
           System.out.println("Version Mismatch");
         }
         sendMessage(Chess.verison);
         scan = new Scanner(in.readLine());
         GameBoard.newGame(scan.nextInt());
       }
     }
     catch (UnknownHostException e) {
       System.err.println("Don't know about host: taranis.");
       System.exit(1);
     } catch (IOException e) {
       System.err.println("Couldn't get I/O for the connection to the server");
       System.exit(1);
     }
     
     connected = true;
     GameBoard.isOnline = true;
     this.t = new Thread(this);
     this.t.start();
     System.out.println("Client Console");
   }
   
   public void run()
   {
     try
     {
       PrintWriter out = null;
       BufferedReader in = null;
       
       out = new PrintWriter(this.kkSocket.getOutputStream(), true);
       
       in = new BufferedReader(new InputStreamReader(this.kkSocket.getInputStream()));
       
 
       String inputLine;
       
       while ((inputLine = in.readLine()) != null) { 
         if (GameBoard.turn == 1) {
           System.out.println("Server: " + inputLine);
           Scanner scan = new Scanner(inputLine);
           if (inputLine.equals("Checkmate")) {
             connected = false;
             GameBoard.isOnline = false;
             break;
           }
           Chess.board.chessMove(scan.nextInt(), scan.nextInt());
         }
         
       }
       
 
     }
     catch (IOException e)
     {
 
       connected = false;
       GameBoard.isOnline = false;
       System.out.println("Server Disconnected");
     }
   }
   
   public void sendMessage(String message)
     throws IOException
   {
     PrintWriter out = new PrintWriter(this.kkSocket.getOutputStream(), true);
     out.println(message);
     System.out.println("Client: " + message);
   }
 }


