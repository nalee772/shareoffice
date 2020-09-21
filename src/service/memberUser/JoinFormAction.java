package service.memberUser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommandProcess;

public class JoinFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
		
			System.out.println("JoinFormAction start...");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "memberUser/joinForm.jsp";
	}

}
