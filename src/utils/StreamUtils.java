package utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtils {
	public static byte[] streamToByteArray(InputStream is) throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int len;
		byte[] b = new byte[1024];
		while((len = is.read(b)) != -1){
			baos.write(b, 0, len);
		}
		
		baos.close();
		return baos.toByteArray();
	}
	public static String streamToStringLoop(InputStream is) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String str;
		while((str = br.readLine()) == null){}
		return str;
	}
	public static String streamToString(InputStream is) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String str;
		StringBuilder sb = new StringBuilder();
		while((str = br.readLine()) != null){
			sb.append(str);
		}
		return sb.toString();
	}
}
