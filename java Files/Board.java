public class Board {

       private static final String empty = "~";
       private static final String hit = "X";
       private static final String miss = "o";
       private static final String shipSpace = "S";
       private static final int numRows = 8;
       private static final int numCols = 8;
       private String[][] battleShipBoard;
       
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
       
       public void hitEmptyBoard(int r, int c) {
		   battleShipBoard[r][c] = hit;
	  
       }
   
       public void missEmptyBoard(int r, int c) {
	   battleShipBoard[r][c] = miss;
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
       
       public boolean checkShip(int x1, int y1, int x2, int y2, int size) {
    	   
    	   // checks if coordinates are correct size
    	   if (((x2 - x1)) == size-1 && (y1 == y2)) {
    		   return true;
    	   }
    	   else if (((y2 - y1)) == size-1 && (x1 == x2)) {
    		   return true;
    	   }
    	   else {
    		   return false;
    	   }
    	  
       }
       
       public boolean checkShipSpace(int x1, int y1, int x2, int y2) {
    	   // checks if a ship is already placed at coordinates
    	   if (x1 == x2) {
    		   int temp_y = y1;
    		   while (temp_y <= y2) {
    			  if (battleShipBoard[x1][y1] == "S") {
    			   return false;
    			  }
    			  temp_y++;
    		   }
    		   
    	   }
    	   else if (y1 == y2) {
    		   int temp_x = x1;
    		   while (temp_x <= x2) {
    			   if (battleShipBoard[x1][y1] == "S") {
    			   return false;
    			   }
    			   temp_x++;
    		   }
    		   
    	   } 
    		   return true;
    	   
       }
   
       public String toString() {
    	   String returnString  = "";
           System.out.println("   0   1   2   3   4   5   6   7");
           for (int i=0;i<numRows;i++) {
          	 	returnString += i+" ";
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
             System.out.println(b.toString());
             
             if (b.checkShip(0, 1, 4, 1, 5) && b.checkShipSpace(0, 1, 4, 1))
            	 b.placeShip(0, 1, 4, 1);
             
             System.out.print(b.toString());
       }
}

 

