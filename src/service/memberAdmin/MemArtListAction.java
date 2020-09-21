package service.memberAdmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.memberAdmin.MemberAdminDao;
import dao.memberUser.MemberDto;
import service.CommandProcess;

public class MemArtListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberAdminDao mad = MemberAdminDao.getInstance(); //싱글톤의 객체 생성 - 자원절감, 보안 목적
		String mbr_id = request.getParameter("mbr_id");
		try {
			//Board TBL Row Count(개수)
			int totCnt = mad.getMemTotCnt(mbr_id);                        //38Row
			String pageNum = request.getParameter("pageNum");
			if(pageNum==null || pageNum.equals("")) { 
				pageNum = "1";                                   //처음 목록을 불렀을 땐 아무 값도 받아오지 않으므로 1페이지로 설정, 이후 페이지에서 pageNum값을 가지고 다니므로 그때는 그때갖고있는 파라미터값으로 pageNum설정
			}
			int currentPage = Integer.parseInt(pageNum);
			int pageSize = 15, blockSize = 10;                    //한 페이지에 10개씩 보일 수 있도록
			int startRow = (currentPage - 1) * pageSize + 1;      //default = 1
			int endRow = startRow + pageSize - 1;                 //10
			int startNum = totCnt - startRow + 1;
			System.out.println("list endRow->"+endRow);
			List<MemberDto> list = mad.list(startRow, endRow);         //리스트를 열개씩 가져오기 위해, 데이터가 방대해지면 한꺼번에 다 보여주기 불가
			System.out.println("list.size()->"+list.size());
			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
			int startPage = (int)(currentPage-1)/blockSize*blockSize + 1;
			int endPage = startPage + blockSize - 1;
			if (endPage > pageCnt) endPage = pageCnt;             //있는 페이지까지만(없어지면 데이터없어도 10페이지까지 나옴)
			
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("list", list);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			
			System.out.println("-----------------------------------------------------------------"); //개발자가 확인하기 위한 출력
			System.out.println("startNum-->" + startNum);
			System.out.println("totCnt-->"+ totCnt);
			System.out.println("currentPage-->" + currentPage);
			System.out.println("blockSize-->" + blockSize);
			System.out.println("pageSize-->" + pageSize);
			System.out.println("pageCnt-->" + pageCnt);
			System.out.println("startPage-->" + startPage);
			System.out.println("endPage-->" + endPage );
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ListAction Err->" + e.getMessage());
		} 
		return "memberAdmin/memberList.jsp";   //서비스 처리 후 보여줘야 할 view화면
	}

}
