package service.memberUser;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.books.BooksDto;
import dao.memberUser.MemberDao;
import service.CommandProcess;

public class UserBooksListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			MemberDao memDao = MemberDao.getInstance();
		
		try {
			BooksDto books = new BooksDto();					
			String mbr_id = request.getParameter("mbr_id");
			String bks_idx = request.getParameter("bks_idx");
			String pageNum = request.getParameter("pageNum");
			
			
			List<BooksDto> userBooksList = memDao.userBooksList(mbr_id);
			System.out.println("typeList -> " + userBooksList.size());
			request.setAttribute("userBooksList", userBooksList);
			request.setAttribute("bks_idx", bks_idx);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("books", books);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "memberUser/userBooksList.jsp";
	}

}
