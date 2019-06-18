package action.gestBook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dao.GuestbookDAO;
import vo.ActionForward;
import vo.Guestbook;
 

 
public class GuestbookReplyFormAction implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
    
        // 부모글의 글번호를 가져온다.
        int guestbook_no = Integer.parseInt(request.getParameter("num"));
        // 답글 작성 후 원래 페이지로 돌아가기 위해 페이지 번호가 필요하다.
        String pageNum = request.getParameter("page");
 
        // 부모 방명록의 정보를 DB에서 가져온다
        GuestbookDAO dao = GuestbookDAO.getInstance();
        Guestbook guestbook = dao.getGuestbook(guestbook_no);
        
        // 방명록 정보와 페이지 번호를 request에 세팅한다.
        request.setAttribute("guestbook", guestbook);
        request.setAttribute("pageNum", pageNum);
        
    
        forward.setPath("guestbook/GuestbookReplyForm");
        forward.setRedirect(false);
        return forward;
    }
}



