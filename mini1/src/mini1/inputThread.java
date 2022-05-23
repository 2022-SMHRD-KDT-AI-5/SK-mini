package mini1;

import java.util.Scanner;

public class inputThread extends Thread{
	SharedArea share;
	Scanner sc;
	
	public inputThread(SharedArea share) {
		this.share = share;
		sc = new Scanner(System.in);
	}
	
	public void run() {
		while (share.isOn) {
			String in = sc.next();
			if (in.equals("d")) {
				share.me.right();
			}
			else if (in.equals("a")) {
				share.me.left();
				
			}try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		}sc.close();
	}
}
