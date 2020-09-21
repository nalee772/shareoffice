package service.memberUser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import dao.memberUser.LoginUser;
import dao.memberUser.MemberDao;
import dao.memberUser.MemberDto;
import service.CommandProcess;

public class UserUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		//PrintWriter out = response.getWriter();
		System.out.println("userUpdateProAction START....");

		try {
			String mbr_id = request.getParameter("mbr_id");
			String mbr_email = request.getParameter("mbr_email");
			String mbr_name = request.getParameter("mbr_name");
			String mbr_pw = request.getParameter("mbr_pw");
			String mbr_tel = request.getParameter("mbr_tel");
			String mbr_ag_email = request.getParameter("mbr_ag_email");
			if (mbr_ag_email == null) {
				mbr_ag_email = "0";
			}
			String mbr_ag_sms = request.getParameter("mbr_ag_sms");
			if (mbr_ag_sms == null) {
				mbr_ag_sms = "0";
			}
			int mbr_level = Integer.parseInt(request.getParameter("mbr_level"));
						
			MemberDao memDao = MemberDao.getInstance();
			MemberDto member = new MemberDto();
			LoginUser user = new LoginUser();
			HttpSession session = request.getSession(); 	//세션을 다시 만들어주면
			
			

			member.setMbr_id(mbr_id);
			member.setMbr_email(mbr_email);
			member.setMbr_name(mbr_name);
			member.setMbr_pw(mbr_pw);
			member.setMbr_tel(mbr_tel);
			member.setMbr_ag_email(mbr_ag_email);
			member.setMbr_ag_sms(mbr_ag_sms);
			
								
			user.setMbr_id(mbr_id); 
			user.setMbr_email(mbr_email);
			user.setMbr_name(mbr_name); 
			user.setMbr_pw(mbr_pw); 
			user.setMbr_tel(mbr_tel);
			user.setMbr_ag_email(mbr_ag_email); 
			user.setMbr_ag_sms(mbr_ag_sms);
			user.setMbr_level(mbr_level);
			

			session.setAttribute("user", user);			//user 객체의 세션값을 다시 불러옴

			int result = memDao.userInfoUpdate(member); //member객체 회원정보 업데이트
			request.setAttribute("result", result);
			
			

			
			if (result > 0) {		
				System.out.println("회원정보 수정 성공");
			} else {
				System.out.println("회원정보 수정 실패");
			
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "memberUser/userUpdatePro.jsp";
	}

}
