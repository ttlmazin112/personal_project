package action.gestBook;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dao.BoardDao;
import dao.GuestbookDAO;
import vo.ActionForward;
 
;
 
public class GuestbookDeleteActionProcess implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        System.out.println("GuestbookDeleteActionProcess11");
    	
    	
    ActionForward forward = new ActionForward();
        
        // 파라미터 값을 가져온다.
    String inputPW = request.getParameter("guestbook_password");
    String g_no = request.getParameter("guestbook_no");
    int guestbook_no = Integer.parseInt(g_no);
        System.out.println("Num : " + g_no);
     
        
        GuestbookDAO dao = GuestbookDAO.getInstance();
        //String dbPW = dao.getPassword(guestbook_no);
        String dbPW = dao.getPassword(guestbook_no);
        
        // 비밀번호가 틀릴 경우
   
        if(!dbPW.equals(inputPW))
        {    
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
			   out.println("alert('글패스워드가 틀려요');");
			   out.println("history.back();");
			   out.println("</script>");
			   out.close();
            
 
            return null;
        }
		/*
		 * if(!dbPW.equals(inputPW)) {
		 * response.setContentType("application/json;charset=UTF-8"); PrintWriter out =
		 * response.getWriter(); out.println(0); out.close();
		 * 
		 * return null; }
		 */
 
        //boolean result = dao.deleteGuestbook(guestbook_no,gue);
		/*
		 * if(result){
		 * 
		 * forward.setPath("GuestbookListAction.do"); forward.setRedirect(true); } else
		 * return null;
		 * 
		 * return forward;
		 *
		 */
        boolean result = dao.deleteGuestbook(guestbook_no);
        if(result){
            forward.setPath("GuestbookListAction.do");    
            forward.setRedirect(true);
        }
        else 
            return null;
 
        return forward;    
    }


}


