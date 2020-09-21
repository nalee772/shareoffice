package service.memberUser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.memberUser.MemberDao;
import service.CommandProcess;

public class FindIdProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			System.out.println("FindIdProAction start...");
			
			String mbr_email = request.getParameter("mbr_email");
			String mbr_name = request.getParameter("mbr_name");
			System.out.println("mbr_email==>"+mbr_email);
			System.out.println("mbr_name==>"+mbr_name);
			
			MemberDao md = MemberDao.getInstance();
			String mbr_id = md.findId(mbr_email, mbr_name );
			
			int result = 1;
			if(mbr_id.equals("") || mbr_id == null) result = 0; 
			
			request.setAttribute("mbr_id", mbr_id);
			System.out.println("mbr_id==>"+mbr_id);
			request.setAttribute("result", result);
			
		} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		
		return "memberUser/findIdPro.jsp";
	}

}
