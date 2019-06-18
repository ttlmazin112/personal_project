package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dao.BoardDao;
import vo.ActionForward;
import vo.Board;

public class BoardWriteProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String name = request.getParameter("name");
//		String pass = request.getParameter("pass");
//		String subject = request.getParameter("subject");
//		String content = request.getParameter("content");
		System.out.println("\n====== BoardWriteProcessAction =======");
		//파라미터값 가져와서 Board 객체로 만드기
		Board board = new Board();
		board.setName(request.getParameter("name"));
		board.setPass(request.getParameter("pass"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		//글작성자 IP주소 값 저장 
		board.setIp(request.getRemoteAddr());
		
		//DB작업 객체 준비 BoardDao 
		BoardDao boardDao = BoardDao.getInstance();
		//메소드 호출 insert(board) 글작성 완료 
		boardDao.insert(board);
		//글목록 (boardList.do)이동 -> 리다이렉트 시킴 
		ActionForward forward = new ActionForward(); 
		forward.setPath("boardList.do");
		forward.setRedirect(true);		
		return forward;
		
	}

}
