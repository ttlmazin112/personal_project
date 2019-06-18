package action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dao.MemberDao;
import vo.ActionForward;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String ID = request.getParameter("id");
		
		if(ID==null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('잘못된 접근입니다.');");
			out.println("location.href='member_list.do';");
			out.println("</script>");
			out.close();
			return null;
		}else {
			MemberDao dao= MemberDao.getInstance();
			int delete = dao.deleteMember(ID);
			if(delete>0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제성공');");
				out.println("location.href='member_list.do';");
				out.println("</script>");
				out.close();
				return null;
			}
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("member_list");
		forward.setRedirect(true);				
		return forward;
	}

}
