package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import dao.MemberDao;
import vo.ActionForward;
import vo.Member;

public class MemberModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8"); 
		System.out.println("MemberModifyAction111");
		ActionForward forward = new ActionForward();
		
		MemberDao dao = MemberDao.getInstance();
		
		
		HttpSession session = request.getSession();
		String id = session.getAttribute("id").toString();
		
		
		Member member = new Member();
		member.setId(id);
		member.setName(request.getParameter("name"));
		member.setPassword(request.getParameter("password"));
		member.setEmail(request.getParameter("email"));
		member.setMtel(request.getParameter("mtel"));
		member.setAddress(request.getParameter("address"));
		
		dao.updateMember(member);
		session.setAttribute("msg", "0");
		
   		forward.setPath("member/resultForm");
   		forward.setRedirect(false);
	
		
		return forward;
	}

}
