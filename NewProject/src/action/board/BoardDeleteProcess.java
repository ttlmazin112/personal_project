package action.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import controller.FrontController;
import dao.BoardDao;
import vo.ActionForward;

public class BoardDeleteProcess implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("\n====== BDP ======");
		//int num, Strin pass 파라미터 가져오기 
		int num = Integer.parseInt(request.getParameter("num"));
		String pass = request.getParameter("pass");
		
		//DB객체 준비 
		BoardDao boardDao = BoardDao.getInstance();
		//deleteBoard(num, pass)
		boolean isSuccess = boardDao.deleteBoard(num, pass);
		System.out.println("\n\n" + isSuccess);
		//(글패스워드가 달라서) 삭제 실패시 
		if(!isSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('글패스워드가 틀려요');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		//페이지번호 파라미터 가져오기
		String pageNum = request.getParameter("pageNum");
		
		//삭제 성공시 
		ActionForward forward = new ActionForward();
		forward.setPath("boardList.do?pageNum="+pageNum);
		forward.setRedirect(true);				
		return forward;
	}

}
