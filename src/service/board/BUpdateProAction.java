package service.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.BoardDto;
import dao.board.BoardDao;
import service.CommandProcess;

public class BUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			String pageNum = request.getParameter("pageNum");
			BoardDto board = new BoardDto();
			board.setArt_idx(Integer.parseInt(request.getParameter("art_idx")));
			board.setArt_title(request.getParameter("art_title"));
			board.setArt_content(request.getParameter("art_content"));
			BoardDao bd = BoardDao.getInstance();
			int result = bd.update(board);
			
			System.out.println("update -> " + bd.update(board));
			request.setAttribute("result", result);
			request.setAttribute("art_idx", board.getArt_idx());
			request.setAttribute("pageNum", pageNum);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println();
		return "board/boardUpdatePro.jsp";
	}

}
