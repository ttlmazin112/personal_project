package action;

import java.util.HashMap; 
import java.util.Map;

import action.member.*;
import action.admin.AdminChartFormAction;
import action.admin.AdminChartIdProcessAction;
import action.admin.AdminEmailAction;
import action.admin.AdminMultiEmailActionProcess;
import action.admin.MemberDelActionProcess;
import action.api.ApiMap;
import action.board.*;
import action.gestBook.GestbookController;
import action.gestBook.GestbookFormAction;
import action.gestBook.GuestbookDeleteActionProcess;
import action.gestBook.GuestbookDeleteFormAction;
import action.gestBook.GuestbookListAction;
import action.gestBook.GuestbookPwCheckAction;
import action.gestBook.GuestbookReplyFormAction;
import action.gestBook.GuestbookReplyProcess;
import action.gestBook.GuestbookUpdateActionProcess;
import action.gestBook.GuestbookUpdateFormAction;
import action.gestBook.GuestbookWriteAction;



public class ActionFactory {
	//싱글톤 패턴 적용
	private static ActionFactory factory = new ActionFactory();
	
	public static ActionFactory getInstance() {
		return factory;
	}


	private Map<String, Action> map;
	// 싱글톤에서 생성자는 private 접근제한자 적용
	private ActionFactory() {
		map = new HashMap<>();
		//회원관리 명령어 관리 
		map.put("main", new MainAction());
		map.put("welcome", new WelcomeAction());
		map.put("investment", new Investment());
		map.put("investment2", new Investment2());
		map.put("book", new book());
		map.put("joinForm", new JoinFormAction());
		map.put("joinprocess", new JoinProcessAction());
		map.put("joinIdCheck", new JoinldCheckAction());
		map.put("loginForm", new LoginFormAction());
		map.put("loginProcess", new LoginProcessAction());
		map.put("logout", new LogoutAction());
		map.put("joinIdCheckJson", new JoinIdCheckJsonAction());
		map.put("member_list", new MemberListAction());
		map.put("MemberDeleteAction", new MemberDeleteAction());
		map.put("memberupdate", new MemberUpdateAction());
		map.put("MemberUpdateProcess", new MemberUpdateProcess());
		map.put("userInfo", new MemberInfoAction());
		map.put("MemberModifyAction", new MemberModifyAction());
		map.put("MemberModifyActionProcess", new MemberModifyActionProcess());
		map.put("UserDeleteAction", new UserDeleteAction());
		map.put("UserDelFormAction", new UserDelFormAction());
		
		
		//방명록 명령어 처리 등록
		map.put("GuestbookForm", new GestbookFormAction());
		map.put("GuestbookWriteAction", new GuestbookWriteAction());
		map.put("GuestbookListAction", new GuestbookListAction());
		map.put("GuestbookReplyForm", new GuestbookReplyFormAction());
		map.put("GuestbookReplyProcess", new GuestbookReplyProcess());
		map.put("GuestbookDeleteFormAction", new GuestbookDeleteFormAction());
		map.put("GuestbookDeleteActionProcess", new GuestbookDeleteActionProcess());
		map.put("GuestbookUpdateFormAction", new GuestbookUpdateFormAction());
		map.put("GuestbookPwCheckAction", new GuestbookPwCheckAction());
		map.put("GuestbookUpdateActionProcess", new GuestbookUpdateActionProcess());
		
		
		
		
		
//		map.put("memberList", new MemberListAction());
		
		//일반 게시판 명령어 처리 등록
		map.put("boardList", new BoardListAction());
		map.put("boardWriteForm", new BoardWriteFormAction());
		map.put("boardWriteProcess", new BoardWriteProcessAction());
		map.put("boardDetail", new BoardDetailAction());
		map.put("boardModifyForm", new BoardModifyFormAction());
		map.put("boardModifyProcess", new BoardModifyProcessAction());
		map.put("boardDeleteForm", new BoardDeleteFormAction());
		map.put("boardDeleteProcess", new BoardDeleteProcess());
		map.put("boardReplyForm", new BoardReplyFormAction());
		map.put("boardReplyProcess", new BoardReplyProcessAction());
		
		//파일 게시판 명령어 처리 등록
		map.put("boardFileList", new BoardFileListAction());
		map.put("boardFileWriteForm", new BoardFileWriteFormAction());
		map.put("boardFileWriteProcess", new BoardFileWriteProcessAction());
		map.put("boardFileDetail", new BoardFileDetailAction());
		map.put("boardFileDeleteForm", new BoardFileDeleteFormAction());
		map.put("boardFileDeleteProcess", new BoardFileDeleteProcessAction());
		map.put("boardFileModifyForm", new BoardFileModifyFormAction());
		map.put("boardFileModifyProcess", new BoardFileModifyProcessAction());
		map.put("fileDownloadProcess", new FileDownloadProcessAction());
		
		//admin 
		map.put("AdminEmailAction", new AdminEmailAction());
		map.put("AdminMultiEmailAction", new AdminMultiEmailActionProcess());
		map.put("AdminChartForm", new AdminChartFormAction());
		map.put("AdminChartId", new AdminChartIdProcessAction());
		map.put("MemberDelActionProcess", new MemberDelActionProcess());
		
		//api 
		map.put("apiMap", new ApiMap());
		
		
	}
	
	
	public Action getAction(String command) {
		/*Action action = null;
		if (command.equals("joinForm")) {
			action = new JoinFormAction();
		} else if (command.equals("joinProcess")) {
			action = new JoinProcessAction();
		} else if (command.equals("loginForm")) {
			action = new LoginFormAction();
		}
		return action;*/
		
		return map.get(command);
	} // getAction()
	
}
