package action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dao.MemberDao;
import vo.ActionForward;

public class MemberDelActionProcess implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String[] id = request.getParameterValues("chBoxId");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (id == null) {
			out.println("<script>");
			out.println("alert('강제 탈퇴할 회원을 선택해 주세요.');");
			out.println("location.href='member_list.do'");
			out.println("</script>");
			out.close();
			return null;
		}
		
		MemberDao memberDao = MemberDao.getInstance();
		
		int result = memberDao.batchDeleteMember(id);
//		int result = memberDao.deleteMember(id);
			
		out.println("<script>");
		if (result > 0) {
			out.println("alert('" + result + "명 강제 탈퇴 완료함');");
			out.println("location.href='member_list.do'");
		} else {
			out.println("alert('탈퇴 처리 실패함');");
			out.println("location.href='member_list.do'");
		}
		out.println("</script>");
		out.close();		
		return null;
//		ActionForward forward = new ActionForward();
//		forward.setRedirect(false);
//		forward.setPath("admin/memberList");
//		
//		return forward;
	}

}
