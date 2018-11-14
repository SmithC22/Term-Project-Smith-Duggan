public class Board {

       private static final String empty = "~";
       private static final String hit = "x";
       private static final String miss = "o";
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
             System.out.print(b.toString());
       }
}

 

