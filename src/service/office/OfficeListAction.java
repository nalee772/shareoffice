package service.office;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.office.OfficeDto;
import dao.office.OfficeDao;
import service.CommandProcess;

public class OfficeListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		try {
			
			String pageNum = request.getParameter("pageNum");
			OfficeDao od = OfficeDao.getInstance();
			String office_search = request.getParameter("office_search");
			String searchCol = request.getParameter("searchCol");
			request.setAttribute("office_search", office_search);
			request.setAttribute("searchCol", searchCol);
			System.out.println("office_search : "+office_search);
			System.out.println("searchCol : "+searchCol);
			
			
			
			int totCnt = od.getTotalCnt(office_search, searchCol);
			
			if (pageNum==null || pageNum.equals("")) {
				pageNum = "1";
			}
			if(totCnt < 11) {
				pageNum = "1";
			}
			int currentPage = Integer.parseInt(pageNum);
			int pageSize = 10, blockSize = 10;
			int startRow = (currentPage -1) * pageSize + 1;
			int endRow = startRow + pageSize - 1;
			int startNum = totCnt - startRow + 1;
			System.out.println("startRow->"+startRow);
			System.out.println("endRow->"+endRow);
//			List<Office> officelist = od.officelist(startRow, endRow, office_search, searchCol);
			List<OfficeDto> officelist = od.officelist(startRow, endRow, office_search, searchCol);
			System.out.println("list->"+ officelist.size());
			
			for (int i = 0; i < officelist.size(); i++) {
				System.out.println("11111111111" +officelist.get(i).getOfc_code());
			}
			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
			int startPage = (int)(currentPage-1)/blockSize*blockSize + 1;
			int endPage = startPage + blockSize -1;
			if (endPage > pageCnt) endPage = pageCnt;
			
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("startRow", startRow);
			request.setAttribute("endRow", endRow);
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
			System.out.println("endRow ->" + endRow);
			System.out.println("startRow ->" + startRow);
			System.out.println("pageNum ->" + pageNum);
			System.out.println("currentPage-->" + currentPage);
			System.out.println("blockSize-->" + blockSize); 
			System.out.println("pageSize-->" + pageSize);  
			System.out.println("pageCnt-->" + pageCnt); 
			System.out.println("startPage-->" + startPage);
			System.out.println("endPage-->" + endPage);
					
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "office/officelist.jsp";
	}

}
