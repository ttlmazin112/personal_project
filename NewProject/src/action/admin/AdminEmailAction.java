package action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import dao.MemberDao;
import vo.ActionForward;
import vo.Member;

public class AdminEmailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		/*
		 * HttpSession session = request.getSession(); // 정상 로그인과정 거쳤는지 확인 String id =
		 * (String) session.getAttribute("id");
		 * 
		 * // 관리자 MemberDao memberDao = MemberDao.getInstance(); List<Member> list =
		 * memberDao.getAllMembers();
		 * 
		 * // request 영역객체에 싣으면 // 디스패치로 이동한 jsp까지 살아서 갑니다.
		 * request.setAttribute("member", list);
		 * 
		 * String[] idArr = request.getParameterValues("id"); if(idArr != null) {
		 * StringBuilder sb = new StringBuilder(); sb.setLength(0); for(String ID :
		 * idArr) { if(sb.length()==0) { sb.append("'"+ID+"'"); } else {
		 * sb.append(", "+"'"+ID+"'"); } }
		 * 
		 * String idStr = sb.toString(); memberDao = MemberDao.getInstance();
		 * List<String> emailList = memberDao.getEmail(idStr); sb.setLength(0);
		 * for(String email : emailList) { if(sb.length()==0) { sb.append(email); } else
		 * { sb.append(", "+email); } } String email = sb.toString();
		 * request.setAttribute("email",email); }
		 */
		String[] ids = request.getParameterValues("chBoxId");
		
		MemberDao dao = MemberDao.getInstance();
		
		List<String> emailList = dao.getEmail(ids);
		System.out.println(emailList);
		
		String emails = "";
		for (int i=0; i<emailList.size(); i++) {
			if (i > 0) {
				emails += ", ";
			}
			emails += emailList.get(i);
		}
		System.out.println(emails);
		
		request.setAttribute("emails", emails);
		
		forward.setPath("admin/adminEmail");
		forward.setRedirect(false);
		return forward;
	}

}
