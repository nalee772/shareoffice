package service.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommandProcess;

public class MainProAction  implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			
			System.out.println("로그인 성공---메인페이지 이동 MainProAction start...");


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "main/mainLogin.jsp";
	}

}
