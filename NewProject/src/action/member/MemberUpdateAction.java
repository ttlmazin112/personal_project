package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dao.MemberDao;
import vo.ActionForward;
import vo.Member;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ID = request.getParameter("id");
		Member member = new Member();
		if(ID!=null) {
			MemberDao dao = MemberDao.getInstance();
			member = dao.detailMember(ID);
			request.setAttribute("member", member);
		}
		ActionForward forward = new ActionForward();
		forward.setPath("member/memberupdate");
		forward.setRedirect(false);	//디스패치 방식 이동	
		return forward;
	}

}
