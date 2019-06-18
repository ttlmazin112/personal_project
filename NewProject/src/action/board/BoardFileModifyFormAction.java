package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dao.BoardDao;
import vo.ActionForward;
import vo.Board;

public class BoardFileModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			//int num 파라미터 가져오기 
			int num =Integer.parseInt(request.getParameter("num"));
			
			//DB객체 준비 
			BoardDao boardDao = BoardDao.getInstance();
			Board board = boardDao.getBoard(num);
			
			//request 영역객체에 저장 
			request.setAttribute("board", board);
			
			/*디스패치 방식으로 jsp뷰로 이동할때 
			기존의 동일한 request객체가 전달됨.
			request.setAttribute("num", num);
			글번호num을 영역책체에 따로 저장할 필요가 없음.
			동일 request객체이므로 글번호 파라미터로 바로 찾을 수 있음*/
			
			
			//update.jsp로 이동 
			ActionForward forward = new ActionForward();
			forward.setPath("center/fileUpdate");
			forward.setRedirect(false);	//디스패치 방식 이동	
			return forward;
	}

}
