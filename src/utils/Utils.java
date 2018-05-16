package utils;

import java.util.Scanner;

import bean.ChessBoard;

public class Utils {
	private static Scanner input = new Scanner(System.in);
	
	public static String readKeyBoard(int length, boolean blankReturn) {
		String str = "";
		while (input.hasNext()) {
			str = input.nextLine();
			if (str.length() == 0){
				if (blankReturn)
					return str;
				continue;
			}
			if (str.length() > length || str.length() < 1){
				System.out.print("输入长度（不大于" + length + "）错误，请重新输入：");
				continue;
			}
			break;
		}
		return str;
	}
	
	public static char readChar() {
		return readKeyBoard(1, false).charAt(0);
	}
	
	public static char readConfirmSelection() {
		char confirm;
		while ((confirm = Character.toUpperCase(readChar())) != 'Y' && confirm != 'N') {
			System.out.print("输入错误，请重新输入:");
		}
		return confirm;
	}
	
	public static int[] readPosition() {
		String str;
		int[] position = new int[2];
		while (true) {
			str = readKeyBoard(10, false);
			try {
				String[] split = str.split(",");
				if (split.length != 2)
					throw new RuntimeException();
				position[0] = Integer.parseInt(split[0].trim());
				position[1] = Integer.parseInt(split[1].trim());

				for (int i = 0; i < position.length; i++) {
					if(position[i] < 1 || position[i] > ChessBoard.getBoardSize()){
						throw new RuntimeException();
					}
				}
				
				break;
			} catch (Exception e) {
				System.out.println("输入坐标格式错误(例：x, y)，请重新输入：");
				continue;
			}
		}
		return position;
	}
}
