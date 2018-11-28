 package NET;
 
 import javax.swing.JFrame;
 
 
 
 
 
 public class IPAddress
   implements Runnable
 {
   public static JFrame frame;
   
   public IPAddress()
   {
     Thread t = new Thread(this);
     t.start();
   }
   
 
 
   public void run() {}
   
 
 
   public static void main(String[] arg)
   {
     IPAddress ip = new IPAddress();
   }
 }


