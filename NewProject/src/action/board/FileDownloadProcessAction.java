package action.board;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class FileDownloadProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FileDownloadProcessAction");
		
		String filename = request.getParameter("filename");
		
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/upload");
		
		File file = new File(realPath, filename);
		
		if (!file.exists()) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('다운로드할 파일이 존재하지 않습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		
		// 입력스트림 준비
		BufferedInputStream input = null;
		input = new BufferedInputStream(new FileInputStream(file));
		
		
		
		String mimeType =  application.getMimeType(file.getPath());
		System.out.println("mimeType : " + mimeType);
		
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		
		response.setContentType(mimeType);
		
		String agent = request.getHeader("User-Agent");
		
		boolean ieBrowser = agent.indexOf("MSIE") > -1 || agent.indexOf("Trident") > -1;
		if (ieBrowser) {
			filename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else {
			filename = new String(filename.getBytes("UTF-8"), "iso-8859-1");
		}
		System.out.println("encoded filename: " + filename);
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		
		// 출력스트림 준비
		ServletOutputStream out = null;
		out = response.getOutputStream();
		
		int data = 0;
		while ((data = input.read()) != -1) {
			out.write(data);
		}
		
		out.close();
		input.close();
		
		return null;
	}

}
