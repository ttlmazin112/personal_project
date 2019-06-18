package action.gestBook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dao.GuestbookDAO;
import vo.ActionForward;
import vo.Guestbook;

public class GuestbookWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
//		Guestbook guestBook = new Guestbook();
//		int no = Integer.parseInt(request.getParameter("guestbook_no"));
//		guestBook.setGuestbook_no(no);

		
		
		
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		
		String guestbook_id = request.getParameter("guestbook_id");
		String guestbook_password = request.getParameter("guestbook_password");
		String guestbook_content = request.getParameter("guestbook_content");
		
		GuestbookDAO dao = GuestbookDAO.getInstance();
		
		
		Guestbook guestbook = new Guestbook();
		guestbook.setGuestbook_no(dao.getSeq());
		guestbook.setGuestbook_id(guestbook_id);
		guestbook.setGuestbook_password(guestbook_password);
		guestbook.setGuestbook_content(guestbook_content);
		
		boolean result = dao.guestbookInsert(guestbook);
		
		if(result){
			forward.setRedirect(true);
			forward.setPath("GuestbookListAction.do");
		}
		
		return forward;
	}

}
