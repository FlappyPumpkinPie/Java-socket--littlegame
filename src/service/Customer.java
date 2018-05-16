package service;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import bean.ChessBoard;
import bean.ChessMan;
import bean.SocketEtc;
/**
 * 客户端，白棋
 * @author yang
 *
 */
public class Customer {
	private Socket socket;
	private ChessBoard c;
	private InputStream inputStream;
	private OutputStream outputStream;
	private static Customer customer;
	
	private Customer() {
		try {
			this.socket = new Socket(InetAddress.getByName(SocketEtc.ip), SocketEtc.port);
			this.c = ChessBoard.getChessBoard();
			this.inputStream = socket.getInputStream();
			this.outputStream = socket.getOutputStream();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Customer getCustomer() {
		if (customer == null)
			customer = new Customer();
		return customer;
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
		c.setChess(inputChess(), ChessMan.BLACK);
		return c.getChessBoardArray();
	}
	//下棋
	public String setOutput(int[] chessPosition){
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(outputStream);
			oos.writeObject(chessPosition);
		} catch (IOException e) {}
		
		c.setChess(chessPosition, ChessMan.WHITE);
		return c.getChessBoardArray();
	}
}

