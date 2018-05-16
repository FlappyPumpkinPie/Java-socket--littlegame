package bean;
import static utils.Utils.*;

public class Test {
	public static void main(String[] args) {
		ChessBoard c = ChessBoard.getChessBoard();
		while(true){
			c.setChess(readPosition(), ChessMan.BLACK);
			System.out.println(c.getChessBoardArray());
		}
		
	}
}
