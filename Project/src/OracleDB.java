import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class OracleDB {
	Connection conn = null;
	String sql;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Scanner sc;
	// user_info
	String id;
	String pw;
	int highScore;
		
	public OracleDB() {
		sc = new Scanner(System.in);
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "sk", pw="123123";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loading Success");
			conn = DriverManager.getConnection(url, user, pw);
			System.out.println("check");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	String[] input_IdPw() {
		System.out.print("ID : ");
		String id = sc.next();
		System.out.print("PW : ");
		String pw = sc.next();
		String[] id_pw = {id, pw};
		return id_pw;
	}
	
	void Signup() {
		String[] id_pw = input_IdPw();
		sql = "select count(*) from user_info where user_id = '" + id_pw[0] + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == 0) {
					sql = "insert into user_info(user_id, password) values('" + id_pw[0] + "', '" + id_pw[1] + "')";
					pstmt = conn.prepareStatement(sql);
					pstmt.executeUpdate();
					System.out.println("Sign Up Success");
				} else {
					System.out.println(id_pw[0] + " is already exists");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	int Login() {
		String[] id_pw = input_IdPw();
		sql = "select * from user_info where user_id = '" + id_pw[0] + "' and password = '" + id_pw[1] + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getString(1);
				pw = rs.getString(2);
				highScore = rs.getInt(3);
				System.out.println("Login Success");
			}
			else {
				System.out.println("Login Fail");
				Login();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return highScore;
	}
	
	void Update_Hidghscore(int score) {
		System.out.println(id + " " + highScore);
		try {
			if (score > highScore) {
				sql = "update user_info set high_score=" + score + " where user_id='" + id + "'";
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	void Update_Records(int score) {
		try {
			sql = "insert into user_records(user_id, score) values('" + id +"', " + score + ")";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	void show_scoreboard() {
		try {
			sql = "select user_id, high_score as score from user_info order by score desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			System.out.println("id\tscore");
			System.out.println("--------------");
			while (rs.next()) {
				System.out.println(rs.getString(1) + "\t" + rs.getInt(2));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	void Logout() {
		id = null;
		pw = null;
		highScore = 0;
	}
	
	boolean Withdrawal() {
		String[] id_pw = input_IdPw();
		sql = "select * from user_info where user_id = '" + id_pw[0] + "' and password = '" + id_pw[1] + "'";
		boolean withdraw = false;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				withdraw = true;
			}
			if (withdraw) {
				sql = "delete from user_info where user_id='" + id + "' and password='" + pw + "'";
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				System.out.println("Withdrawl user " + id);
			}
			else System.out.println("Withdrawl user Fail");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}return withdraw;
	}
	
	void user_records() {
		sql = "select * from user_records where user_id = '" + id + "' order by record_date";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("\tRecords");
			System.out.println("-------------------------");
			if (rs.next()) {
				System.out.println(rs.getDate(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	void close() {
		try {
			if (!rs.isClosed())
				rs.close();
			if (!pstmt.isClosed()) pstmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}
