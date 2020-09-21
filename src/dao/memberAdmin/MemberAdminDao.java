package dao.memberAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dao.books.BooksDto;
import dao.memberUser.MemberDto;

public class MemberAdminDao {
	private static MemberAdminDao instance;

	private MemberAdminDao() {
	}

	public static MemberAdminDao getInstance() {
		if (instance == null) {
			instance = new MemberAdminDao();
		}
		return instance;
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB"); // lookup(참고)
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	// getTotalCnt() : DB 로우 수 가져오는 메소드
	public int getTotalCnt() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int tot = 0;
		String sql = "SELECT count(*) FROM member";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tot = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		System.out.println(tot);
		return tot;
	}

	// list(startRow, endRow) : 테이블 안의 모든 데이터를 dto의 배열로 저장하는 메소드
	public List<MemberDto> list(int startRow, int endRow) throws SQLException {
		List<MemberDto> list = new ArrayList<MemberDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM rn, a.* FROM (SELECT * FROM member) a) WHERE rn between ? and ?";
		System.out.println("MemberAdminDao list startRow->" + startRow);
		System.out.println("MemberAdminDao list endRow->" + endRow);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			System.out.println("MemberAdminDao list sql->" + sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					MemberDto member = new MemberDto();
					member.setMbr_id(rs.getString("mbr_id"));
					member.setMbr_ag_email(rs.getString("mbr_ag_email"));
					member.setMbr_name(rs.getString("mbr_name"));
					member.setMbr_pw(rs.getString("mbr_pw"));
					member.setMbr_tel(rs.getString("mbr_tel"));
					member.setMbr_email(rs.getString("mbr_email"));
					member.setMbr_ag_sms(rs.getString("mbr_ag_sms"));
					member.setMbr_status(rs.getInt("mbr_status"));
					member.setMbr_level(rs.getInt("mbr_level"));
					list.add(member);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return list;
	}

	// select(mbr_id) : 한 로우만 선택하는 메소드
	public MemberDto select(String mbr_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE mbr_id=?";
		MemberDto member = new MemberDto();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbr_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member.setMbr_ag_email(rs.getString("mbr_ag_email"));
				member.setMbr_ag_sms(rs.getString("mbr_ag_sms"));
				member.setMbr_email(rs.getString("mbr_email"));
				member.setMbr_id(rs.getString("mbr_id"));
				member.setMbr_level(rs.getInt("mbr_level"));
				member.setMbr_name(rs.getString("mbr_name"));
				member.setMbr_pw(rs.getString("mbr_pw"));
				member.setMbr_status(rs.getInt("mbr_status"));
				member.setMbr_tel(rs.getString("mbr_tel"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return member;
	}

	// 해당회원 게시글 개수
	public int getMemTotCnt(String mbr_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int tot = 1;
		String sql = "SELECT COUNT(*) FROM article WHERE mbr_id=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbr_id);
			System.out.println("dao getMemTotCnt() mbr_id->" + mbr_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tot = rs.getInt(1);
				System.out.println("dao getMemTotCnt() tot->" + tot);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		System.out.println("Dao....getMemTotCnt() tot ==> " + tot);
		return tot;
	}

	// list(startRow, endRow) : 해당 회원의 게시글 데이터를 dto의 배열로 저장하는 메소드
	public List<MemberArticle> mArtList(String mbr_id, int MstartRow, int MendRow) throws SQLException {
		List<MemberArticle> list = new ArrayList<MemberArticle>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM rn, a.* FROM (SELECT * FROM article WHERE mbr_id =? ORDER BY art_idx) a) WHERE rn between ? and ?";
		System.out.println("MemArtlist list MstartRow->" + MstartRow);
		System.out.println("MemArtlist list MendRow->" + MendRow);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbr_id);
			pstmt.setInt(2, MstartRow);
			pstmt.setInt(3, MendRow);
			System.out.println("MemberAdminDao artlist sql->" + sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					System.out.println("rs.next() == true");
					MemberArticle memart = new MemberArticle();
					memart.setArt_rn(rs.getInt("rn"));
					System.out.println("art_rn->" + memart.getArt_rn());
					memart.setArt_content(rs.getString("art_content"));
					memart.setArt_date(rs.getString("art_date"));
					memart.setArt_idx(rs.getInt("art_idx"));
					memart.setArt_title(rs.getString("art_title"));
					memart.setMbr_id(rs.getString("mbr_id"));
					list.add(memart);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return list;
	}

	// 회원 정지 메소드
	public int suspend(String mbr_id, String admin_mbr_id, String adminPasswd) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql1 = "SELECT mbr_pw FROM member where mbr_id=? and mbr_level=2";
		String sql = "UPDATE member SET mbr_status = 2 WHERE mbr_id=?";
		System.out.println("-------Dao banish() sql==> " + sql);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, admin_mbr_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbpasswd = rs.getString("mbr_pw");
				if (dbpasswd.equals(adminPasswd)) {
					pstmt.close();
					rs.close();

					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, mbr_id);
					result = pstmt.executeUpdate();
				} else {
					result = -1;
				}
			}
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

