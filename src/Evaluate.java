import java.util.Arrays;

public class Evaluate {
	private Dictionary Dict;
	 // playing board
	//initiate variables
	char[][]  gameBoard;
	private int winNumber;
	private int sizeBoard;
	private char HUMANPLAYER = 'h';
	private char COMPUTER = 'c';
	private char EMPTY = 'e';
	
	
	public Evaluate(int size, int tilesToWin, int maxLevels) { //constructor
		this.sizeBoard = size; 
		this.winNumber = tilesToWin;//stores tilestoWin
		this.gameBoard = new char [size][size];//creates gameboard with size
		for(char[] row: gameBoard) {
			Arrays.fill(row, 'e'); //fill rows with e
		}
		
		}
	private int tilestowin() {
		return winNumber;
		
	}
			
	public Dictionary createDictionary() {//function that creates dictionasry
		this.Dict = new Dictionary(6007); //creates dictionary 
		return Dict;
	}
	public Record repeatedState(Dictionary dict) {
		Record flag = null;
		String gameString = "";
		for(int i = 0; i< gameBoard.length; i++) {//loops through row
			for (int j =0; j<gameBoard[i].length;j++) {//loops through columns
				gameString += gameBoard[j][i];// joins letters to form string
				
            }
			if (Dict.get(gameString) == null) {//if the string is null
				return flag;
			}
			else {
				flag =  Dict.get(gameString);//returns record object
			}
        }
		return flag;
        
    }
	public void insertState(Dictionary Dict, int score, int level) {//function that inserts record into dictionary
		//Record flag = null;
		String gameString = "";
		for(int i = 0; i< sizeBoard; i++) {
			for (int j =0; j<gameBoard[i].length;j++) { //loops through board from top to bottom and left to right
				gameString += gameBoard[j][i];//joins letters to make string 
				
            }
		
	}
		try {
		Record State = new Record(gameString, score, level); //creates new object 
		Dict.put(State);//puts state to dict
		}
		catch (DuplicatedKeyException e){
			//System.out.println("Key already in dictionary");//stops from same key being in dict
		}
		
			
	


	}	
	public void storePlay(int row, int col, char symbol) {//function that stores a move onto the board
		gameBoard[row][col] = symbol; // puts symbol in designated spot
		
	}
	public boolean squareIsEmpty(int row, int col) {//function to tell if tile is empty
		if( gameBoard[row][col] == 'e'){ //shows if empty tile
			return true;
			}
		else {
			return false; 
			}
		
	}
	public boolean tileOfComputer(int row, int col) {//function to tell if tile is computer
		if( gameBoard[row][col] == 'c'){//shows is tile is computer
			return true;
			}
		else {
			return false; 
			}
	}
	public boolean tileOfHuman(int row, int col) {//function to tell if tile is human
		if( gameBoard[row][col] == 'h'){//shows if tile is human 
			return true;
			}
		else {
			return false; 
			}
	}///function to check if game has been won
	public boolean wins (char Symbol) {
		
		//diagonal  method
int winCounter1 = 0;
		
for (int rand = 0; rand < sizeBoard - winNumber; rand++) {//loops through board
	int rowCheck = 0;
	int row1 = rand;
	for (int col1 = 0; row1 < sizeBoard && col1 < sizeBoard; row1++, col1++) {//loops through board
		if (gameBoard[row1][col1] == Symbol) {
			rowCheck++;
			if (rowCheck == winNumber) {//change
				return true;
			}
		}
		else {
			rowCheck = 0;
		}
	}
}
for (int rand= 0; rand < 2 * sizeBoard - 1; ++rand) {//loops through board
	int winCounter6 = 0;
    int number = (rand < sizeBoard) ? 0 : rand - sizeBoard + 1; //if not then
    for (int j = number; j <= rand - number; ++j) {//loop
    	if (gameBoard [j][rand-j] == Symbol) {
    		winCounter6++;//add to counter if true
    		if (winCounter6 == winNumber) {//if total is equal to winNumber... win
    			return true;
    		}
    	}
    	else {
    		winCounter6 = 0;//reset
    	}
    }
}
for (int i = 1; i < sizeBoard - winNumber; i++) {//loops through array
	int winCounter5 = 0;
	int col3 = i;
	for (int row3 = 0; row3 < sizeBoard && col3 < sizeBoard; row3++, col3++) {//loops 
		if (gameBoard[row3][col3] == Symbol) {//if equal to symbol
			winCounter5++;//increment 
			if (winCounter5 == winNumber) {//if total is equal
				return true;
			}
		}
		else {
			winCounter5 = 0;//reset
		}
	}
}

		// Check columns for win method
		
		
		for(int column3 = 0; column3< sizeBoard; column3 ++){//loop through board
			int winCounter3 = 0;
			for(int row3 = 0; row3 < sizeBoard; row3 ++) {
				if(gameBoard[row3][column3] == Symbol) {//if symbol
					winCounter3++ ;//increase count
					if(winCounter3 == tilestowin()){//if total is toal needed..., game won
						return true; 
				}	
				}
				else {
					winCounter3 = 0;//resets value
				}
			}
		}
		
		
		
		//check rows for win method
		
		for(int row4 = 0; row4 < sizeBoard; row4 ++){
			int winCounter4 = 0;
			for(int col4 = 0; col4 < sizeBoard; col4 ++) {
				if(gameBoard[row4][col4] == Symbol) {
					winCounter4++;
					if(winCounter4 == tilestowin()){//if total is toal needed..., game won
						return true;
						
						//stop finding and exit the for loop if the number of in line symbol is reached
					}
				}
				else {
					winCounter4 = 0;//resets value
				}
			}
		}
		return false;
		
	}
	
	public boolean isDraw() {//function to determine if game is a draw
		boolean flag = true;
		if(wins('c')) {//if computer won then draw is not possible
			flag = false;
		}
		if(wins('h')) {//if human won then draw is not possible
			flag = false;
		}
		
		else {
			for(int r=0; r<sizeBoard; r++) { //loops through board
				for(int c=0; c<sizeBoard; c++) {
					if(gameBoard[r][c] == 'e') { //if any spot is still empty -> game is not over
						flag = false;
					}
				}
			}
		}
		return flag;
	}
    public int evalBoard() {
    	if (wins('c') ){// if Computer won return true
    		return 3;
    }
    	else if (wins('h')){// if human won return true
    		return 0;
    }
    	else if (isDraw()) {// if a draw return true
    		return 2;
    		
    	}
    	else  {
    		return 1; //return if game is still ongoing
    }
		 
    }
		
}
