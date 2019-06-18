package action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import dao.MemberDao;
import vo.ActionForward;

public class UserDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	ActionForward forward = new ActionForward();
		
		
		HttpSession session = request.getSession();
		String id = session.getAttribute("id").toString();
		String password = request.getParameter("password");
		
		MemberDao dao = MemberDao.getInstance();
		int check = dao.deleteMember(id, password);
		
		if(check == 1){
			
			session.removeAttribute("id");
			
			forward.setPath("member/resultForm");
			forward.setRedirect(false);
		}
		else{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('회원탈퇴 실패');");
			out.println("location.href='userInfo.do';");
			out.println("</script>");
			out.close();
			return null;
		}
		
		return forward;
	}

}
