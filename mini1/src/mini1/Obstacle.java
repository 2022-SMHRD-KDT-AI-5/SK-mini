package mini1;

import java.util.Random;

public class Obstacle {
	int x;
	int y;
	char c;
	
	public Obstacle() {
		Random rd = new Random();
		x = rd.nextInt(30);
		y = 0;
		c = '|';
	}
	
	void fall() {
		y += 1;
	}
}
