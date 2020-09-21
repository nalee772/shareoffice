package service.memberAdmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.books.BooksDto;
import dao.memberAdmin.MemberAdminDao;
import dao.memberUser.MemberDto;
import service.CommandProcess;

public class BooksSearchAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null || pageNum.equals("")) pageNum = "1";
			String bookSearch = request.getParameter("bookSearch");
			MemberAdminDao mad = MemberAdminDao.getInstance();
			List<BooksDto> listB = mad.Bsearch(bookSearch);
			int size = listB.size();
			System.out.println("listB.size->"+listB.size());
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("listB", listB);
			request.setAttribute("size", size);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} return "memberAdmin/booksSearch.jsp";
	}

}
