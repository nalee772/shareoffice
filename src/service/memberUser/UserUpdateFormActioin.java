package service.memberUser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.memberUser.LoginUser;
import dao.memberUser.MemberDao;
import dao.memberUser.MemberDto;
import service.CommandProcess;

public class UserUpdateFormActioin implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");	
			HttpSession session = request.getSession();
			System.out.println("userUpdateFormAction start...");
			
			String mbr_id = request.getParameter("mbr_id");
			String mbr_email = request.getParameter("mbr_email");
			String mbr_name = request.getParameter("mbr_name");
			String mbr_tel = request.getParameter("mbr_tel");
			
			
			
			MemberDao memDao = MemberDao.getInstance();
			MemberDto member = memDao.userInfoSelect(mbr_id);
			
			request.setAttribute("member", member);
			request.setAttribute("mbr_id", mbr_id); 
			request.setAttribute("mbr_email",mbr_email); 
			request.setAttribute("mbr_name", mbr_name);
			request.setAttribute("mbr_tel", mbr_tel);
			
			
			
						
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		return "memberUser/userUpdateForm.jsp";
	}

}
