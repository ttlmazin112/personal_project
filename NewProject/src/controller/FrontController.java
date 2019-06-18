package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionFactory;
import vo.ActionForward;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// http://localhost:80/funweb-mvc/main.do
		System.out.println("요청받음");

		// 1) 사용자 요청정보(명령command) 확인
		String requestURI = request.getRequestURI();
		System.out.println("URI주소: " + requestURI);
		// URI주소: /funweb-mvc/main.do

		String contextPath = request.getContextPath();
		System.out.println("contextPath: " + contextPath);
		// contextPath: /funweb-mvc

		String command = requestURI.substring(contextPath.length());
		System.out.println("command: " + command);
		// command: /main.do

		int index = command.lastIndexOf(".");
		command = command.substring(1, index);
		System.out.println("명령어command: " + command);
		// 명령어command: main

		// 2) 확인된 요청(명령어)을 처리하고,
		// 응답할 뷰(jsp)를 선택함 또는
		// 새로운 요청명령어를 선택함

		// -브라우저가 요청할 명령어 : sendRedirect
		// -서버가 실행할 뷰(jsp) : requestDispatching

		// 모델2에서 JSP를 사용하는 용도로
		// sendRedirect 대신 requestDispatching 방식 사용함.

		// 보호된 WEB-INF 폴더 내의 컨텐츠 직접요청 가능여부 확인
		// ./WEB-INF/views/member/joinForm.jsp (리다이렉트 X)
		// ./member/joinForm.jsp (리다이렉트 O)
		// response.sendRedirect("./member/joinForm.jsp");

		// 회원가입 페이지 응답주기
//		RequestDispatcher dispatcher
//			= request.getRequestDispatcher("WEB-INF/views/member/joinForm.jsp");
//		dispatcher.forward(request, response);

		Action action = null;
		ActionForward forward = null;
		
		ActionFactory factory = ActionFactory.getInstance();
		action = factory.getAction(command);
		if (action != null) {
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		// 3) 선택된 뷰(JSP)를 실행
		// 이동
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				String path = "WEB-INF/views/" + forward.getPath() + ".jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(path);
				dispatcher.forward(request, response); // 핵심!
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글처리 먼저하고 doGet()을 호출함
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
