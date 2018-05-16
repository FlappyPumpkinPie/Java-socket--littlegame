package bean;

import java.util.ArrayList;

/**
 * 初始化棋盘，下棋以及判断输赢
 * @author yang
 *
 */
public class ChessBoard {
	private  ArrayList<String> chessBoardArray;
	private static ChessBoard chessBoard;
	private static final int BOARD_SIZE = 9;
	
	private ChessBoard(){
		chessBoardArray = new ArrayList<>();
		
		for (int i = 0; i < BOARD_SIZE + 1; i++) {
			if (i == 0) {
				chessBoardArray.add("  ");
			}else{
				chessBoardArray.add(" " + i);
			}
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (i == 0) {
					chessBoardArray.add(" " + (j + 1));
				}else{
					chessBoardArray.add(" ░");
				}
			}
			chessBoardArray.add("\r\n");
			
		}
	}
	
	public synchronized static ChessBoard getChessBoard(){
		if(chessBoard == null)
			chessBoard = new ChessBoard();
		return chessBoard;
	}
	/**
	 * 获得棋牌信息
	 * @return 棋盘
	 */
	public String getChessBoardArray() {
		StringBuilder sb = new StringBuilder();
		for (String string : chessBoardArray) {
			sb.append(string);
		}
		return sb.toString();
	}
	/**
	 * 返回数组下标
	 * @param 位置坐标
	 * @return 数组下标
	 */
	private int getIndex(int[] position){
		return position[0] * (BOARD_SIZE + 2) + position[1];
	}
	/**
	 * 下棋
	 * @param 坐标
	 * @param 棋子
	 */
	public void setChess(int[] position, ChessMan chess){
		if(isThisChess(position[0], position[1], ChessMan.BLACK) 
				|| isThisChess(position[0], position[1], ChessMan.WHITE)){
			return;
		}
		chessBoardArray.set(getIndex(position), chess.getChess());
		this.isWin(position, chess);
	}
	/**
	 * 判断输赢
	 * @param 坐标数组
	 * @param 棋子
	 */
	private void isWin(int[] position, ChessMan chess) {
		int sum = 0;
		//检查竖向
		for (int i = position[0]; i < BOARD_SIZE; i++) {
			if(isThisChess(i, position[1], chess)){
				sum++;
			}else{
				break;
			}
		}
		for (int i = position[0] - 1; i > 0; i--) {
			if(isThisChess(i, position[1], chess)){
				sum++;
			}else{
				break;
			}
		}
		if (sum >= 5){
			throw new RuntimeException(chess.name() + "获得胜利");
		}
		
		//检查横向
		sum = 0;
		for (int i = position[1]; i < BOARD_SIZE; i++) {
			if(isThisChess(position[0], i, chess)){
				sum++;
			}else{
				break;
			}
		}
		for (int i = position[1] - 1; i > 0; i--) {
			if(isThisChess(position[0], i, chess)){
				sum++;
			}else{
				break;
			}
		}
		if (sum >= 5){
			throw new RuntimeException(chess.name() + "获得胜利");
		}
		
		//检查斜右下
		sum = 0;
		for (int i = position[0], j = position[1]; i < BOARD_SIZE && j < BOARD_SIZE; i++, j++) {
			if(isThisChess(i, j, chess)){
				sum++;
			}else{
				break;
			}
		}
		for (int i = position[0] - 1, j = position[1] - 1;
				i > 0 && j > 0;
				i--, j--) {
			if(isThisChess(i, j, chess)){
				sum++;
			}else{
				break;
			}
		}
		if (sum >= 5){
			throw new RuntimeException(chess.name() + "获得胜利");
		}
		
		//检查斜右上
		sum = 0;
		for (int i = position[0], j = position[1]; i < BOARD_SIZE && j > 0; i++, j--) {
			if(isThisChess(i, j, chess)){
				sum++;
			}else{
				break;
			}
		}
		for (int i = position[0] - 1, j = position[1] + 1;
				i > 0 && j < BOARD_SIZE;
				i--, j++) {
			if(isThisChess(i, j, chess)){
				sum++;
			}else{
				break;
			}
		}
		if (sum >= 5){
			throw new RuntimeException(chess.name() + "获得胜利");
		}
	}
	/**
	 * 判断是否是相同棋子
	 * @param 横坐标
	 * @param 纵向坐标
	 * @param 棋子
	 * @return 是否相同
	 */
	private boolean isThisChess(int x, int y, ChessMan chess){
		return chess.getChess().equals(chessBoardArray.get(getIndex(new int[]{x,y})));
	}

	public static int getBoardSize() {
		return BOARD_SIZE;
	}
}
