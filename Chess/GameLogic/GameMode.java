 package GameLogic;
 
 public class GameMode {
   private static int x;
   private static int y;
   private static int mode;
   private static String modeName;
   
   public static void changeMode(int value) { Chess.game.changeSize(value);
     switch (value) {
     case 1: 
       x = 8;
       y = 8;
       mode = 1;
       modeName = "Standard Chess";
       
       break;
     case 2: 
       x = 10;
       y = 10;
       mode = 2;
       modeName = "Big Bang Theory Chess";
       break;
     case 3: 
       x = 8;
       y = 14;
       mode = 3;
       modeName = "Dencker Chess";
       break;
     case 4: 
       x = 8;
       y = 8;
       mode = 4;
       modeName = "Chess960";
       break;
     case 5: 
       x = 8;
       y = 8;
       mode = 5;
       modeName = "Displacement Chess";
       break;
     case 6: 
       x = 8;
       y = 8;
       mode = 6;
       modeName = "Backwards Chess";
     }
   }
   
   public static int getX()
   {
     return x;
   }
   
   public static int getY() {
     return y;
   }
   
   public static int getMode() {
     return mode;
   }
   
   public static String getModeName() {
     return modeName;
   }
   
   public static void setDefaultGame() {
     x = 8;
     y = 8;
     mode = 1;
     modeName = "Standard Chess";
   }
   
   public static boolean isInBounds(int xValue, int yValue) {
     if ((xValue < x) && (xValue > -1) && (yValue < y) && (yValue > -1)) return true;
     return false;
   }
 }


