package service.memberUser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.memberUser.MemberDao;
import service.CommandProcess;

public class UserPicksDeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pageNum = request.getParameter("pageNum");
			int ofc_code =Integer.parseInt(request.getParameter("ofc_code"));
			int ofctype_code =Integer.parseInt(request.getParameter("ofctype_code"));
			String mbr_id = request.getParameter("mbr_id");
			MemberDao memDao = MemberDao.getInstance();
			int result = memDao.delete_Picks(ofc_code, ofctype_code, mbr_id);
			request.setAttribute("result", result);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("mbr_id", mbr_id);
			request.setAttribute("ofc_code", ofc_code);
			request.setAttribute("ofctype_code", ofctype_code);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "memberUser/userPicksDelete.jsp";
	}

}
