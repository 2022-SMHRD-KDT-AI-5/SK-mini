package Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	// Socket
	Socket socket;
	BufferedReader reader;
	PrintWriter writer;
	SharedArea share;
	word_update wt;
	backUpdate bt;
	OracleDB db;
	String sql;

	public ServerThread() {
		super();
	}

	public ServerThread(Socket sock, SharedArea share, OracleDB db) {
		super();
		this.socket = sock;
		this.share = share;
		this.db = db;
		this.wt = wt;
		this.bt = bt;
		try {
			reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

	void loop() {
		boolean loop = true;
		boolean loop1 = true;
		boolean loop2 = false;
		int n;
		try {
			while (loop) {
				while (loop1) {
					n = Integer.parseInt(reader.readLine()); // read 1
					writer.println(""); // write 1
					writer.flush();
					switch (n) { // 1.login 2.sign up 3.exit
					case 1:
						String loginAnswer = db.Login(reader.readLine()); // read 2
						writer.println(loginAnswer); // write 2
						writer.flush();
						if (loginAnswer.split(",")[0].equals("true")) {
							loop1 = false;
							loop2 = true;
						}
						break;
					case 2:
						String answer = reader.readLine();
						String[] id_pw = answer.split(",");
						db.Signup(id_pw);
						writer.println();
						writer.flush();
						break;
					case 3:
						loop = loop1 = loop2 = false;
						System.out.println("Exit");
						break;
					}
				}
				while (loop2) { // 1.logout 2.start 3.my record 4.scoreboard
					n = Integer.parseInt(reader.readLine()); // read 1
					switch (n) {
					case 1:
						writer.println(""); // write 1
						writer.flush();
						db.Logout();
						loop1 = true;
						loop2 = false;
						break;
					case 2:
						word_update wut = new word_update(share);
						backUpdate but = new backUpdate(share);
						input it = new input(share, reader, writer);
						wut.start();
						but.start();
						it.start();
						try {
							it.join();
							but.join();
							wut.join();
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						writer.println(Integer.toString(share.score));
						writer.flush();
						String update = reader.readLine();
						if (update.startsWith("update")) {
							System.out.println("highscore");
							db.Update_Hidghscore(update);
							writer.println();
							writer.flush();
							update = reader.readLine();
						}
						db.Update_Records(update);
						writer.println();
						writer.flush();
						System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
						System.out.println("Score : " + share.score+"\n\n\n\n\n\n\n\n\n");
						share.init();
						break;
					case 3:
						db.user_records(reader.readLine());
						writer.println("");
						writer.flush();
						break;
					case 4:
						db.show_scoreboard(reader.readLine());
						writer.println("");
						writer.flush();
						break;
					case 5:
						loop = loop1 = loop2 = false;
						System.out.println("Exit");
						break;
					default:
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	void check(String s) {
		for (int i = 0; i < share.woArr.size(); i++) {
			if (share.woArr.get(i).get_word().equals(s)) {
				share.woArr.remove(i);
				share.score += 1;
			}
		}
	}

	public void run() {
		try {
			loop();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
