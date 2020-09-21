package service.books;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.books.BooksDto;
import dao.office.OfficeDao;
import dao.office.OfficeDto;
import service.CommandProcess;

public class BooksSelectFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("booksSelectFormAction strat.....");
			int ofc_code = Integer.parseInt(request.getParameter("ofc_code"));
			System.out.println("booksSelectFormAction ofc_code-->" + ofc_code);
			int ofctype_code = Integer.parseInt(request.getParameter("ofctype_code"));
			System.out.println("booksSelectFormAction ofctype_code-->" + ofctype_code);
			String pageNum = request.getParameter("pageNum");
			OfficeDao od = OfficeDao.getInstance();	
			BooksDto books = new BooksDto();			
			OfficeDto office = od.select1(ofc_code);
			String bks_fromdate = request.getParameter("bks_fromdate") ==null?"":request.getParameter("bks_fromdate");
			books.setBks_fromdate(bks_fromdate);
			System.out.println("BooksSearchFormAction bks_fromdate->" + bks_fromdate);
			String bks_todate = request.getParameter("bks_todate")==null?"":request.getParameter("bks_todate");
			books.setBks_todate(bks_todate);
			System.out.println("BooksSearchFormAction bks_todate->" + bks_todate);
			int bks_count = request.getParameter("bks_count")==null?0:Integer.parseInt(request.getParameter("bks_count"));
			System.out.println("bks_count : "+bks_count);
			books.setBks_count(bks_count);			
			
			request.setAttribute("ofctype_code", ofctype_code);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("office", office);
			request.setAttribute("books", books);
	
			List<OfficeDto> typeList = od.typeList(ofc_code);
			System.out.println("typeList -> " + typeList.size());
			request.setAttribute("list", typeList);
			
			List<OfficeDto> imageList = od.imageList(ofc_code, ofctype_code);
			System.out.println("imageList.size()->"+imageList.size());
			request.setAttribute("image", imageList);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "books/booksSelectForm.jsp";
	}

}
