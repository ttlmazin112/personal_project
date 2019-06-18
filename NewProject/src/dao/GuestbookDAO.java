package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Guestbook;

public class GuestbookDAO {
	private static GuestbookDAO guestBookDao = new GuestbookDAO();
	
	private GuestbookDAO() {
		
	}
	
	public static GuestbookDAO getInstance() {
		return guestBookDao;
	}
	
	 // 시퀀스를 가져온다.
    public int getSeq()  {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
        int result = 0;
        try {
            con = H2DBUtiil.getConnection();
            System.out.println("커넥션 가져옴");
            // 시퀀스 값을 가져온다. (DUAL : 시퀀스 값을 가져오기위한 임시 테이블)
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT guestbook_no_seq.NEXTVAL FROM DUAL ");
 
            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(); // 쿼리 실행
            System.out.println("쿼리 실행됨");
            if (rs.next()) {
            	System.out.println("if rs.next 진입----");
            	result = rs.getInt(1);
            	System.out.println("result : " + result);
            }
 
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally {
        	H2DBUtiil.closeJDBC(con, pstmt, rs);
        }
        
        return result;
    } // end getSeq


	
    // 병명록 등록
    public boolean guestbookInsert(Guestbook guestbook){
    	System.out.println("guestbookInsert() guestbook : " + guestbook);
    	
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
        boolean result = false;
        
        try {
            con = H2DBUtiil.getConnection();
 
            // 자동 커밋을 false로 한다.
            con.setAutoCommit(false);
            
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO GUESTBOOK ");
            sql.append(" (GUESTBOOK_NO, GUESTBOOK_ID, GUESTBOOK_PASSWORD, GUESTBOOK_CONTENT ");
            sql.append(" , GUESTBOOK_GROUP, GUESTBOOK_PARENT, GUESTBOOK_DATE, GUESTBOOK_LEVEL) ");
            sql.append(" VALUES(?,?,?,?,?,?,sysdate,?)");
            
            int no = guestbook.getGuestbook_no();           // 글번호(시퀀스 값)
            int group = guestbook.getGuestbook_group();     // 그룹번호
            int parent = guestbook.getGuestbook_parent();     // 부모글번호
            int level = guestbook.getGuestbook_level();
            // 부모글일 경우 그룹번호와 글번호 동일
            if(parent == 0) group = no;
    
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, no);
            pstmt.setString(2, guestbook.getGuestbook_id());
            pstmt.setString(3, guestbook.getGuestbook_password());
            pstmt.setString(4, guestbook.getGuestbook_content());
            pstmt.setInt(5, group);
            pstmt.setInt(6, parent);
            pstmt.setInt(7, level);
 
            int flag = pstmt.executeUpdate();
            if(flag > 0){
                result = true;
                con.commit(); // 완료시 커밋
            }
            
        } catch (Exception e) {
            try {
                con.rollback(); // 오류시 롤백
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } 
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
        	H2DBUtiil.closeJDBC(con, pstmt, rs);
        }
        
        
        return result;    
    } // end boardInsert();



	
    public ArrayList<Guestbook> getGuestbookList(int pageNum){
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	
    	ArrayList<Guestbook> list = new ArrayList<Guestbook>();
    	
    	try {
    		con=H2DBUtiil.getConnection();
    		
    		StringBuffer sb = new StringBuffer();
    		 sb.append("SELECT * FROM GUESTBOOK ");
//    	     sb.append("    (SELECT guestbook_level, guestbook_no, guestbook_id,");
//             sb.append("            guestbook_password, guestbook_content,");
//             sb.append("            guestbook_group, guestbook_parent, guestbook_date");
//             sb.append("    FROM GUESTBOOK ");
//             sb.append("    START WITH guestbook_parent = 0");
//             sb.append("    CONNECT BY PRIOR guestbook_no = guestbook_parent");
             sb.append(" ORDER BY guestbook_group desc ");                      
             sb.append(" OFFSET ? LIMIT ? ");

            // 방명록 목록은 한 화면에 총 5개가 보이도록 한다.
            pstmt = con.prepareStatement(sb.toString());
            pstmt.setInt(1, pageNum);
            pstmt.setInt(2, pageNum+4);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                Guestbook guestbook = new Guestbook();
                guestbook.setGuestbook_level(rs.getInt("guestbook_level"));
                guestbook.setGuestbook_no(rs.getInt("guestbook_no"));
                guestbook.setGuestbook_id(rs.getString("guestbook_id"));
                guestbook.setGuestbook_password(rs.getString("guestbook_password"));
                guestbook.setGuestbook_content(rs.getString("guestbook_content"));
                guestbook.setGuestbook_group(rs.getInt("guestbook_group")+1);
                guestbook.setGuestbook_parent(rs.getInt("guestbook_parent"));
                guestbook.setGuestbook_date(rs.getDate("guestbook_date"));
                list.add(guestbook);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
        	H2DBUtiil.closeJDBC(con, pstmt, rs);
        }
        
       
        return list;
    }



public int getGuestbookCount(){
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	int count = 0;
	
	try {
		con = H2DBUtiil.getConnection();
		
		pstmt = con.prepareStatement("SELECT COUNT(*) FROM GUESTBOOK");
		
		rs = pstmt.executeQuery();
		if(rs.next()) {
			count = rs.getInt(1);
		}	
	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException(e.getMessage());
	}finally {
		H2DBUtiil.closeJDBC(con, pstmt, rs);
	}
	return count;
}//getGuestbookCount()

//방명록 1개의 정보를 가져온다.
public Guestbook getGuestbook(int g_num)
{
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
    Guestbook guestbook = null;
    
    try {
        con = H2DBUtiil.getConnection();
        
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM GUESTBOOK where guestbook_no = ?");
        
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, g_num);
        
        rs = pstmt.executeQuery();
        while(rs.next())
        {
            guestbook = new Guestbook();
            guestbook.setGuestbook_no(rs.getInt("guestbook_no"));
            guestbook.setGuestbook_id(rs.getString("guestbook_id"));
            guestbook.setGuestbook_password(rs.getString("guestbook_password"));
            guestbook.setGuestbook_content(rs.getString("guestbook_content"));
            guestbook.setGuestbook_group(rs.getInt("guestbook_group"));
            guestbook.setGuestbook_parent(rs.getInt("guestbook_parent"));
            guestbook.setGuestbook_date(rs.getDate("guestbook_date"));
        }
    } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
    }finally {
    	H2DBUtiil.closeJDBC(con, pstmt, rs);
    }
    
   
    return guestbook; 
} // end getGuestbook

