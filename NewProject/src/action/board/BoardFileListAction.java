package action.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dao.BoardDao;
import vo.ActionForward;
import vo.Board;

public class BoardFileListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 페이지 번호 파라미터 가져오기
				/*
				한 페이지 당 레코드(행) 3건씩 가져오기.
				페이지 번호         시작행번호 (0부터 시작)
				    1          ->        0
				    2          ->        3
				    3          ->        6
				    4          ->        9
				 시작행번호 = (페이지번호-1) * 3;
				*/
				// 페이지번호 파라미터 가져오기
				String strPageNum = request.getParameter("pageNum"); // 페이지번호
				if (strPageNum == null) {
					strPageNum = "1"; // 페이지번호 기본값 1로 설정
				}
				int pageNum = Integer.parseInt(strPageNum); // 페이지번호를 정수로 변환
				
				// 검색어 파라미터 가져오기
				// 검색 요청을 안하면(검색 버튼을 안눌렀을시) null 값 리턴
				String search = request.getParameter("search");
				if(search == null) {
					search ="";
				}
				
				// =========================================
				//  한 페이지에 해당하는 글목록 구하기 작업
				// =========================================
				int pageSize = 5; // 한 페이지 당 보여줄 글(레코드) 갯수
				int startRow = (pageNum-1) * pageSize; // 페이지의 시작행번호
				
				// DB객체 준비
				BoardDao boardDao = BoardDao.getInstance();
				
				// getBoards() 호출해서 글목록 데이터를 list로 가져오기
				List<Board> list = boardDao.getBoards(startRow, pageSize,search);
				
				
				// =========================================
				//  페이지 블록 구하기 작업
				// =========================================
				int allRowCount = 0; // 전체 행 갯수
				if(search == null || search.equals("")) {
					
					allRowCount = boardDao.getBoardCount();
				}else {
					allRowCount = boardDao.getBoardCount(search);
				}			
				
				int maxPage = allRowCount / pageSize + (allRowCount % pageSize == 0 ? 0 : 1);
				// 1페이지 ~ maxPage 페이지까지 존재함.
				// -> 페이지 블록단위로 끊어줌

				// 시작페이지번호(1)             끝페이지번호(10)
				// 1 2 3 4 5 6 7 8 9 10             -- [블록구성 10개]
				
				// 시작페이지번호(11)             끝페이지번호(20)
				// 11 12 13 14 15 16 17 18 19 20   -- [블록구성 10개]
				
				// 시작페이지번호(21)             끝페이지번호(23)
				// 21 22 23                         -- [블록구성 3개]
				
				// 한 페이지블록을 구성하는 페이지갯수
				int pageBlockSize = 2;
				
				// 시작페이지번호 구하기
				int startPage = ((pageNum/pageBlockSize) - (pageNum%pageBlockSize==0 ? 1 : 0)) * pageBlockSize + 1;
				// 끝페이지번호 구하기
				int endPage = startPage + pageBlockSize - 1;
				if (endPage > maxPage) { // 마지막 블록에서 끝페이지번호 구하기
					endPage = maxPage;
				}
				
				Map<String, Integer> pageInfoMap = new HashMap<>();
				pageInfoMap.put("startPage", startPage); // 시작페이지번호
				pageInfoMap.put("endPage", endPage);     // 끝페이지번호
				pageInfoMap.put("pageBlockSize", pageBlockSize);//페이지블록크기
				pageInfoMap.put("maxPage", maxPage);     // 전체 페이지수
				pageInfoMap.put("allRowCount", allRowCount); // 전체 행갯수
				pageInfoMap.put("pageNum", pageNum); // 사용자가 요청한 페이지번호
				
				// 글목록 데이터 list를 request 영역객체에 저장
				request.setAttribute("list", list); //글목록 list
				request.setAttribute("pageInfoMap", pageInfoMap); //페이지블록 출력관련 데이터
				
				if (search == null) {
					search = "";
				}
				
				request.setAttribute("search", search); // 검색어
				
				ActionForward forward = new ActionForward();
				forward.setPath("center/fileNotice");
				forward.setRedirect(false);
				return forward;
	}

}
