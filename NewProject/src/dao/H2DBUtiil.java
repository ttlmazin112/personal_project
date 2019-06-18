package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.h2.jdbcx.JdbcConnectionPool;

public class H2DBUtiil {
	private static JdbcConnectionPool jdbcConnectionPool;
	// DB연결
	public static Connection getConnection() throws Exception {
		String url = "jdbc:h2:tcp://localhost/~/최수혁";
		String user = "sa";
		String passwd = "";
		Connection con = null;

		// 1) JDBC 드라이버 로딩
		Class.forName("org.h2.Driver");
		// 2) DB연결
//		con = DriverManager.getConnection(url, user, passwd);
		
		//H2DB에 JDBC 커넥션 풀 적용 
		if(jdbcConnectionPool == null) {
			jdbcConnectionPool  = JdbcConnectionPool.create(url, user, passwd);
		}
		con = jdbcConnectionPool.getConnection();
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
