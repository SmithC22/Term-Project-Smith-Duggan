public class Board {

       private static final String empty = "~";
       private static final String hit = "x";
       private static final String miss = "o";
       private static final String shipSpace = "S";
       private static final int numRows = 10;
       private static final int numCols = 10;
       private static String[][] battleShipBoard;
       
       public Board() {
             battleShipBoard = new String[numRows][numCols];
       }

       public void fillEmpty() {
             for(int i = 0; i<numRows; i++) {
                    for(int j = 0;j<numCols;j++) {
                           battleShipBoard[i][j] = empty;
                    }
             }
       }
      
       public boolean isHit(int r, int c) {
             if(battleShipBoard[r][c].equals("x")) {
                    return true;
             }
             else {
                    return false;
             }
       }
       
       public void placeShip(int x1, int y1, int x2, int y2) {
    	   if (x1 == x2) {
    		   while (y1 <= y2) {
    			   battleShipBoard[x1][y1] = shipSpace;
    			   y1++;
    		   }
    	   }
    	   else if (y1 == y2) {
    		   while (x1 <= x2) {
    			   battleShipBoard[x1][y1] = shipSpace;
    			   x1++;
    		   }
    	   }
       }
       
       public static boolean placeDestroyer(int x1, int y1, int x2, int y2, Board b) {
    	   if (((x2 - x1)+1) == 2) {
    		   b.placeShip(x1,y1,x2,y2);
    		   return true;
    	   }
    	   else if (((y2 - y1)+1) == 2) {
    		   b.placeShip(x1,y1,x2,y2);
    		   return true;
    	   }
    	   else {
    		   return false;
    	   }
    	   
       }
       
       public static boolean placeSubmarine(int x1, int y1, int x2, int y2, Board b) {
    	   if (((x2 - x1)+1) == 3) {
    		   b.placeShip(x1,y1,x2,y2);
    		   return true;
    	   }
    	   else if (((y2 - y1)+1) == 3) {
    		   b.placeShip(x1,y1,x2,y2);
    		   return true;
    	   }
    	   else {
    		   return false;
    	   }
       }
       
       public static boolean placeCruiser(int x1, int y1, int x2, int y2, Board b) {
    	   if (((x2 - x1)+1) == 3) {
    		   b.placeShip(x1,y1,x2,y2);
    		   return true;
    	   }
    	   else if (((y2 - y1)+1) == 3) {
    		   b.placeShip(x1,y1,x2,y2);
    		   return true;
    	   }
    	   else {
    		   return false;
    	   }
       }
       
       public static boolean placeBattleship(int x1, int y1, int x2, int y2, Board b) {
    	   if (((x2 - x1)+1) == 4) {
    		   b.placeShip(x1,y1,x2,y2);
    		   return true;
    	   }
    	   else if (((y2 - y1)+1) == 4) {
    		   b.placeShip(x1,y1,x2,y2);
    		   return true;
    	   }
    	   else {
    		   return false;
    	   }
       }
       
       public static boolean placeCarrier(int x1, int y1, int x2, int y2, Board b) {
    	   if (((x2 - x1)+1) == 5) {
    		   b.placeShip(x1,y1,x2,y2);
    		   return true;
    	   }
    	   else if (((y2 - y1)+1) == 5) {
    		   b.placeShip(x1,y1,x2,y2);
    		   return true;
    	   }
    	   else {
    		   return false;
    	   }
       }
   
       public String toString() {
             String returnString  = "";
             for (int i=0;i<numRows;i++) {
                    for (int j = 0;j<numCols; j++) {
                           returnString += "[" + battleShipBoard[i][j] + "] ";
                    }
                    returnString += "\n";
             }
             return returnString;
       }
 
       public static void main(String[] args) {
             Board b = new Board();
             b.fillEmpty();
             placeCarrier(1, 2, 1, 6, b);
             System.out.print(b.toString());
       }
}

 

