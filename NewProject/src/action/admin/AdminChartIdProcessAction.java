package action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import action.Action;
import dao.BoardDao;
import vo.ActionForward;

public class AdminChartIdProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDao bDao = BoardDao.getInstance();

		JSONArray jArray = bDao.getCountPerID();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(jArray);
		
		return null;
	}

}
