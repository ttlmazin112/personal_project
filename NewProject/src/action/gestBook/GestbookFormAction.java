package action.gestBook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;




public class GestbookFormAction implements Action
{
	private String path = "MainForm.jsp?contentPage=gestbook/GestbookForm.jsp";

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("guestbook/GuestbookForm");
		forward.setRedirect(false);
		
		
		return forward;
	}
}
