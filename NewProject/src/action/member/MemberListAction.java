package action.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import dao.MemberDao;
import vo.ActionForward;
import vo.Member;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 관리자만 접근하는 액션
		
		// 세션 구하기
		HttpSession session = request.getSession();
		// 정상 로그인과정 거쳤는지 확인
		String id = (String) session.getAttribute("id");

//		if (id == null || !id.equals()) {
//		}
		
		ActionForward forward = null;
		if (!"admin".equals(id)) {
			forward = new ActionForward("main.do", true);
			return forward;
		}
		
		// 관리자
		MemberDao memberDao = MemberDao.getInstance();
		List<Member> list = memberDao.getAllMembers();
		
		// request 영역객체에 싣으면
		// 디스패치로 이동한 jsp까지 살아서 갑니다.
		request.setAttribute("memberlist", list);
		

		forward = new ActionForward();
		forward.setPath("member/member_list");
		forward.setRedirect(false);
		return forward;
	}

}
