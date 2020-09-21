package service.books;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.books.BooksDto;
import dao.office.OfficeDto;
import service.CommandProcess;

public class BooksFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			System.out.println("BooksFormAction start...");
			BooksDto books = new BooksDto();
			OfficeDto office = new OfficeDto();						
			office.setOfctype_name(request.getParameter("ofctype_name"));			
			office.setOfc_tel(request.getParameter("ofc_tel"));			
			office.setOfc_location(request.getParameter("ofc_location"));			
			office.setOfc_name(request.getParameter("ofc_name"));		
			books.setBks_fromdate(request.getParameter("bks_fromdate"));			
			books.setBks_todate(request.getParameter("bks_todate"));			
			books.setBks_count(Integer.parseInt(request.getParameter("bks_count")));			
			office.setOfc_code(Integer.parseInt(request.getParameter("ofc_code")));
			System.out.println("BooksFormAction ofc_code"+request.getParameter("ofc_code"));			
			int ofctype_code = Integer.parseInt(request.getParameter("ofctype_code"));
			System.out.println("BooksFormAction ofctype_code"+request.getParameter("ofctype_code"));
			office.setPicture1(request.getParameter("picture1"));
			office.setPicture2(request.getParameter("picture2"));
			
			request.setAttribute("ofctype_code", ofctype_code);
			request.setAttribute("office", office);
			request.setAttribute("books", books);	
			request.setAttribute("ofctype_name", request.getParameter("ofctype_name"));
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "books/booksForm.jsp";
	}
}