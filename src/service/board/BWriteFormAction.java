package service.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.BoardDto;
import dao.board.BoardDao;
import service.CommandProcess;

public class BWriteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("BWriteFormAction start...");
		try {
			int art_idx = 0, art_ref = 0, art_reflevel = 0, art_refstep = 0;
			String pageNum = request.getParameter("pageNum");
			String mbr_id = request.getParameter("mbr_id");
			System.out.println("BWriteFormAction pageNum->" + pageNum);
			if (pageNum == null)
				pageNum = "1";
			// 댓글
			System.out.println("BWriteFormAction art_idx->" + request.getParameter("art_idx"));
			if (request.getParameter("art_idx") != null) { // pagenum 이 0이아니니까 해당 num에대해 관련
				art_idx = Integer.parseInt(request.getParameter("art_idx"));
				BoardDao bd = BoardDao.getInstance();
				BoardDto board = bd.select(art_idx);
				art_ref = board.getArt_ref();
				System.out.println("BWriteFormAction art_ref->" + art_ref);
				art_reflevel = board.getArt_reflevel();
				System.out.println("BWriteFormAction art_reflevel->" + art_reflevel);
				art_refstep = board.getArt_refstep(); // 0이아니니 일단 해당글의 re_level, re_step를 가져옴 (이땐 0)
				System.out.println("BWriteFormAction art_refstep->" + art_refstep);
			}
			// ----------------------------

			System.out.println("BWriteFormAction mbr_id->" + mbr_id);
			request.setAttribute("art_idx", art_idx);
			request.setAttribute("art_ref", art_ref);
			request.setAttribute("art_reflevel", art_reflevel);
			request.setAttribute("art_refstep", art_refstep);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("mbr_id", mbr_id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "board/boardWriteForm.jsp";
	}

}
