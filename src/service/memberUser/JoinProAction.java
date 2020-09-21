package service.memberUser;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.memberUser.MemberDao;
import dao.memberUser.MemberDto;
import service.CommandProcess;

public class JoinProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

			MemberDao memDao = MemberDao.getInstance();
			MemberDto member = new MemberDto();

			member.setMbr_id(mbr_id);
			member.setMbr_email(mbr_email);
			member.setMbr_name(mbr_name);
			member.setMbr_pw(mbr_pw);
			member.setMbr_tel(mbr_tel);
			member.setMbr_ag_email(mbr_ag_email);
			member.setMbr_ag_sms(mbr_ag_sms);

			int result = memDao.join(member);

			if (result > 0) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();		
				out.println("<script>alert('회원가입을 환영합니다 로그인을 해주세요');location.href='loginForm.do';</script>");
				System.out.println("회원가입 성공");

			} else {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('회원정보를 다시 입력해주세요');location.href='joinForm.do';</script>");		
				System.out.println("회원정보를 다시 입력해주세요");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return null;
	}

}
