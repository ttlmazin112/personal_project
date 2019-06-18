package action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import dao.MemberDao;
import vo.ActionForward;

public class LoginProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		
		
		ActionForward forward = null;
		
		// DB작업 객체 생성
		MemberDao memberDao = MemberDao.getInstance();
		int check = memberDao.loginCheck(id, pass);
		
		String keepLogin = request.getParameter("keepLogin");
		System.out.println("keepLogin : " + keepLogin);
		
		if (check != 1) { // 로그인 실패시
			String message = "";
			if (check == -1) { // 아이디 불일치
				message = "해당하는 아이디가 없습니다.";
			} else if (check == 0) { // 비밀번호 불일치
				message = "비밀번호가 일치하지 않습니다.";
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("id", id);	    
			    response.sendRedirect("main.do?keepLogin="+keepLogin+"&id="+id);
			}
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + message + "');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		
		
		// 아이디,비밀번호 모두 일치(로그인 성공)
		// 세션 구하기
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		
		
		// .me로 끝나면 명령어 형식
		// -> 프론트 컨트롤러로 다시 입력을 유도
		// -> 리다이렉트로 브라우저가 재요청하도록함.
		forward = new ActionForward();
		forward.setPath("main.do"); 
		forward.setRedirect(true); // 리다이렉트(브라우저 재요청)
		return forward;
	}

}
