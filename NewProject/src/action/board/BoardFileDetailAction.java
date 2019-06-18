package action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dao.BoardDao;
import vo.ActionForward;
import vo.Board;

public class BoardFileDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 글번호 파라미터 가져오기
		int num = Integer.parseInt(request.getParameter("num"));

		// DB객체 준비
		BoardDao boardDao = BoardDao.getInstance();
		// 조회수 1증가
		boardDao.updateReadcount(num);
		// 글번호에 해당하는 글 전체(상세)내용 가져오기
		Board board = boardDao.getBoard(num);
		
		/*
		글내용 줄바꿈 처리 방법(2가지 방법)
		(1) <pre>태그처리
		(2) \r\n -> <br> 바꾸기
		*/

		String content ="";
		if(board.getContent() != null){
			content = board.getContent().replace("\r\n", "<br>");
			board.setContent(content);
		}

		
		String filename = board.getFilename();
		String ext = "";
		if(filename != null) {
			int index= filename.lastIndexOf('.');
			ext = filename.substring(index+1);
			System.out.println("이미지 확장자: "+ext);
		}
		

		request.setAttribute("board", board); // 글번호 해당되는 내용
		request.setAttribute("ext", ext); //파일명 확장자 저장
		
		ActionForward forward = new ActionForward();
		forward.setPath("center/fileContent");
		forward.setRedirect(false);
		return forward;
	}

}
