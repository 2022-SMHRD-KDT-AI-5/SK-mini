package mini1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class exe {
	public static void main(String[] args) {
		SharedArea share = new SharedArea();
		insertThread isthread = new insertThread(share);
		printThread pthread = new printThread(share);
		inputThread ipthread = new inputThread(share);
		
		isthread.start();
		pthread.start();
		ipthread.start();
		
	}
}
