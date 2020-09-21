package service.memberAdmin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.memberAdmin.MemberAdminDao;
import service.CommandProcess;

public class MemBanProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int result = 0;
		try {
			String pageNum = request.getParameter("pageNum");
			String adminPasswd = request.getParameter("adminPasswd");
			/*String admin_mbr_id = "nalee";*/
			String admin_mbr_id = request.getParameter("admin_mbr_id");
			System.out.println("BanProAction admin_mbr_id===>"+ admin_mbr_id);
			MemberAdminDao mad = MemberAdminDao.getInstance();
			String mbr_id = request.getParameter("mbr_id");
			System.out.println("BanProAction mbr_id===>"+ mbr_id);
			result = mad.banish(mbr_id,admin_mbr_id,adminPasswd);
			

			request.setAttribute("pageNum", pageNum);
			request.setAttribute("mbr_id", mbr_id);
			request.setAttribute("result", result);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("MemBanFormAction Err->" + e.getMessage());
		} 
		return "memberAdmin/memBanPro.jsp";
	}

}
