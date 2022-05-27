package mini1;

import java.util.ArrayList;
import java.util.Arrays;

public class SharedArea {
	ArrayList<Obstacle> obsArr;
	Me me;
	char[][] background;
	int score;
	boolean isOn;
	int level;

	public SharedArea() {
		level = 1;
		score = 0;
		isOn = true;
		obsArr = new ArrayList();
		me = new Me();
		background = new char[15][20];
		for (int i = 0; i < background.length; i++)
			Arrays.fill(background[i], ' ');
	}

	void init() {
		for (int i = 0; i < background.length; i++)
			Arrays.fill(background[i], ' ');
	}
	
	void reset() {
		level = 1;
		score = 0;
		isOn = true;
		obsArr = new ArrayList();
		me = new Me();
		for (int i = 0; i < background.length; i++)
			Arrays.fill(background[i], ' ');
	}
}
