package service.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.BoardDto;
import dao.board.BoardDao;
import service.CommandProcess;

public class BWriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			BoardDto board = new BoardDto();
	        
	        String pageNum = request.getParameter("pageNum");
	        String mbr_id = request.getParameter("mbr_id");
	        System.out.println("ProAction mbr_id=>" + mbr_id);
	        String brd_id= request.getParameter("brd_id");
	        String art_title= request.getParameter("art_title");
	        String art_content= request.getParameter("art_content");
	        
	        board.setArt_idx(Integer.parseInt(request.getParameter("art_idx")));
	        board.setArt_ref(Integer.parseInt(request.getParameter("art_ref")));
			board.setArt_refstep(Integer.parseInt(request.getParameter("art_refstep")));
			board.setArt_reflevel(Integer.parseInt(request.getParameter("art_reflevel")));
			//댓글 작성을 위한 정보들 set	        
	    	        
	        board.setMbr_id(mbr_id);
	        board.setBrd_id(brd_id);
	        board.setArt_title(art_title);
	        board.setArt_content(art_content);
			
	        BoardDao dbPro = BoardDao.getInstance();//DB 
	        int result = dbPro.insert(board);
	        
	        
	        request.setAttribute("result", result);
	        request.setAttribute("pageNum", pageNum);
	        request.setAttribute("mbr_id", mbr_id);
	        
	        
	        
		} catch(Exception e) {
			System.out.println(e.getMessage()); 
		}
		return "board/boardWritePro.jsp";
	}

}
