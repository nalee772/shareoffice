package dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dao.board.BoardDto;
import dao.board.BoardDao;

public class BoardDao {
	private static BoardDao instance;

	private BoardDao() {
	}

	public static BoardDao getInstance() {
		if (instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

//	페이징 처리를 위한 DB상의 총 게시글 갯수 체크 메소드
	public int getTotalCnt(String art_search, String search_select) throws SQLException {
		Connection conn = null; // Connection은 DB에 있는 정보와 연결시켜주는 것
		Statement stmt = null; // Statement 객체의 executeQuery() 메소드를 호출하여 SQL 질의를 실행시킬 수 있다.
		ResultSet rs = null; // Select 문을 사용한 질의의 경우 성공 시 결과물로 ResultSet을 반환
		int tot = 0;
		String sql = "select count(*) from article where 1=1";
		StringBuffer bur = new StringBuffer();
		bur.append(sql);

		if (art_search != null && !search_select.equals("")) {
			if (search_select != null && !search_select.equals("")) {
				if (search_select.equals("art_title")) {
					bur.append(" and art_title like '%" + art_search + "%'");
				} else if (search_select.equals("art_content")) {
					bur.append(" and art_content like '%" + art_search + "%'");
				} else {
					bur.append(" and mbr_id like '%" + art_search + "%'");
				}
			}
		}
		System.out.println(bur.toString());
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(bur.toString());
			if (rs.next())
				tot = rs.getInt(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
		}

		return tot;
	}

//	게시글 리스트 불러오기 위한 메소드
	public List<BoardDto> list(int startRow, int endRow, String art_search, String search_select) throws SQLException {
		List<BoardDto> list = new ArrayList<BoardDto>();
		Connection conn = null;
		// PreparedStatement 객체는 한 번 분석되면 재사용이 용이하다는 장점을 가지고 있다.
		// PreparedStatement는 statement보다 보안이 뛰어나다
		// PreparedStatement를 사용하면 위치홀더는 물음표(?)로 표현
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM (select rownum rn ,a.* from (select case when brd_id = 'NOTICE'  THEN 0 ELSE 1 END AS flag, t.* from article t  ";
		/*
		 * String sql =
		 * " SELECT * FROM (select rownum rn ,a.* from (select * from article order by brd_id DESC, art_ref DESC, art_refstep) a)  WHERE 1=1"
		 * ;
		 */
		StringBuffer bur = new StringBuffer();

		bur.append(sql);
		if (art_search != null && !art_search.equals("")) {
			if (search_select != null && !search_select.equals("")) {
				if (search_select.equals("art_title")) {
					bur.append(" where art_title like '%" + art_search + "%' order by flag , art_ref DESC, art_refstep) a)");
					bur.append(" WHERE rn between ? and ?");
				} else if (search_select.equals("art_content")) {
					bur.append(" where art_content like '%" + art_search + "%' order by flag , art_ref DESC, art_refstep) a)");
					bur.append(" WHERE rn between ? and ?");
				} else {
					bur.append(" where mbr_id like '%" + art_search + "%' order by flag , art_ref DESC, art_refstep) a)");
					bur.append(" WHERE rn between ? and ?");
				}
			}
		} else {
			bur.append(" order by flag , art_ref DESC, art_refstep) a) WHERE rn between ? and ?");
		}
		try {
			System.out.println(
					"SELECT art_idx, mbr_id, brd_id, art_title, art_date, art_count, art_ref, art_refstep, art_reflevel FROM (select rownum rn ,a.* from (select * from article order by art_idx DESC) a) WHERE 1=1 and art_title like '%"
							+ search_select + "%' or art_content like '%" + search_select + "%' or mbr_id like '%"
							+ search_select + "%' and rn between 1 and 10");
			System.out.println("bur : " + bur.toString());
			conn = getConnection();
			pstmt = conn.prepareStatement(bur.toString());
			pstmt.setInt(1, startRow); // setInt는 넘겨주는 인자 값이 int형이기 때문
			System.out.println("startRow->" + startRow);
			pstmt.setInt(2, endRow);
			System.out.println("endRow->" + endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDto board = new BoardDto();
				board.setArt_idx(rs.getInt("art_idx"));
				board.setBrd_id(rs.getString("brd_id"));
				board.setArt_title(rs.getString("art_title"));
				board.setArt_count(rs.getInt("art_count"));
				board.setMbr_id(rs.getString("mbr_id"));
				board.setArt_date(rs.getString("art_date"));
				board.setArt_ref(rs.getInt("art_ref"));
				board.setArt_refstep(rs.getInt("art_refstep"));
				board.setArt_reflevel(rs.getInt("art_reflevel"));

				System.out.println("board rs.next art_idx->" + rs.getInt("art_idx"));
				System.out.println("board rs.next mbr_id->" + rs.getString("mbr_id"));
				System.out.println("board rs.next art_title->" + rs.getString("art_title"));
				System.out.println("board rs.next art_count->" + rs.getInt("art_count"));

				list.add(board);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
		}

		return list;
	}

//게시글 조회수 체크를 위한 메소드
	public void readCount(int art_idx) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update article set art_count = art_count + 1 where art_idx=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, art_idx);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}

//	게시글 열람을 위한 메소드
	public BoardDto select(int art_idx) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select art_idx, mbr_id, art_title, art_content, art_date, art_ref, art_reflevel, art_refstep from article where art_idx="
				+ art_idx;
		BoardDto board = new BoardDto();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				board.setArt_idx(rs.getInt("art_idx"));
				board.setMbr_id(rs.getString("mbr_id"));
				board.setArt_title(rs.getString("art_title"));
				board.setArt_content(rs.getString("art_content"));
				board.setArt_date(rs.getString("art_date"));
				board.setArt_ref(rs.getInt("art_ref"));
				board.setArt_reflevel(rs.getInt("art_reflevel"));
				board.setArt_refstep(rs.getInt("art_refstep"));

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return board;
	}

//	게시글 수정을 위한 메소드
	public int update(BoardDto board) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update article set art_title=?, art_content=? where art_idx=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getArt_title());
			pstmt.setString(2, board.getArt_content());
			pstmt.setInt(3, board.getArt_idx());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return result;
	}

//	게시글 등록을 위한 메소드
	public int insert(BoardDto board) throws SQLException {
		int art_idx = board.getArt_idx();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql1 = "SELECT nvl(max(art_idx),0) FROM ARTICLE";
		// 답글
		String sql = "INSERT INTO article(art_idx, mbr_id, brd_id, art_title, art_content, art_ref, art_refstep, art_reflevel) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		String sql2 = "UPDATE article set art_refstep = art_refstep+1 where art_ref =? and art_refstep >?";
		// 답글 들여쓰기 sql
		try {
			conn = getConnection();

			// -----------------------------------------
			if (art_idx != 0) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, board.getArt_ref());
				pstmt.setInt(2, board.getArt_refstep());
				System.out.println("BoardDao insert board.getArt_ref()->" + board.getArt_ref());
				System.out.println("BoardDao insert board.getArt_refstep()->" + board.getArt_refstep());
				pstmt.executeUpdate();
				pstmt.close();
				board.setArt_refstep(board.getArt_refstep() + 1);
				board.setArt_reflevel(board.getArt_reflevel() + 1);
			}
			// --------------------댓글 판독 후 들여쓰기

			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			rs.next();
			int number = rs.getInt(1) + 1;
			rs.close();
			pstmt.close();
			if (art_idx == 0)
				board.setArt_ref(number);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, board.getMbr_id());
			pstmt.setString(3, board.getBrd_id());
			pstmt.setString(4, board.getArt_title());
			pstmt.setString(5, board.getArt_content());
			pstmt.setInt(6, board.getArt_ref());
			pstmt.setInt(7, board.getArt_refstep());
			pstmt.setInt(8, board.getArt_reflevel());
			result = pstmt.executeUpdate();
			// art_idx가 0일경우 즉 새글일 경우 정보 set

		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return result;
	}

//	게시글 삭제를 위한 메소드
	public int delete(int art_idx, String mbr_id) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql1 = "select mbr_id from article where art_idx=?";
		String sql = "delete from article where art_idx=?";
		try {
			String mbrid = "";
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, art_idx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mbrid = rs.getString(1);
				if (mbrid.equals(mbr_id)) {
					rs.close();
					pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, art_idx);
					result = pstmt.executeUpdate();
				} else
					result = 0;
			} else
				result = -1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return result;
	}

}
