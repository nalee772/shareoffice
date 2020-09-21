package service.memberAdmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.memberAdmin.MemberAdminDao;
import dao.memberUser.MemberDto;
import service.CommandProcess;

public class MemSearchAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null) pageNum = "1";
			String memSearch = request.getParameter("memSearch");
			MemberAdminDao mad = MemberAdminDao.getInstance();
			List<MemberDto> listS = mad.search(memSearch);
			int size = listS.size();
			System.out.println("listS.size->"+listS.size());
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("listS", listS);
			request.setAttribute("size", size);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} return "memberAdmin/memSearch.jsp";
	}

}
