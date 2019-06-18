package action.admin;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import vo.ActionForward;


public class AdminMultiEmailActionProcess implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	   long beginTime = System.currentTimeMillis();

	   /*(1) 업로드 작업 
	   1. request

	   2. upload 폴더 만들고 물리적 경로*/
	   ServletContext application=request.getServletContext();
	   String realPath = application.getRealPath("/temp");
	   System.out.println("realPath : "+realPath);

	   //3. 파일업로드 최대크기 제한값 
	   int maxPostSize = 1024 * 1024 * 10; //1024byte * 1024 * 10 (업로드 10MB 제한)

	   //4. 한글처리  유니코드로 설정 "utf-8"

	   //5. 업로드하는 파일명이 기존 업로드된 파일명과 같은 경우 => 파일명 자동변경 정책

	   //파일업로드 처리 완료!
	   MultipartRequest multi = new MultipartRequest(request, realPath, maxPostSize,"utf-8", new DefaultFileRenamePolicy());

	   String address = multi.getParameter("address");
	   String subject = multi.getParameter("subject");
	   String content = multi.getParameter("content");

	   //업로드한 파일이름 가져오기 
	   String filename = multi.getFilesystemName("filename");

	   File file = new File(realPath, filename);
	   System.out.println("파일 경로: " + file.getAbsolutePath()); //파일의 절대경로
	   System.out.println("파일 경로 getCanonicalPath: " + file.getCanonicalPath()); // 파일의 절대경로


	   //파일첨부 이메일 전송 

	   //첨부파일 객체 준비 
	   EmailAttachment attachment = new EmailAttachment();
	   attachment.setPath(realPath + "/" + filename);
	   attachment.setDescription("첨부파일 설명");
	   attachment.setName(filename);

	   // MultiPartEmail 객체 생성
	   MultiPartEmail multiPartEmail = new MultiPartEmail();

	   multiPartEmail.setHostName("smtp.daum.net");
	   multiPartEmail.setSmtpPort(465);
	   multiPartEmail.setAuthentication("imbc112", "tkfkdgogkswlals9630");

	   multiPartEmail.setSSLOnConnect(true);
	   multiPartEmail.setStartTLSEnabled(true);

	   //보내는사람 설정
	   multiPartEmail.setFrom("imbc112@daum.net", "관리자", "utf-8");

	   //받는사람 설정
	   
	   multiPartEmail.addTo(address, "받는사람이름", "utf-8");

	   //받는사람(참조인) 설정
	   //simpleEmail.addCc(receiver, "참조인 이름", "utf-8");
	   //받는사람(숨은참조인) 설정
	   //simpleEmail.addBcc(receiver, "숨은참조인 이름", "utf-8");

	   //이메일제목 설정
	   multiPartEmail.setSubject(subject);
	   //이메일내용 설정
	   multiPartEmail.setMsg(content);
	   //첨부파일 추가 
	   multiPartEmail.attach(attachment); //add로 동작


	   //* 이메일 전송
	   String str = multiPartEmail.send();

	   long execTime =System.currentTimeMillis() - beginTime;
	   System.out.println("이메일 전송에 걸린시간: " + execTime + "밀리초");

	   file.delete(); //첨부파일에 사용된 파일 삭제
	   
	   if(str!=null)

	   response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('성공적으로 발송하였습니다.');");
		out.println("history.back();");
		out.println("</script>");
		out.close();
		
		return null;
      
      

	}
	
}
