package action.gestBook;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import action.Action;
import dao.GuestbookDAO;
import vo.ActionForward;

public class GuestbookPwCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GuestbookPwCheckAction111");
		 // 파라미터 값을 가져온다.
        String inputPW = request.getParameter("guestbook_password");
        String g_no = request.getParameter("guestbook_no");
        int guestbook_no = Integer.parseInt(g_no);
        System.out.println("PW / NUM : " + inputPW + " / " + guestbook_no);
        
        GuestbookDAO dao = GuestbookDAO.getInstance();
        String dbPW = dao.getPassword(guestbook_no);
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        if(!dbPW.equals(inputPW))   {
        	out.println("0"); // 비밀번호가 틀릴 경우
        	 return null;
        }else {
        	   out.println("1"); // 비밀번호 일치
               
               out.close();
               return null;
          
        }
    
     
    }

	}


