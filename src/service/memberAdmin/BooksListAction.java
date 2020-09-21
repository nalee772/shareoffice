package service.memberAdmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.books.BooksDto;
import dao.memberAdmin.MemberAdminDao;
import dao.memberUser.MemberDto;
import service.CommandProcess;

public class BooksListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberAdminDao mad = MemberAdminDao.getInstance();
		String admin_mbr_id = request.getParameter("admin_mbr_id");                  //싱글톤의 객체 생성 - 자원절감, 보안 목적
		try {
			int totCnt = mad.getBookTotCnt();                        
			String BpageNum = request.getParameter("BpageNum");
			
			if(BpageNum==null || BpageNum.equals("")) { 
				BpageNum = "1";                                   //처음 목록을 불렀을 땐 아무 값도 받아오지 않으므로 1페이지로 설정, 이후 페이지에서 pageNum값을 가지고 다니므로 그때는 그때갖고있는 파라미터값으로 pageNum설정
			}
			int BcurrentPage = Integer.parseInt(BpageNum);
			int BpageSize = 10, BblockSize = 10;                    //한 페이지에 10개씩 보일 수 있도록
			int BstartRow = (BcurrentPage - 1) * BpageSize + 1;      //default = 1
			int BendRow = BstartRow + BpageSize - 1;                 //10
			int BstartNum = totCnt - BstartRow + 1;
			System.out.println("Blist... BendRow->"+ BendRow);
			List<BooksDto> Blist = mad.bList(BstartRow, BendRow);         //리스트를 열개씩 가져오기 위해, 데이터가 방대해지면 한꺼번에 다 보여주기 불가
			System.out.println("Blist.size()->"+Blist.size());
			int BpageCnt = (int)Math.ceil((double)totCnt/BpageSize);
			int BstartPage = (int)(BcurrentPage-1)/BblockSize*BblockSize + 1;
			System.out.println(BstartPage);
			int BendPage = BstartPage + BblockSize - 1;
			if (BendPage > BpageCnt) BendPage = BpageCnt;             //있는 페이지까지만(없어지면 데이터없어도 10페이지까지 나옴)
			
			request.setAttribute("BtotCnt", totCnt);
			request.setAttribute("BpageNum", BpageNum);
			request.setAttribute("BstartNum", BstartNum);
			request.setAttribute("BcurrentPage", BcurrentPage);
			request.setAttribute("BstartRow", BstartRow);
			request.setAttribute("Blist", Blist);
			request.setAttribute("BblockSize", BblockSize);
			request.setAttribute("BpageCnt", BpageCnt);
			request.setAttribute("BstartPage", BstartPage);
			request.setAttribute("BendPage", BendPage);
			
			request.setAttribute("admin_mbr_id", admin_mbr_id);
			
			System.out.println("-----------------------------------------------------------------"); //개발자가 확인하기 위한 출력
			System.out.println("BstartRow-->" + BstartRow);
			System.out.println("totCnt-->"+ totCnt);
			System.out.println("BcurrentPage-->" + BcurrentPage);
			System.out.println("BblockSize-->" + BblockSize);
			System.out.println("BpageSize-->" + BpageSize);
			System.out.println("BpageCnt-->" + BpageCnt);
			System.out.println("BstartPage-->" + BstartPage);
			System.out.println("BendPage-->" + BendPage );
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ListAction Err->" + e.getMessage());
		} 
		return "memberAdmin/booksList.jsp";   //경로
	}

}