	// 회원 추방 메소드
	public int banish(String mbr_id, String admin_mbr_id, String adminPasswd) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql1 = "SELECT mbr_pw FROM member where mbr_id=? and mbr_level=2";
		String sql = "UPDATE member SET mbr_status = 3 WHERE mbr_id=?";
		System.out.println("-------Dao banish() sql==> " + sql);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, admin_mbr_id);
			/* System.out.println("-------Dao banish() admin_mbr_id==> "+ admin_mbr_id); */
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbpasswd = rs.getString("mbr_pw");
				/*
				 * System.out.println("-------Dao banish() dbpasswd==> "+
				 * rs.getString("mbr_pw"));
				 */
				if (dbpasswd.equals(adminPasswd)) {
					pstmt.close();
					rs.close();

					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, mbr_id);
					result = pstmt.executeUpdate();
				} else {
					result = -1;
				}
			}
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

	public List<MemberDto> search(String memSearch) throws SQLException {
		List<MemberDto> list = new ArrayList<MemberDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE (mbr_id like '%'||?||'%' or mbr_name like '%'||?||'%')";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memSearch);
			pstmt.setString(2, memSearch);
			System.out.println("MemberAdminDao search sql->" + sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					MemberDto member = new MemberDto();
					member.setMbr_id(rs.getString("mbr_id"));
					member.setMbr_ag_email(rs.getString("mbr_ag_email"));
					member.setMbr_name(rs.getString("mbr_name"));
					member.setMbr_pw(rs.getString("mbr_pw"));
					member.setMbr_tel(rs.getString("mbr_tel"));
					member.setMbr_email(rs.getString("mbr_email"));
					member.setMbr_ag_sms(rs.getString("mbr_ag_sms"));
					member.setMbr_status(rs.getInt("mbr_status"));
					member.setMbr_level(rs.getInt("mbr_level"));
					list.add(member);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return list;
	}

	public int active(String mbr_id, String admin_mbr_id, String adminPasswd) throws SQLException {

		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql1 = "SELECT mbr_pw FROM member where mbr_id=? and mbr_level=2";
		String sql = "UPDATE member SET mbr_status = 1 WHERE mbr_id=?";
		System.out.println("-------Dao banish() sql==> " + sql);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, admin_mbr_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbpasswd = rs.getString("mbr_pw");
				if (dbpasswd.equals(adminPasswd)) {
					pstmt.close();
					rs.close();

					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, mbr_id);
					result = pstmt.executeUpdate();
				} else {
					result = -1;
				}
			}
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

	public int getBookTotCnt() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int tot = 0;
		String sql = "SELECT count(*) FROM books";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tot = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		System.out.println("Dao....getBookTotCnt() tot ==> " + tot);
		return tot;
	}

	public List<BooksDto> bList(int startRow, int endRow) throws SQLException {
		List<BooksDto> list = new ArrayList<BooksDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM rn, a.* FROM (SELECT b.bks_idx, m.mbr_name, b.mbr_id, b.ofc_code, b.ofctype_code, o.ofc_name, o.ofctype_name, b.bks_count, b.bks_fromdate, b.bks_todate FROM books b, office o, MEMBER m WHERE m.mbr_id = b.mbr_id AND b.OFC_CODE = o.OFC_CODE AND b.ofctype_code = o.ofctype_code ) a) WHERE rn between ? and ?";
		System.out.println("MemberAdminDao list startRow->" + startRow);
		System.out.println("MemberAdminDao list endRow->" + endRow);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			System.out.println("MemberAdminDao list sql->" + sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					BooksDto books = new BooksDto();
					books.setBks_idx(rs.getInt("bks_idx"));
					books.setMbr_id(rs.getString("mbr_id"));
					books.setOfc_code(rs.getInt("ofc_code"));
					books.setBks_name(rs.getString("mbr_name"));
					books.setOfctype_code(rs.getInt("ofctype_code"));
					books.setOfc_name(rs.getString("ofc_name"));
					books.setOfctype_name(rs.getString("ofctype_name"));
					books.setBks_count(rs.getInt("bks_count"));
					books.setBks_fromdate(rs.getString("bks_fromdate"));
					books.setBks_todate(rs.getString("bks_todate"));
					list.add(books);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return list;
	}

	public List<BooksDto> Bsearch(String bookSearch) throws SQLException {
		List<BooksDto> list = new ArrayList<BooksDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT b.bks_idx, m.mbr_name, b.mbr_id, b.ofc_code, b.ofctype_code, o.ofc_name, o.ofctype_name, b.bks_count, b.bks_fromdate,b.bks_todate FROM BOOKS b, OFFICE o, MEMBER m WHERE m.mbr_id = b.mbr_id AND b.OFC_CODE = o.OFC_CODE AND b.ofctype_code = o.ofctype_code AND (b.mbr_id like '%'||?||'%' or m.mbr_name like '%'||?||'%')";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookSearch);
			pstmt.setString(2, bookSearch);
			System.out.println("MemberAdminDao 'B'search sql->" + sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					BooksDto books = new BooksDto();
					books.setBks_idx(rs.getInt("bks_idx"));
					books.setMbr_id(rs.getString("mbr_id"));
					books.setOfc_code(rs.getInt("ofc_code"));
					books.setBks_name(rs.getString("mbr_name"));
					books.setOfctype_code(rs.getInt("ofctype_code"));
					books.setOfc_name(rs.getString("ofc_name"));
					books.setOfctype_name(rs.getString("ofctype_name"));
					books.setBks_count(rs.getInt("bks_count"));
					books.setBks_fromdate(rs.getString("bks_fromdate"));
					books.setBks_todate(rs.getString("bks_todate"));
					list.add(books);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return list;
	}

}
