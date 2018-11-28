 package NET;
 
 import GameLogic.Chess;
 import GameLogic.GameBoard;
 import GameLogic.GameMode;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 import java.io.PrintWriter;
 import java.net.ServerSocket;
 import java.net.Socket;
 import java.util.Scanner;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class Server
   implements Runnable
 {
   private ServerSocket serverSocket;
   private Socket clientSocket;
   private Thread t;
   private boolean connected;
   
   public Server()
   {
     this.t = new Thread(this);
     
     this.t.start();
     
     System.out.println("Server Console");
   }
   
 
 
   public void start()
     throws IOException
   {}
   
 
 
   public void run()
   {
     IPAddress ip = new IPAddress();
     
     this.serverSocket = null;
     try {
       this.serverSocket = new ServerSocket(5239);
     } catch (IOException e) {
       System.err.println("Could not listen on port: 5239.");
       System.exit(1);
     }
     
     this.clientSocket = null;
     try {
       this.clientSocket = this.serverSocket.accept();
     } catch (IOException e) {
       System.err.println("Accept failed.");
       System.exit(1);
     }
     try {
       GameBoard.newGame(GameMode.getMode());
     } catch (IOException e) {
       System.err.println("Could not create a new game");
     }
     this.connected = true;
     GameBoard.isOnline = true;
     
     
     try
     {
       do
       {
         PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream(), true);
         
         BufferedReader in = new BufferedReader(
           new InputStreamReader(
           this.clientSocket.getInputStream()));
         
 
         sendMessage(Chess.verison);
         String inputLine = in.readLine();
         if (!inputLine.equals(Chess.verison)) {
           System.out.println("Verison Mismatch");
         }
         String outputLine = ""+GameMode.getMode();
         sendMessage(outputLine);
         try
         {
           while ((inputLine = in.readLine()) != null) {
             if (GameBoard.turn == 2) {
               System.out.println("Client: " + inputLine);
               Scanner scan = new Scanner(inputLine);
               
               Chess.board.chessMove(scan.nextInt(), scan.nextInt());
               
 
 
               if (inputLine.equals("Checkmate")) {
                 this.connected = false;
                 GameBoard.isOnline = false;
                 break;
               }
             }
           }
         }
         catch (IOException e) {
           this.connected = false;
           GameBoard.isOnline = false;
           System.out.println("Client Disconnected");
         }
       } while (this.connected);
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
     }
     catch (IOException e1)
     {
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
       e1.printStackTrace();
     }
     
 
 
     try
     {
       label321:
       
 
 
       this.clientSocket.close();
       this.serverSocket.close();
     }
     catch (IOException e) {
       e.printStackTrace();
     }
   }
   
   public void sendMessage(String message) throws IOException
   {
     PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream(), true);
     out.println(message);
     System.out.println("Server: " + message);
   }
 }


