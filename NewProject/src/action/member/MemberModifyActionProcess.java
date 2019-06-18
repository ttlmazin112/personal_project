package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import dao.MemberDao;
import vo.ActionForward;
import vo.Member;

public class MemberModifyActionProcess implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		
		HttpSession session = request.getSession();
		String id = session.getAttribute("id").toString();
		
		
		MemberDao dao = MemberDao.getInstance();
		Member member = dao.getUserInfo(id);
		
		
		request.setAttribute("memberInfo", member);
		
		
		forward.setPath("member/userModify");
		forward.setRedirect(false);
		return forward;
	}

}
