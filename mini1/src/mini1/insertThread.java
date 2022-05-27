package mini1;

public class insertThread extends Thread{
	SharedArea share;
	
	public insertThread(SharedArea share) {
		this.share = share;
	}
	
	public void run() {
		while(share.isOn) {
			Obstacle obs = new Obstacle();
			synchronized(share) {
				share.obsArr.add(obs);
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
