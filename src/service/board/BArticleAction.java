package service.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.BoardDto;
import dao.board.BoardDao;
import service.CommandProcess;

public class BArticleAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int art_idx = Integer.parseInt(request.getParameter("art_idx"));
			String pageNum = request.getParameter("pageNum");
			BoardDao bd = BoardDao.getInstance();
			bd.readCount(art_idx); //조회수
			BoardDto board = bd.select(art_idx);
			request.setAttribute("art_idx", art_idx);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("board", board);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "board/boardArticle.jsp";
	}
}