package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class LoginFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("member/login"); // member/loginForm.jsp 경로 설정
		forward.setRedirect(false);  // boolean 기본값이 false
		return forward;
	}
class form {
	boolean onsubmit() {
		return false;
	}
}
	
}
