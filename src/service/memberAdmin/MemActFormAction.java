package service.memberAdmin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.CommandProcess;

public class MemActFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pageNum = request.getParameter("pageNum");
			String admin_mbr_id = request.getParameter("admin_mbr_id");
			String mbr_id = request.getParameter("mbr_id");
			

			request.setAttribute("pageNum", pageNum);
			request.setAttribute("mbr_id", mbr_id);
			request.setAttribute("admin_mbr_id", admin_mbr_id);
			System.out.println("MemActForm pageNum==>>"+pageNum);
			System.out.println("MemActForm mbr_id==>>"+mbr_id);
			System.out.println("MemActForm admin_mbr_id==>>"+admin_mbr_id);

			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("MemActFormAction Err->" + e.getMessage());
		} 
		return "memberAdmin/memActForm.jsp";
	}

}
