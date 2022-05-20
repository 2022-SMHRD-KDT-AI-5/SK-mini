import java.util.Scanner;

public class Loops {
	static Scanner sc = new Scanner(System.in);
	static String id;
	static int highScore;
	static int menu1() {
		System.out.println("-------------------------");
		System.out.println("\n\n\n1. Login \n\n2. Scoreboard\n\n3. Sign up\n\n4. Exit\n\n\n");
		System.out.println("-------------------------");
		System.out.print("Select >> ");
		int n = sc.nextInt();
		
		return n;
	}
	
	static int menu2() {
		System.out.println("-------------------------");
		System.out.println("\n\n\n1. Logout \n\n2. Scoreboard\n\n3. My Records\n\n4. Withdrawal\n\n5.Exit\n\n\n");
		System.out.println("-------------------------");
		System.out.print("Select >> ");
		int n = sc.nextInt();
		
		return n;
	}
	
	static void main_loop(OracleDB db) {
		boolean loop = true;
		boolean loop1 = true;
		boolean loop2 = false;
		int n;
		while (loop) {
			while (loop1) {
				n = menu1();
				switch (n) {
				case 1:
					highScore = db.Login();
					loop1 = false;
					loop2 = true;
					break;
				case 2:
					db.show_scoreboard();
					break;
				case 3:
					db.Signup();
					break;
				case 4:
					loop = loop1 = loop2 = false;
					System.out.println("Exit");
					break;
				}
			}
			while (loop2) {
				n = menu2();
				switch (n) {
				case 1:
					db.Logout();
					loop1 = true;
					loop2 = false;
					break;
				case 2:
					db.show_scoreboard();
					break;
				case 3:
					db.user_records();
					break;
				case 4:
					loop1 = db.Withdrawal();
					loop2 = !loop1;
					break;
				case 5:
					loop = loop1 = loop2 = false;
					System.out.println("Exit");
					break;
				default:
					db.Update_Records(50);
				}
			}
		}
	}
}
