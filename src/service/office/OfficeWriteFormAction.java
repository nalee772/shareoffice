	package service.office;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.office.OfficeDto;
import dao.office.OfficeDao;
import service.CommandProcess;

public class OfficeWriteFormAction implements CommandProcess {

	
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pageNum = request.getParameter("pageNum");
			if (pageNum == null) pageNum = "1";
			/*Officedao od = Officedao.getInstance();
			Office office = od.select(ofc_code);
		
			ofc_code = office.getOfc_code();
			String ofc_name = office.getOfc_name();
			int ofctype_code = office.getOfctype_code();
			String ofc_location = office.getOfc_location();
			String ofc_tel = office.getOfc_tel();

			request.setAttribute("ofc_name", ofc_name);
			request.setAttribute("ofctype_code", ofctype_code);
			request.setAttribute("ofc_location", ofc_location);
			request.setAttribute("ofc_tel", ofc_tel);

			request.setAttribute("pageNum", pageNum);*/
		} catch(Exception e) { System.out.println(e.getMessage()); }
		
		return "office/officeWriteForm.jsp";
	}

}
