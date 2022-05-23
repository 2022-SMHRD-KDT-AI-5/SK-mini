package mini1;

public class Me {
	int x;
	int y;
	char c;
	
	public Me() {
		x = 15; // col
		y = 14; // row
		c = 'O';
	}
	
	void left() {
		if (x >= 1)
			x -= 1;
	}
	
	void right() {
		if (x <= 28)
			x += 1;
	}
}
