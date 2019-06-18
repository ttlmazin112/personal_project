package action.gestBook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;
 

 
public class GuestbookDeleteFormAction implements Action
{
 
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();   
        forward.setPath("guestbook/GuestbookDeleteForm");
        forward.setRedirect(false);
        return forward;
    }
}


