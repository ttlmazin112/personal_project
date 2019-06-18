package action.gestBook;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dao.GuestbookDAO;
import vo.ActionForward;
import vo.Guestbook;
 

 
public class GuestbookReplyProcess implements Action
{
    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        request.setCharacterEncoding("utf-8");
        ActionForward forward = new ActionForward();
    
        GuestbookDAO dao = GuestbookDAO.getInstance();
        Guestbook guestbook = new Guestbook();
        
        // 답글 작성 후 원래 페이지로 돌아가기 위해 페이지 번호가 필요하다.
        String pageNum = request.getParameter("page");
    
        // 파리미터를 가져온다.
        int guestbook_no = Integer.parseInt(request.getParameter("guestbook_no"));
        String guestbook_id = request.getParameter("guestbook_id");
        String guestbook_password = request.getParameter("guestbook_password");
        String guestbook_content = request.getParameter("guestbook_content");
        int guestbook_group = Integer.parseInt(request.getParameter("guestbook_group"));
 
        
        int seqNum = dao.getSeq();
        System.out.println("seqNum : " + seqNum);
        
        guestbook.setGuestbook_no(seqNum);
        guestbook.setGuestbook_id(guestbook_id);
        guestbook.setGuestbook_password(guestbook_password);
        guestbook.setGuestbook_content(guestbook_content);
        guestbook.setGuestbook_group(guestbook_group);
        guestbook.setGuestbook_parent(guestbook_no); // 부모 방명록의 글번호를 세팅
        
       boolean result = dao.guestbookInsert(guestbook);
        
            if(result) {
                   
            forward.setPath("GuestbookListAction.do?page="+pageNum);
            forward.setRedirect(true);
            }
        return forward;
    }
}
