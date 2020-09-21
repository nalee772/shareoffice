package service.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.BoardDao;
import service.CommandProcess;

public class BDeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int art_idx = Integer.parseInt(request.getParameter("art_idx"));
			String mbr_id = request.getParameter("mbr_id");
			String pageNum = request.getParameter("pageNum");
			BoardDao bd = BoardDao.getInstance();
			int result = bd.delete(art_idx, mbr_id);
			request.setAttribute("result", result);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("art_idx", art_idx);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "board/boardDelete.jsp";
	}
}
