package service.memberUser;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.memberUser.MemberDao;
import dao.office.OfficeDto;
import service.CommandProcess;

public class UserPicksListAction implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			MemberDao memDao = MemberDao.getInstance();
		
		try {
			OfficeDto office = new OfficeDto();	
			String pageNum = request.getParameter("pageNum");
			String mbr_id = request.getParameter("mbr_id");
			String ofc_code = request.getParameter("ofc_code");
			String ofctype_code = request.getParameter("ofctype_code");
			
			
			List<OfficeDto> userPicksList = memDao.userPicksList(mbr_id);
			System.out.println("typeList -> " + userPicksList.size());
			request.setAttribute("userPicksList", userPicksList);
			request.setAttribute("ofc_code", ofc_code);
			request.setAttribute("ofctype_code", ofctype_code);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("office", office);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "memberUser/userPicksList.jsp";
	
	}

}
