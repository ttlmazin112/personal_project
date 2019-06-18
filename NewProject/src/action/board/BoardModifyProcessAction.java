package action.board;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/*import org.apache.jasper.compiler.Node.ForwardAction;*/

import action.Action;
import dao.BoardDao;
import vo.ActionForward;
import vo.Board;

public class BoardModifyProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("BoardModifyProcessAction");

		/*
		 * (1) 업로드 작업 1. request
		 * 
		 * 2. upload 폴더 만들고 물리적 경로 ServletContext
		 * application=request.getServletContext(); String realPath =
		 * application.getRealPath("/upload");
		 * System.out.println("realPath : "+realPath);
		 * 
		 * //3. 파일업로드 최대크기 제한값 int maxPostSize = 1024 * 1024 * 10; //1024byte * 1024 *
		 * 10 (업로드 10MB 제한)
		 * 
		 * //4. 한글처리 유니코드로 설정 "utf-8"
		 * 
		 * //5. 업로드하는 파일명이 기존 업로드된 파일명과 같은 경우 => 파일명 자동변경 정책
		 * 
		 * //파일업로드 처리 완료! MultipartRequest multi = new MultipartRequest(request,
		 * realPath, maxPostSize,"utf-8", new DefaultFileRenamePolicy());
		 * 
		 */

		// name pass subject content 파라미터 가져오기
		Board board = new Board();
		board.setNum(Integer.parseInt(request.getParameter("num")));
		board.setName(request.getParameter("name"));
		board.setPass(request.getParameter("pass"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));

		System.out.println("board : " + board);
		// DB객체 준비
		BoardDao boardDao = BoardDao.getInstance();
		// 메소드호출 updateBoard(board)
		// 글 패스워드 일치하면 글수정 후 글목록으로 이동
		// 글 패스워드 불일치하면 이전화면으로 돌아가기
		// boolean isSuccess = true 수정성공
		// boolean isSuccess = false 수정실패

		boolean isSuccess = boardDao.updateBoard(board);
		System.out.println("isSuccess : " + isSuccess);
		// 글수정 실패시
		if (!isSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('글비밀번호가 틀려요');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;

		}

		// 페이지번호 파라미터 가져오기
		String pageNum = request.getParameter("pageNum");

		// 글수정 성공시 수정한 글이 있는 페이지번호로 글목록 요청
		ActionForward forward = new ActionForward();
		forward.setPath("boardList.do?pageNum=" + pageNum);
		forward.setRedirect(true);
		return forward;

	}

}
