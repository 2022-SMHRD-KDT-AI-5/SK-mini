package mini1;

public class updateThread extends Thread {
	SharedArea share;

	public updateThread(SharedArea share) {
		this.share = share;
	}

	boolean check(Obstacle obs) {
		if (obs.x == share.me.x && obs.y == share.me.y) {
			return false;
		} else
			return true;
	}

	public void run() {
		while (share.isOn) {
			synchronized (share) {
				for (int i = 0; i < share.obsArr.size(); i++) {
					share.obsArr.get(i).fall();
					if (!check(share.obsArr.get(i))) {
						share.isOn = false;
						break;
					}
					if (share.obsArr.get(i).y > 14) {
						share.obsArr.remove(i);
					}
				}
				share.init();
				for (int i = 0; i < share.obsArr.size(); i++) {
					int y = share.obsArr.get(i).y;
					int x = share.obsArr.get(i).x;
					for (int j = 0; j < share.obsArr.size(); j++) {
						share.background[y][x] = share.obsArr.get(i).c;
					}
				}
				int x = share.me.x;
				share.background[14][x] = share.me.c;
			}
			try {
				Thread.sleep(1000 - 100*(share.level));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
