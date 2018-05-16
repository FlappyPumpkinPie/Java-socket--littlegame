package service;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import bean.ChessBoard;
import bean.ChessMan;
import bean.SocketEtc;
/**
 * 网络交互，读取命令结果
 * 服务端为黑子
 * @author yang
 *
 */
public class Service {
	private ServerSocket ss;
	private Socket socket;
	private ChessBoard c;
	private InputStream inputStream;
	private OutputStream outputStream;
	private static Service service;
	
	private Service() {
		try {
			this.ss = new ServerSocket(SocketEtc.port);
			this.socket = this.ss.accept();
			this.c = ChessBoard.getChessBoard();
			this.inputStream = socket.getInputStream();
			this.outputStream = socket.getOutputStream();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Service getService() {
		if (service == null)
			service = new Service();
		return service;
	}
	//从网络读取数组命令
	public int[] inputChess(){
		int[] chessPosition = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(inputStream);
			Object obj;
			while((obj = ois.readObject()) == null){}
			chessPosition = (int[])obj;
		} catch (Exception e) {}
		
		return chessPosition;
	}
	//对网络对手下棋
	public String setInput(){
		c.setChess(inputChess(), ChessMan.WHITE);
		return c.getChessBoardArray();
	}
	//下棋
	public String setOutput(int[] chessPosition){
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(outputStream);
			oos.writeObject(chessPosition);
		} catch (IOException e) {}
		
		c.setChess(chessPosition, ChessMan.BLACK);
		return c.getChessBoardArray();
	}
}
