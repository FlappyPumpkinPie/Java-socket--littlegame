package view;

import service.Customer;
import utils.Utils;
/**
 * 客户端开始
 * @author yang
 *
 */
public class ViewCustomer {
	public static void main(String[] args) {
		Customer customer = Customer.getCustomer();
		Thread customerIn = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						System.out.println(customer.setInput());
					} catch (Exception e) {
						System.out.println(e.getMessage());
						break;
					}
				}
			}
		});
		
		Thread customerOut = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						System.out.println(customer.setOutput(Utils.readPosition()));
					} catch (Exception e) {
						System.out.println(e.getMessage());
						break;
					}
				}
			}
		});
		
		customerIn.start();
		customerOut.start();
	}
}
