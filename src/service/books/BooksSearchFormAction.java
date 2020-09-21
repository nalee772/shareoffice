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

public class BooksSearchFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ofc_region = "";
		OfficeDao od = OfficeDao.getInstance();
		try {
			request.setCharacterEncoding("utf-8");
			BooksDto books = new BooksDto();
			OfficeDto office = new OfficeDto();
			System.out.println("BooksSearchFormAction start");
			ofc_region = request.getParameter("ofc_region")==null?"":request.getParameter("ofc_region");
			office.setOfc_region(ofc_region);			
			String bks_fromdate=request.getParameter("bks_fromdate")==null?"":request.getParameter("bks_fromdate");
			books.setBks_fromdate(bks_fromdate);
			String bks_todate=request.getParameter("bks_todate")==null?"":request.getParameter("bks_todate");
			books.setBks_todate(bks_todate);
			int bks_count=request.getParameter("bks_count")==null?0:Integer.parseInt(request.getParameter("bks_count"));
			System.out.println("bks_count : "+bks_count);
			books.setBks_count(bks_count);		
		
			request.setAttribute("office", office);
			request.setAttribute("books", books);
			
			int totCnt = od.getTotalCnt2(ofc_region);
			String pageNum = request.getParameter("pageNum");
			if (pageNum==null || pageNum.equals("")) {
				pageNum = "1";
			}
			int currentPage = Integer.parseInt(pageNum);
			int pageSize = 10, blockSize = 10;
			int startRow = (currentPage -1) * pageSize + 1;
			int endRow = startRow + pageSize -1;
			int startNum = totCnt - startRow + 1;
			System.out.println("startRow->"+startRow);
			System.out.println("endRow->"+endRow);
			List<OfficeDto> officelist = od.officelist2(startRow, endRow,ofc_region);
			System.out.println("list->"+ officelist.size());
			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
			int startPage = (int)(currentPage-1)/blockSize*blockSize + 1;
			int endPage = startPage + blockSize -1;
			if (endPage > pageCnt) endPage = pageCnt;
			
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("officelist", officelist);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			
			System.out.println("----------------------------------------------------------");
			System.out.println("startNum ->" + startNum);
			System.out.println("totCnt-->" + totCnt);  
			System.out.println("currentPage-->" + currentPage);
			System.out.println("blockSize-->" + blockSize); 
			System.out.println("pageSize-->" + pageSize);  
			System.out.println("pageCnt-->" + pageCnt); 
			System.out.println("startPage-->" + startPage);
			System.out.println("endPage-->" + endPage);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "books/booksSearchForm.jsp";
	}

}
