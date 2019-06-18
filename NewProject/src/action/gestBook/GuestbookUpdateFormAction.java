package action.gestBook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dao.GuestbookDAO;
import vo.ActionForward;
import vo.Guestbook;

public class GuestbookUpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
ActionForward forward = new ActionForward();
        
        // 방명록 글번호와 페이지 번호를 가져온다.
        int guestbook_no = Integer.parseInt(request.getParameter("num"));
        String pageNum = request.getParameter("page");
        
        // DB에서 해당 방명록의 정보를 가져온다.
        GuestbookDAO dao = GuestbookDAO.getInstance();
        Guestbook guestbook = dao.getGuestbook(guestbook_no);
        
        // 방명록의 정보와 페이지번호를 request에 세팅한다.
        request.setAttribute("guestbook", guestbook);
        request.setAttribute("pageNum", pageNum);
        
        forward.setRedirect(false);
        forward.setPath("guestbook/GuestbookUpdateForm");
        
        return forward;



	}

}
