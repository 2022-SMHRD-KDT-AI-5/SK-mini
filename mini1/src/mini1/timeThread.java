package mini1;

public class timeThread extends Thread{
	SharedArea share;
	
	public timeThread(SharedArea share) {
		this.share = share;
	}
	
	public void run() {
		
		while(share.isOn) {
			try {
				Thread.sleep(5000); // 10sec
				if (share.level <= 5) 
					share.level += 1;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}

}
