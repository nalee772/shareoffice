package service.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.memberUser.LoginUser;
import service.CommandProcess;

public class MainFormAction implements CommandProcess{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			LoginUser user = new LoginUser();
			
			System.out.println("MainFormAction start...");


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}  

		return "main/main.jsp";
	}
}

