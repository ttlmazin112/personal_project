package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.naming.Context;

public class OracleDB {
	// DB연결
	public static Connection getConnection() throws Exception {
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		String user = "scott";
//		String passwd = "tiger";
		Connection con = null;

		// 1) JDBC 드라이버 로딩
//		Class.forName("oracle.jdbc.OracleDriver");
		// 2) DB연결
//		con = DriverManager.getConnection(url, user, passwd);
		
		// ====== DBCP 적용 코드 =======
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
		con = ds.getConnection();//커넥션 한개 빌려줌
		return con;
	} // getConnection()

	// JDBC 자원닫기
	public static void closeJDBC(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} // closeJDBC()
}
