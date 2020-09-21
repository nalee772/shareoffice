package service.memberUser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.memberUser.LoginUser;
import dao.memberUser.MemberDao;
import service.CommandProcess;

public class LoginProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	//	String rtnString = "";

		try {
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			//PrintWriter out = response.getWriter();
			System.out.println("LoginFormAction start...");
			
			HttpSession session = request.getSession();	//세션생성
			String mbr_id = request.getParameter("mbr_id");
			String mbr_pw = request.getParameter("mbr_pw");
			
			
			
			MemberDao memDao = MemberDao.getInstance(); // 싱글톤
			LoginUser user = memDao.login(mbr_id, mbr_pw);	//로그인유저 객체에 login dao정보 저장

			if (user.getResult()== 1) { //유저 객체안의 값이 1이면 (=값이 들어있으면)
				//session.setAttribute("mbr_id", mbr_id); // 세션저장
				request.setAttribute("result", 1); //로그인 성공값 1 set 
				session.setAttribute("user", user); //로그인 유저정보 세션 저장	
				// response.sendRedirect("member/index-log.jsp");// 세션전송
				System.out.println("로그인 성공");
				int mbr_level = user.getMbr_level();
				session.setAttribute("mbr_level", mbr_level);

			//	rtnString = "member/index-log.jsp";
			/*} else if (user.getResult() == 0) {
				System.out.println("비밀번호가 틀렸습니다");
				*/
			//	rtnString = "member/loginForm.do";				
				// 

			}else{
				System.out.println("비밀번호가 틀렸습니다");
				request.setAttribute("result", 0); //로그인 실패값 0 set
				//rtnString = "member/loginForm.do";
				// out.println("<script>alert('없는 아이디 입니다');</script>");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "memberUser/loginPro.jsp";
	}
}
