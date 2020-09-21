package service.memberUser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.memberUser.MemberDao;
import service.CommandProcess;

public class FindPwProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			System.out.println("FindPwProAction start...");
			
			String mbr_id = request.getParameter("mbr_id");
			String mbr_email = request.getParameter("mbr_email");
			
			MemberDao md = MemberDao.getInstance();
			int result = md.findPw(mbr_id, mbr_email);
			int result2 = 0;
			if(result>0) {
				String tempPw = md.randomPw(5);
			
				request.setAttribute("tempPw", tempPw);
				System.out.println("FindPwProAction tempPw..." + tempPw);
				request.setAttribute("result", result);
				System.out.println("FindPwProAction result>0 result =>" + result);
				result2 = md.updateRPw(mbr_id, tempPw);
				request.setAttribute("result2", result2);
				System.out.println("FindPwProAction result>0 result2 =>" + result2);
				
				
			} else {
				request.setAttribute("result", result);
				System.out.println("FindPwProAction else result =>" + result);
			}
					
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "memberUser/findPwPro.jsp";
	}

}
