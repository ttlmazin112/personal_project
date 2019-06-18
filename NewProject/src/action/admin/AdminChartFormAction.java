package action.admin;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class AdminChartFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminChartFormAction==========");
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("admin/adminChart");
		
		return forward;
	}

}
