package service.office;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.office.OfficeDto;
import dao.office.OfficeDao;
import service.CommandProcess;

public class OfficeContentAction implements CommandProcess {

	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String pageNum = request.getParameter("pageNum");
			int ofc_code = Integer.parseInt(request.getParameter("ofc_code"));
			int ofctype_code = Integer.parseInt(request.getParameter("ofctype_code"));
			
			System.out.println("OfficeContentAction ofc_code->"+ofc_code);
			System.out.println("OfficeContentAction ofctype_code->"+ofctype_code);
			System.out.println("OfficeContentAction pageNum->"+pageNum);
			OfficeDao od = OfficeDao.getInstance();
			OfficeDto office = od.select(ofc_code,ofctype_code );
			System.out.println("OfficeContentAction office.getOfc_code->"+office.getOfc_code());
			System.out.println("OfficeContentAction office.getOfc_name->"+office.getOfc_name());
			
			request.setAttribute("ofc_code", ofc_code);
			request.setAttribute("ofctype_code", ofctype_code);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("office", office);
			
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "office/officeContent.jsp";
	}

}