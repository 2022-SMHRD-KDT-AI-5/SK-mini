package Server;


import java.util.Arrays;
import java.util.Random;

public class word_update extends Thread{
	SharedArea share;
	
	
	public word_update(SharedArea share) {
		super();
		this.share = share;
	}

	void print() {
		System.out.println("\n==================================================");
		for(int i=0; i<share.row; i++) {
			for (int j=0; j<share.column; j++) {
				System.out.print(share.back[i][j]);
			}System.out.println();
		}
		System.out.println("▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");
	}
	
	public void run() {
		try {
			while(share.isOn) {
				synchronized(share) {
					print();
				}
				Thread.sleep(100);
			}
		} catch (Exception e) {
		}
	}
}

class backUpdate extends Thread {
	SharedArea share;
	Random rd;
	
	public backUpdate(SharedArea share) {
		this.share = share;
		rd = new Random();
	}
	
	word_object generate_word() {
		int index = rd.nextInt(share.words.size());
		String word = share.words.get(index);
	    int x= rd.nextInt(share.column - word.length());
	    word_object w = new word_object(word, x);
	    return w;
	}
	
	void update() {
		for (int i=0; i<share.row; i++)
			Arrays.fill(share.back[i], ' ');
		if (share.woArr.size() != 0) {
			for (int i=0; i<share.woArr.size(); i++) {
				share.woArr.get(i).fall();
			}
		}
		terminate();
		
		
		for (int i=0; i<share.woArr.size(); i++) {
			word_object wo = share.woArr.get(i);
			int[] yx = wo.get_yx(); // {y, x}
			String word = wo.get_word();
			for (int j=0; j<word.length(); j++) {
				share.back[yx[0]][yx[1]+j] = word.charAt(j);
			}
		}
	}
	
	void terminate() {
		if (share.woArr.get(0).get_yx()[0] >= share.back.length) {
			share.isOn = false;
		}
	}
	
	
	
	public void run() {
		try {
			while (share.isOn) {
				
				word_object w = generate_word();
				synchronized(share) {
					share.woArr.add(w);
					update();
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
		}
	}
}
