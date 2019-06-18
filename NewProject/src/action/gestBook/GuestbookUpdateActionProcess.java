package action.gestBook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dao.GuestbookDAO;
import vo.ActionForward;
import vo.Guestbook;

public class GuestbookUpdateActionProcess implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 request.setCharacterEncoding("UTF-8");
	        ActionForward forward = new ActionForward();
	    
	        GuestbookDAO dao = GuestbookDAO.getInstance();
	        Guestbook guestbook = new Guestbook();
	        
	        // 파라미터 값을 가져온다.
	        int guestbook_no = Integer.parseInt(request.getParameter("guestbook_no"));
	        String guestbook_id = request.getParameter("guestbook_id");
	        String guestbook_content = request.getParameter("guestbook_content");
	        String pageNum = request.getParameter("page");
	        
	        guestbook.setGuestbook_no(guestbook_no);
	        guestbook.setGuestbook_id(guestbook_id);
	        guestbook.setGuestbook_content(guestbook_content);
	        
	        boolean result = dao.updateGuestbook(guestbook);
	        
	        if(result){
	            forward.setRedirect(true);
	            forward.setPath("GuestbookListAction.do?page="+pageNum);
	        }
	        else
	            return null;
	        
	        return forward;


	}

}
