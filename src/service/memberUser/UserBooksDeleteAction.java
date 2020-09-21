package service.memberUser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.memberUser.MemberDao;
import service.CommandProcess;

public class UserBooksDeleteAction implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		try {
			String pageNum = request.getParameter("pageNum");
			int bks_idx = Integer.parseInt(request.getParameter("bks_idx"));
			String mbr_id = request.getParameter("mbr_id");
			MemberDao memDao = MemberDao.getInstance();
			int result = memDao.delete_Books(bks_idx, mbr_id);
			request.setAttribute("result", result);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("bks_idx", bks_idx);
			request.setAttribute("mbr_id", mbr_id);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "memberUser/userBooksDelete.jsp";
	}

}
