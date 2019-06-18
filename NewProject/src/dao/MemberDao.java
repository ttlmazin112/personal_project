package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import vo.Board;
import vo.Member;


public class MemberDao {

	private static MemberDao memberDao = new MemberDao();

	private MemberDao() {
	}

	public static MemberDao getInstance() {
		return memberDao;
	}

	
	public int insert(Member member) {
		int insertedRowCount = 0;

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";

		try {
			con = H2DBUtiil.getConnection();

			sql = "INSERT INTO member (id,password,name,birthday,gender,email,address,tel,mtel,reg_date) ";
			sql += "VALUES (?,?,?,?,?,?,?,?,?,current_timestamp)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setTimestamp(4, member.getBirthday());
			pstmt.setString(5, member.getGender());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getAddress());
			pstmt.setString(8, member.getTel());
			pstmt.setString(9, member.getMtel());
			// 실행
			insertedRowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			H2DBUtiil.closeJDBC(con, pstmt, null);
		}

		return insertedRowCount;
	} // insert()
	
	
	public int countById(String id) {
		int count = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			con = H2DBUtiil.getConnection();
			
			sql = "SELECT COUNT(*) FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 실행
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			H2DBUtiil.closeJDBC(con, pstmt, rs);
		}
		
		return count;
	} // countById()
	
	
	public int loginCheck(String id, String pass) {
		// *반환값 의미
		// 아이디 불일치: -1
		// 아이디 일치, 비밀번호 불일치: 0
		// 아이디, 비밀번호 모두 일치: 1
		int check = -1;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			con = H2DBUtiil.getConnection();
			sql = "SELECT password FROM member WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) { // 아이디 일치
				if (pass.equals(rs.getString("password"))) {
					// 비밀번호 일치
					check = 1;
				} else {
					check = 0; // 비밀번호 불일치
				}
			} else {
				check = -1; // 아이디 불일치
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			H2DBUtiil.closeJDBC(con, pstmt, rs);
		}
		
		return check;
	} // loginCheck()
	
	
	public List<Member> getAllMembers() {
		List<Member> list = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			con = H2DBUtiil.getConnection();
			sql = "SELECT * FROM member ORDER BY id";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
//				member.setAge(rs.getInt("age"));
//				member.setGender(rs.getString("gender"));
				member.setEmail(rs.getString("email"));
//				member.setReg_date(rs.getTimestamp("reg_date"));
				
				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			H2DBUtiil.closeJDBC(con, pstmt, rs);
		}
		
		return list;
	} // getAllMembers()
	
	public int deleteMember(String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        String sql="DELETE FROM member where id=?";
        int delete = 0;
        try {
            con = H2DBUtiil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            delete = pstmt.executeUpdate();
            
        }catch(Exception e){e.printStackTrace();
        }finally{
            H2DBUtiil.closeJDBC(con, pstmt, rs);;
        }
        return delete;
    }
	
	public Member detailMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Member member = null;
		
		try {
			con = H2DBUtiil.getConnection();
			
			String sql = "SELECT * FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 실행
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				member = new Member();
				
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setAddress(rs.getString("address"));
				member.setMtel(rs.getString("mtel"));
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			H2DBUtiil.closeJDBC(con, pstmt, rs);
		}
		return member;
	} // detailMember()
	
	
	public boolean memberUpdate(Member member) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		boolean isSuccess = false; 
		String sql = "";
		
		try {
			con = H2DBUtiil.getConnection();
			//num에 해당하는 pass 가져오기 
			sql = "SELECT  id From member WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			//select문 실행
			rs = pstmt.executeQuery();
			//rs데이터 있으면 글비밀번호 비교
			// 맞으면 update문 수행, isSuccess = true
			// 틀리면 isSuccess = false
			if(rs.next()) {
				if(member.getId().equals(rs.getString("id"))) {
					System.out.println("id");
					rs.close(); //rs객체 먼저 닫기 
					pstmt.close(); // select문 가진  pstmt객체 닫기 
					
					
					sql = "UPDATE member ";
					sql += "SET id=?, password=?, name=?, email=?, address=?, mtel=? ";
					sql += "WHERE id=? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, member.getId());
					pstmt.setString(2, member.getPassword());
					pstmt.setString(3, member.getName());
					pstmt.setString(4, member.getEmail());
					pstmt.setString(5, member.getAddress());
					pstmt.setString(6, member.getMtel());
					pstmt.setString(7, member.getId());
					
					
					
					//update문 실행
					pstmt.executeUpdate();
					
					isSuccess = true;
				}else {
					isSuccess = false;
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}finally {
			H2DBUtiil.closeJDBC(con, pstmt, rs);
		}
		return isSuccess;
		
		
	}//updateBoard()

	public Member getUserInfo(String id) 
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;

		try {
			
			StringBuffer query = new StringBuffer();
			query.append("SELECT * FROM MEMBER WHERE ID=?");

			con = H2DBUtiil.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) 
			{
				
//				String birthday = rs.getDate("birth").toString();
//				String year = birthday.substring(0, 4);
//				String month = birthday.substring(5, 7);
//				String day = birthday.substring(8, 10);
//				
//				
//				String email = rs.getString("email");
//				int idx = email.indexOf("@"); 
//				String email1 = email.substring(0, idx);
//				String email2 = email.substring(idx+1);
				
				
				member = new Member();
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setGender(rs.getString("gender"));
				member.setBirthday(rs.getTimestamp("birthday"));
				member.setEmail(rs.getString("email"));
				member.setMtel(rs.getString("mtel"));
				member.setAddress(rs.getString("address"));
				member.setReg_date(rs.getDate("reg_date"));
			}

			return member;

		} catch (Exception sqle) {
			throw new RuntimeException(sqle.getMessage());
		} finally {
			
			H2DBUtiil.closeJDBC(con, pstmt, rs);
		}
	}	// end getUserInfo
	
	public void updateMember(Member member) throws SQLException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			StringBuffer query = new StringBuffer();
			query.append("UPDATE MEMBER SET");
			query.append(" password=?, email=?, mtel=?, address=?");
			query.append(" WHERE id=?");

			con = H2DBUtiil.getConnection();
			pstmt = con.prepareStatement(query.toString());

			
			con.setAutoCommit(false);
			
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getMtel());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getId());

			int flag = pstmt.executeUpdate();
			if(flag > 0){
				con.commit();
			}
						
		} catch (Exception sqle) {
			con.rollback(); 
			throw new RuntimeException(sqle.getMessage());
		} finally {
			H2DBUtiil.closeJDBC(con, pstmt, null);
		}
	} // end updateMember

	public int deleteMember(String id, String pw) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String dbpw = ""; 
		int x = -1;

		try {
			
			StringBuffer query1 = new StringBuffer();
			query1.append("SELECT PASSWORD FROM MEMBER WHERE ID=?");

			
			StringBuffer query2 = new StringBuffer();
			query2.append("DELETE FROM MEMBER WHERE ID=?");

			con = H2DBUtiil.getConnection();

			
			con.setAutoCommit(false);
			
			
			pstmt = con.prepareStatement(query1.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) 
			{
				dbpw = rs.getString("password");
				if (dbpw.equals(pw)) 
				{
					
					pstmt = con.prepareStatement(query2.toString());
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					con.commit(); 
					x = 1; 
				} else {
					x = 0; 
				}
			}

			return x;

		} catch (Exception sqle) {
			try {
				con.rollback(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
			throw new RuntimeException(sqle.getMessage());
		} finally {
		H2DBUtiil.closeJDBC(con, pstmt, rs);
		}
	} // end deleteMember
	
	public JSONArray getCountPerAddress() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		
		JSONArray jsonArray = new JSONArray();
		
		JSONArray colNameArray = new JSONArray();
		colNameArray.add("거주지");
		colNameArray.add("인원수");
		
		jsonArray.add(colNameArray);
		
		try {
			con = H2DBUtiil.getConnection();
			
			sb.append("SELECT  SUBSTRING(address, 1, 2) AS addr, COUNT(*) AS cnt ");
			sb.append("FROM member ");
			sb.append("GROUP BY SUBSTRING(address, 1, 2) ");
			sb.append("ORDER BY addr ");
			
			pstmt = con.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				JSONArray rowArray = new JSONArray();
				rowArray.add(rs.getString("addr"));
				rowArray.add(rs.getInt("cnt"));
				
				jsonArray.add(rowArray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			H2DBUtiil.closeJDBC(con, pstmt, rs);
		}
		System.out.println(jsonArray);
		return jsonArray;
	} // getCountPerAddress()

	public int batchDeleteMember(String[] id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int[] result = null;
		
		try {
			StringBuffer query = new StringBuffer();
			con = H2DBUtiil.getConnection();
			con.setAutoCommit(false);
			query.append("DELETE FROM member ");
			query.append("WHERE id = ?");
			pstmt = con.prepareStatement(query.toString());
			
			for (String st : id) {
				pstmt.setString(1, st);
				pstmt.addBatch();
			}
			
			result = pstmt.executeBatch();
			
			con.commit();
			con.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			H2DBUtiil.closeJDBC(con, pstmt, null);
		}
		
		return result.length;
	}//batchDeleteMember()
	
	public static void main(String[] args) {
		MemberDao dao = MemberDao.getInstance();
		List<Member> list = dao.getAllMembers();
		
		// 직접 JSON으로 수동변환
		JSONArray jsonArray = new JSONArray();
		for (Member member : list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", member.getId());
			jsonObject.put("password", member.getPassword());
			jsonObject.put("name", member.getName());
			jsonArray.add(jsonObject);
		}
		System.out.println("jsonArray : " + jsonArray);
		
		// Gson 사용해서 JSON으로 자동변환
		Gson gson = new Gson(); 
		String json = gson.toJson(list);
		System.out.println("json : " + json);
	} // main()
	
	public List<String> getEmail(String[] ids) {

	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      List<String> list = new ArrayList<String>();
	      String sql = "";
	      String idStr = "";
	      for (int i=0; i<ids.length; i++) {
	    	  if (i > 0) {
	    		  idStr += ",";
	    	  } 
	    	  idStr += "'" + ids[i] + "'";
	      }
	      System.out.println("idStr: " + idStr);
	      
	      try {
	         con = H2DBUtiil.getConnection();
	         
	         
	         
	         
	         sql = "SELECT EMAIL FROM MEMBER WHERE id IN ("+idStr+") ";
	         System.out.println("sql : " + sql);
	         
	         pstmt = con.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         while(rs.next()) {
	            list.add(rs.getString("EMAIL")); 
	         }
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         H2DBUtiil.closeJDBC(con, pstmt, rs);
	      }
	      
	      return list;
	   }
	

}
