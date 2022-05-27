package mini1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class exe {
	public static void main(String[] args) {
		SharedArea share = new SharedArea();
		OracleDB db = new OracleDB();
		Loops loop = new Loops(db, share);
		loop.start();
	}
}
