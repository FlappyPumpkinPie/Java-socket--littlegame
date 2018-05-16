package bean;

public enum ChessMan {
	BLACK(" ●"),WHITE(" ○");
	
	private String chess;
	
	private ChessMan(String chess){
		this.chess = chess;
	}
	public String getChess(){
		return chess;
	}
}
