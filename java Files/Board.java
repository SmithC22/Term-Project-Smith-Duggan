public class Board {

       private static final String empty = "~";
       private static final String hit = "X";
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
       
       public boolean hitEmptyBoard(int r, int c) {
    	   if(battleShipBoard[r][c].equals("~")) {
    		   battleShipBoard[r][c] = "X";
    		   System.out.println("Hit!");
    		   return true;
    	   }
    	   else {
    		   System.out.println("Miss!");
    		   return false;
    		   }
       }
       
       public boolean hit(int r, int c) {
    	   if(battleShipBoard[r][c].equals("S")) {
    		   battleShipBoard[r][c] = "X";
    		   System.out.println("Hit!");
    		   return true;
    	   }
    	   else {
    		   System.out.println("Miss!");
    		   return false;
    		   }
    	   }
      
       public boolean isHit(int r, int c) {
             if(battleShipBoard[r][c].equals("X")) {
                    return true;
             }
             else {
                    return false;
             }
       }
       
       public static void placeShip(int x1, int y1, int x2, int y2) {
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
       
       public static boolean checkShip(int x1, int y1, int x2, int y2, int size) {
    	   if (((x2 - x1)+1) == size) {
    		   return true;
    	   }
    	   else if (((y2 - y1)+1) == size) {
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

             b.checkShip(1, 2, 1, 6, 5);
             System.out.print(b.toString());
       }
}

 

