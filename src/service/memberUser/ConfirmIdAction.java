package service.memberUser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.memberUser.MemberDao;
import service.CommandProcess;

public class ConfirmIdAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		
		try {
			String mbr_id = request.getParameter("mbr_id");
			
			MemberDao memDao = MemberDao.getInstance();
			int result = memDao.confirmId(mbr_id);
			
			request.setAttribute("result", result);
			
			if(result == 0) {
				System.out.println("사용 가능한 아이디 입니다.");
				request.setAttribute("mbr_id", mbr_id);
				
			} else {
				System.out.println("이미 존재하는 아이디 입니다.");
				request.setAttribute("mbr_id", mbr_id);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "memberUser/confirmId.jsp";
	}

}
