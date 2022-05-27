package mini1;

import java.util.ArrayList;


public class printThread extends Thread{
SharedArea share;
	
	public printThread(SharedArea share) {
		this.share = share;
	}
	
	boolean check(Obstacle obs) {
		if (obs.x == share.me.x && obs.y == share.me.y) {
			return false;
		}
		else return true;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
	}
	
	
	void update() {
		for (int i=0; i<share.obsArr.size(); i++) {
			share.obsArr.get(i).fall();
			if(!check(share.obsArr.get(i))) {
				share.isOn = false;
				break;
			}
			if (share.obsArr.get(i).y > 14) {
				share.obsArr.remove(i);
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
		int x = share.me.x;
		share.background[14][x] = share.me.c;
		
	}
	
	public void run() {
		while(share.isOn) {
			synchronized(share) {
				System.out.println("--------------------");
				for (int i=0; i<share.background.length; i++) {
					for (int j=0; j<share.background[0].length; j++) {
						System.out.print(share.background[i][j]);
					}System.out.println();
				}System.out.println("--------------------");
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}System.out.println("GAME OVER");
	}

}
