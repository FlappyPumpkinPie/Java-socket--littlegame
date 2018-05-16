package view;

import service.Service;
import utils.Utils;
/**
 * 服务端开始
 * @author yang
 *
 */
public class ViewService {
	public static void main(String[] args) {
		Service service = Service.getService();
		Thread serIn = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						System.out.println(service.setInput());
					} catch (Exception e) {
						System.out.println(e.getMessage());
						break;
					}
				}
			}
		});
		
		Thread serOut = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						System.out.println(service.setOutput(Utils.readPosition()));
					} catch (Exception e) {
						System.out.println(e.getMessage());
						break;
					}
				}
			}
		});
		
		serIn.start();
		serOut.start();
	}
}
