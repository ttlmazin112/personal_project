package action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dao.MemberDao;
import vo.ActionForward;
import vo.Member;

public class MemberUpdateProcess implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberDao dao = MemberDao.getInstance();
		Member member = new Member();
		String ID = request.getParameter("id");
		
		member.setId(request.getParameter("id"));
		member.setPassword(request.getParameter("password"));
		member.setName(request.getParameter("name"));
		member.setEmail(request.getParameter("email"));
		member.setAddress(request.getParameter("address"));
		member.setMtel(request.getParameter("mtel"));
		System.out.println("member"+member);
		boolean update= dao.memberUpdate(member);
		if(update == true) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('수정 성공.');");
			out.println("location.href='member_list.do';");
			out.println("</script>");
			out.close();
			return null;
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('수정 실패.');");
			out.println("location.href='member_list.do';");
			out.println("</script>");
			out.close();
			return null;
		}
		
	}

}
