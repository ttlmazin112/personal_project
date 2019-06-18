package action.board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import dao.BoardDao;
import vo.ActionForward;
import vo.Board;

public class BoardFileWriteProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// (1) 업로드 작업
		// 1 request
		
		// 2 upload 폴더 만들고 물리적 경로
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/upload");
		System.out.println("realPath : " + realPath);
		
		// 3 파일업로드 최대크기 제한값
		int maxPostSize = 1024 * 1024 * 10; // 1024byte * 1024 * 10; (업로드 10MB 제한)
		
		// 4 한글처리 유니코드로 설정 "utf-8"
		
		// 5 업로드하는 파일명이 기존 업로드된 파일명과 같은 경우 => 파일명 자동변경 정책
		
		// 파일업로드 처리 완료!
		MultipartRequest multi 
			= new MultipartRequest(request, 
								   realPath, 
								   maxPostSize, 
								   "utf-8", 
								   new DefaultFileRenamePolicy());
		
		
		// (2) DB작업
		// 파라미터값 가져와서 Board 객체로 만들기	
		// 업로드를 위해 기본request를 MultipartRequest로 변환함.
		// -> 파라미터 가져올때 변환된 MultipartRequest로 가져와야 함!
		Board board = new Board();
		board.setName(multi.getParameter("name"));
		board.setPass(multi.getParameter("pass"));
		board.setSubject(multi.getParameter("subject"));
		board.setContent(multi.getParameter("content"));
		
		String originalFilename = multi.getOriginalFileName("filename");
		System.out.println("원본 파일명: " + originalFilename);
		
		// 다중파일 업로드정보 가져오기
		String filenames = "";
		Enumeration enu = multi.getFileNames();
		while (enu.hasMoreElements()) {
			String fileParamName = (String) enu.nextElement();
			String filename = multi.getFilesystemName(fileParamName);
			if (filename != null) {
				filenames += filename + ":";
			}
		}
		// 마지막 콜론(:) 문자 제거해서 가져오기
		int index = filenames.lastIndexOf(":");
		filenames = filenames.substring(0, index);
		
		
		// 섬네일 이미지 생성하기
		String[] arrFilename = filenames.split(":");
		for (String strFilename : arrFilename) {
			// 이미지 파일인지 확인
			boolean isImage = strFilename.lastIndexOf("jpg") > -1
					|| strFilename.lastIndexOf("gif") > -1
					|| strFilename.lastIndexOf("png") > -1;
			// 이미지 파일이 맞으면 섬네일 생성
			if (isImage) {
				ParameterBlock pb = new ParameterBlock();
				pb.add(realPath + "/" + strFilename);
				RenderedOp op = JAI.create("fileload", pb);
				
				// 섬네일 생성하기위한 원본이미지 객체 준비
				BufferedImage bi = op.getAsBufferedImage();
				// 섬네일 출력결과객체를 버퍼로 준비
				BufferedImage thumb = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
				// 앞의 버퍼공간에 그릴수있는 그래픽객체를 리턴(그림판의 붓)
				Graphics2D g = thumb.createGraphics();
				// 준비된 버퍼공간 원본이미지(bi)를 그린다.
				g.drawImage(bi, 0, 0, 100, 100, null);
				// 출력 일정보를 file객체로 준비
				String thumbnailPath = application.getRealPath("/images/thumbnail");
				File thumbnailFile = new File(thumbnailPath + "/sm_" + strFilename);
				// 버퍼이미지를 해당경로의 파일에 jpg로 출력(쓰기)
				ImageIO.write(thumb, "jpg", thumbnailFile);
			} // if
		} // for
		
		
//		String realFilename = multi.getFilesystemName("filename");
//		System.out.println("실제 파일명: " + realFilename);
		
		board.setFilename(filenames); // 실제 파일명으로 저장
		
		// 글작성자 IP주소 값 저장
		board.setIp(request.getRemoteAddr());
		
		
		
		// DB작업 객체 준비 BoardDao
		BoardDao boardDao = BoardDao.getInstance();
		// 메소드 호출  insert(board) 글작성 완료
		boardDao.insert(board);
		// 글목록(boardList.do) 화면으로 이동. 리다이렉트
		ActionForward forward = new ActionForward();
		forward.setPath("boardFileList.do");
		forward.setRedirect(true);
		return forward;
	}

}
