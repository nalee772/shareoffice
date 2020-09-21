package service.memberUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.books.BooksDto;
import dao.memberUser.MemberDao;
import dao.office.OfficeDao;
import dao.office.OfficeDto;
import dao.picks.PicksDto;
import service.CommandProcess;

public class PickProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			System.out.println("PicksProAction start...");

			String mbr_id = request.getParameter("mbr_id");
			int ofc_code = Integer.parseInt(request.getParameter("ofc_code"));
			int ofctype_code = Integer.parseInt(request.getParameter("ofctype_code"));
			String pageNum = request.getParameter("pageNum");

			PicksDto picks = new PicksDto();
			MemberDao memDao = MemberDao.getInstance();

			picks.setMbr_id(mbr_id);
			picks.setOfc_code(ofc_code);
			picks.setOfctype_code(ofctype_code);

			int result = memDao.picksInsert(picks);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("result", result);

			if (result > 0) {				
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();		
				out.println("<script>alert('찜목록에 추가되었습니다');location.href='booksSearchForm.do';</script>");
				System.out.println("찜목록 추가 성공");

			} else {			
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('이미 추가된 매장입니다');history.go(-1);</script>");		
				System.out.println("찜목록 추가 실패");	
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
