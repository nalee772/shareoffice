package service.office;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.office.OfficeDao;
import service.CommandProcess;

public class OfficeDeleteAction implements CommandProcess {

	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String pageNum = request.getParameter("pageNum");
			int ofc_code = Integer.parseInt(request.getParameter("ofc_code"));
			int ofctype_code = Integer.parseInt(request.getParameter("ofctype_code"));
			OfficeDao od = OfficeDao.getInstance();
			int result = od.delete(ofc_code, ofctype_code);
			request.setAttribute("result", result);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("ofc_code", ofc_code);
			request.setAttribute("ofctype_code", ofctype_code);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return "office/officeDelete.jsp";
	}

}
