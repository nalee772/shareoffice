package service.office;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.office.OfficeDto;
import dao.office.OfficeDao;
import service.CommandProcess;

public class OfficeUpdateFormAction implements CommandProcess {

	
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int ofc_code = Integer.parseInt(request.getParameter("ofc_code"));
			int ofctype_code = Integer.parseInt(request.getParameter("ofctype_code"));
			String pageNum = request.getParameter("pageNum");
			OfficeDao od = OfficeDao.getInstance(); // getInstance() 객체를 생성해주는 메소드
			OfficeDto office = od.select(ofc_code, ofctype_code);
			
			
			request.setAttribute("ofc_code", ofc_code);
			request.setAttribute("ofctype_code", ofctype_code);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("office", office);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		return "office/officeUpdateForm.jsp";
	}

}
