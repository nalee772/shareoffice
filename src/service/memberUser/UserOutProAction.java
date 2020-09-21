package service.memberUser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.memberUser.MemberDao;
import service.CommandProcess;

public class UserOutProAction implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		System.out.println("UserOutPro start........");
	
		try {
			
			
			String mbr_id = request.getParameter("mbr_id");
			String mbr_pw = request.getParameter("mbr_pw");
			
			
			MemberDao memDao = MemberDao.getInstance();				
			int result = memDao.userOut(mbr_id,mbr_pw);
			
			request.setAttribute("result", result);			
			request.setAttribute("mbr_id", mbr_id); 
			//request.setAttribute("mbr_pw", mbr_pw);
		

			/*
			 * HttpSession session = request.getSession(); session.invalidate();
			 */
			if (result > 0) {		
				HttpSession session = request.getSession(); 
				session.invalidate();
				System.out.println("회원탈퇴 성공");
			
			} else {
				System.out.println("회원탈퇴 실패");
				System.out.println("mbr_pw-->"+mbr_pw);
			
			}
			
		}catch (Exception e) {		
			System.out.println(e.getMessage());
		}
		
		return "memberUser/userOutPro.jsp";
		
		
		
	}

}
