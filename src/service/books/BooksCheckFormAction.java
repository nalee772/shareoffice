package service.books;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.books.BooksDao;
import dao.books.BooksDto;
import dao.office.OfficeDto;
import service.CommandProcess;

public class BooksCheckFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			System.out.println("BooksAction start...");
			BooksDto books = new BooksDto();
			OfficeDto office = new OfficeDto();
			
			int ofc_code = Integer.parseInt(request.getParameter("ofc_code"));
			System.out.println("BooksCheckFormAction start..."+request.getParameter("ofc_code"));
			int ofctype_code = Integer.parseInt(request.getParameter("ofctype_code"));
			System.out.println("BooksCheckFormAction start..."+request.getParameter("ofctype_code"));			
			
			office.setOfc_region(request.getParameter("ofc_region"));			
			office.setOfctype_name(request.getParameter("ofctype_name"));		
			office.setOfc_tel(request.getParameter("ofc_tel"));
			office.setOfc_location(request.getParameter("ofc_location"));
			office.setOfc_name(request.getParameter("ofc_name"));			
			System.out.println("BooksCheckFormAction 8 mbr_id=>"+ request.getParameter("mbr_id"));
			office.setPicture1(request.getParameter("picture1"));
			office.setPicture2(request.getParameter("picture2"));
			
			
			
			books.setMbr_id(request.getParameter("mbr_id"));
			books.setOfc_code(ofc_code);
			books.setOfctype_code(ofctype_code);
			books.setBks_count(Integer.parseInt(request.getParameter("bks_count")));
			books.setBks_fromdate(request.getParameter("bks_fromdate"));
			books.setBks_todate(request.getParameter("bks_todate"));
			
			
		
			BooksDao bd = BooksDao.getInstance();
			int result = bd.insert(books);								
			
			request.setAttribute("office", office);
			request.setAttribute("books", books);
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "books/booksCheckForm.jsp";
	}
}
