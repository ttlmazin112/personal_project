package action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import action.Action;
import dao.MemberDao;
import vo.ActionForward;

public class JoinIdCheckJsonAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ID 중복검사할 userid 파라미터 가져오기
		String userid = request.getParameter("userid");

		MemberDao memberDao = MemberDao.getInstance();
		int count = memberDao.countById(userid);
		
		boolean isDuplicated = false;
		if(count > 0) {
			isDuplicated = true;
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();

		//1) JSON List컬렉션
		//out.println("[" + isDuplicated + ", " + "]"); 불편해서 이렇게 안씀!
		
//		JSONArray jsonArray = new JSONArray();
//		jsonArray.add(isDuplicated);//인덱스 0
//		jsonArray.add(10); //인덱스1
//		jsonArray.add("문자열"); //인덱스2
//		out.println(jsonArray);
//		System.out.println(jsonArray);

		//2) JSON Map컬렉션
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("isDuplicated",isDuplicated);
//		jsonObject.put("number",10);
//		out.println(jsonObject);
//		System.out.println(jsonObject);
		
		//3)자바스크립트 기본타입으로 전송
		out.println(isDuplicated);
		System.out.println(isDuplicated);
		
		out.close();
		return null;
	}

}