// 비밀번호를 가져온다.
public String getPassword(int guestbook_no){
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
    String password = null;
    
    try {
        con = H2DBUtiil.getConnection();
        
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT guestbook_password FROM GUESTBOOK where guestbook_no = ?");
        
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setInt(1, guestbook_no);
        
        rs = pstmt.executeQuery();
        if(rs.next()) password = rs.getString("guestbook_password");
        
    } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
    }finally {
    	H2DBUtiil.closeJDBC(con, pstmt, rs);
    }
    
    
    return password;
} // end getPassword

//방명록 삭제
public boolean deleteGuestbook(int guestbook_no) 
{
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
boolean result = false;

try {
   con = H2DBUtiil.getConnection();
   con.setAutoCommit(false); // 자동 커밋을 false로 한다.

   StringBuffer sql = new StringBuffer();
   sql.append("DELETE FROM GUESTBOOK");
   sql.append(" WHERE guestbook_no =?");
			/*
			 * sql.append(" (SELECT guestbook_no"); sql.append(" FROM GUESTBOOK");
			 * sql.append(" START WITH guestbook_no = ?");
			 * sql.append(" CONNECT BY PRIOR guestbook_no = guestbook_parent) ");
			 */
   
   pstmt = con.prepareStatement(sql.toString());
   pstmt.setInt(1, guestbook_no);
   
   int flag = pstmt.executeUpdate();
   if(flag > 0){
       result = true;
       con.commit(); // 완료시 커밋
   }    
   
} catch (Exception e) {
   try {
       con.rollback(); // 오류시 롤백
   } catch (SQLException sqle) {
       sqle.printStackTrace();
   }
   throw new RuntimeException(e.getMessage());
}finally {
	H2DBUtiil.closeJDBC(con, pstmt, rs);
}


return result;
} // end deleteGuestbook 

// 방명록 수정
public boolean updateGuestbook(Guestbook guestbook) 
{
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
    boolean result = false;
    
    try{
        con = H2DBUtiil.getConnection();
        con.setAutoCommit(false); // 자동 커밋을 false로 한다.
        
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE GUESTBOOK SET");
        sql.append(" guestbook_id=?");
        sql.append(" ,guestbook_content=?");
        sql.append(" ,guestbook_date=SYSDATE ");
        sql.append("WHERE guestbook_no=?");

        pstmt = con.prepareStatement(sql.toString());
        pstmt.setString(1, guestbook.getGuestbook_id());
        pstmt.setString(2, guestbook.getGuestbook_content());
        pstmt.setInt(3, guestbook.getGuestbook_no());
        
        int flag = pstmt.executeUpdate();
        if(flag > 0){
            result = true;
            con.commit(); // 완료시 커밋
        }
        
    } catch (Exception e) {
        try {
            con.rollback(); // 오류시 롤백
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        throw new RuntimeException(e.getMessage());
    }finally {
    	H2DBUtiil.closeJDBC(con, pstmt, rs);
    }

  
    return result;
} // end updateGuestbook    














	
	
}




