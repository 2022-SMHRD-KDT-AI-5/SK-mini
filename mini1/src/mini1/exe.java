package mini1;

public class exe {
	public static void main(String[] args) {
		SharedArea share = new SharedArea();
		insertThread ithread = new insertThread(share);
		printThread pthread = new printThread(share);
		
		ithread.start();
		pthread.start();
	}

}
