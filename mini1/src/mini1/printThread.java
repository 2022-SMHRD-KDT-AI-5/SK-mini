package mini1;

import java.util.ArrayList;


public class printThread extends Thread{
SharedArea share;
	
	public printThread(SharedArea share) {
		this.share = share;
	}
	
	void update() {
		for (int i=0; i<share.obsArr.size(); i++) {
			share.obsArr.get(i).fall();
			if (share.obsArr.get(i).y > 14) {
				share.obsArr.remove(i);
			int y = share.obsArr.get(i).y;
			int x = share.obsArr.get(i).x;
			
			}
		}
		share.init();
		for (int i=0; i<share.obsArr.size(); i++) {
			int y = share.obsArr.get(i).y;
			int x = share.obsArr.get(i).x;
			for (int j=0; j<share.obsArr.size(); j++) {
				share.background[y][x] = share.obsArr.get(i).c;
			}
		}
		
	}
	
	public void run() {
		while(true) {
			synchronized(share) {
				update();
				System.out.println("-----------------------------");
				for (int i=0; i<share.background.length; i++) {
					for (int j=0; j<share.background[0].length; j++) {
						System.out.print(share.background[i][j]);
					}System.out.println();
				}System.out.println("-----------------------------");
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
