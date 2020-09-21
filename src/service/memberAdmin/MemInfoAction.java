package service.memberAdmin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.memberAdmin.MemberAdminDao;
import dao.memberAdmin.MemberArticle;
import dao.memberUser.MemberDto;
import service.CommandProcess;

public class MemInfoAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberAdminDao mad = MemberAdminDao.getInstance();
		String pageNum = request.getParameter("pageNum");
		String mbr_id = request.getParameter("mbr_id");
		String admin_mbr_id = request.getParameter("admin_mbr_id");
		try {
		String MpageNum = request.getParameter("MpageNum");
		System.out.println("InfoAction mbr_id->"+mbr_id);
		MemberDto member = mad.select(mbr_id);
		int MtotCnt = mad.getMemTotCnt(mbr_id);
		System.out.println("InfoAction MtotCnt->"+MtotCnt);
		System.out.println("InfoAction Mpagenum=>"+MpageNum);
		
		if(MpageNum==null || MpageNum.equals("")) { 
			MpageNum = "1";                                   //처음 목록을 불렀을 땐 아무 값도 받아오지 않으므로 1페이지로 설정, 이후 페이지에서 pageNum값을 가지고 다니므로 그때는 그때갖고있는 파라미터값으로 pageNum설정
		}
		int McurrentPage = Integer.parseInt(MpageNum);
		int MpageSize = 5, MblockSize = 10;                    //한 페이지에 10개씩 보일 수 있도록
		int MstartRow = (McurrentPage - 1) * MpageSize + 1;      //default = 1
		int MendRow = MstartRow + MpageSize - 1; //10
		if (MendRow > MtotCnt) MendRow = MtotCnt;
		int MstartNum = MtotCnt - MstartRow + 1;
		System.out.println("list MendRow->"+MendRow);
		List<MemberArticle> Mlist = mad.mArtList(mbr_id, MstartRow, MendRow);         //리스트를 열개씩 가져오기 위해, 데이터가 방대해지면 한꺼번에 다 보여주기 불가
		System.out.println("Mlist.size()->"+Mlist.size());
		int MpageCnt = (int)Math.ceil((double)MtotCnt/MpageSize);
		int MstartPage = (int)(McurrentPage-1)/MblockSize*MblockSize + 1;
		int MendPage = MstartPage + MblockSize - 1;
		if (MendPage > MpageCnt) MendPage = MpageCnt;             //있는 페이지까지만(없어지면 데이터없어도 10페이지까지 나옴)
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("MtotCnt", MtotCnt);
		request.setAttribute("MpageNum", MpageNum);
		request.setAttribute("McurrentPage", McurrentPage);
		request.setAttribute("MstartNum", MstartNum);
		request.setAttribute("Mlist", Mlist);
		request.setAttribute("MblockSize", MblockSize);
		request.setAttribute("MpageCnt", MpageCnt);
		request.setAttribute("MstartPage", MstartPage);
		request.setAttribute("MendPage", MendPage);
		
		request.setAttribute("MpageNum", MpageNum);
		request.setAttribute("member", member);
		request.setAttribute("admin_mbr_id", admin_mbr_id);
		
		request.setAttribute("mbr_email", member.getMbr_email());
		request.setAttribute("mbr_name", member.getMbr_name());
		request.setAttribute("mbr_id", mbr_id);
		request.setAttribute("mbr_tel", member.getMbr_tel());
		request.setAttribute("mbr_ag_email", member.getMbr_ag_email());
		request.setAttribute("mbr_ag_sms", member.getMbr_ag_sms());
		
		
		System.out.println("-----------------------------------------------------------------"); //개발자가 확인하기 위한 출력
		System.out.println("MstartNum-->" + MstartNum);
		System.out.println("MtotCnt-->"+ MtotCnt);
		System.out.println("McurrentPage-->" + McurrentPage);
		System.out.println("MblockSize-->" + MblockSize);
		System.out.println("MpageSize-->" + MpageSize);
		System.out.println("MpageCnt-->" + MpageCnt);
		System.out.println("MstartPage-->" + MstartPage);
		System.out.println("MendPage-->" + MendPage );
		
		System.out.println("mbr_email-->" + member.getMbr_email());
		System.out.println("mbr_name-->" + member.getMbr_name());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} return "memberAdmin/memberInfo.jsp";
	}

}
