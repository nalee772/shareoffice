package dao.memberUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.*;
import dao.books.BooksDto;
import dao.office.OfficeDto;
import dao.picks.PicksDto;

public class MemberDao {
	private static MemberDao instance;

	private MemberDao() {

	}

	// 객체 생성
	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}

	// DB연결
	private Connection getConnection() {
		Connection conn = null;

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
			System.out.println("DB연결 성공");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	// 회원가입--사용자
	// -------------------------------------------------------------------------------------------
	// default 회원 상태-1(활동) // default 레벨-1(회원) insert
	public int join(MemberDto member) throws SQLException {
		String sql = "insert into member" + "(mbr_id,mbr_email,mbr_name,mbr_pw,mbr_tel,mbr_ag_email,mbr_ag_sms) "
				+ "values(?,?,?,?,?,?,?)";

		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			// form 의 input값 member객체에 저장
			pstmt.setString(1, member.getMbr_id());
			pstmt.setString(2, member.getMbr_email());
			pstmt.setString(3, member.getMbr_name());
			pstmt.setString(4, member.getMbr_pw());
			pstmt.setString(5, member.getMbr_tel());
			pstmt.setString(6, member.getMbr_ag_email());
			pstmt.setString(7, member.getMbr_ag_sms());

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

	// 회원가입-아이디중복체크-------------------------------------------------------------------------------------
	//// 아이디를 받아서 DB의 MEMBER 테이블에 아이디가있으면 1을 리턴
	public int confirmId(String mbr_id) throws SQLException {
		String sql = "select mbr_id from member where mbr_id=?";

		int result = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbr_id);
			rs = pstmt.executeQuery();

			if (rs.next())
				result = 1; // 이미 있는 아이디면 1을 리턴
			else
				result = 0; // 아이디를 사용가능하면 0 리턴

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
		return result;
	}

	// 아이디와 비밀번호가 맞는지 확인후
	// 로그인---------------------------------------------------------------------
	// result = 1 ->로그인 result=0 ->로그인정보안맞음
	public LoginUser login(String mbr_id, String mbr_pw) throws SQLException {
		String sql = "select * from member where mbr_id=? AND mbr_status=1";

		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LoginUser user = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbr_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String dbPasswd = rs.getString("mbr_pw");
				if (dbPasswd.equals(mbr_pw)) {
					result = 1; // 로그인 성공 1

				} else {
					result = 0; // 로그인 실패 (비밀번호 틀림)
				}
				// 로그인유저 객체에 로그인한 회원 정보 생성
				user = new LoginUser();
				user.setMbr_id(rs.getString("mbr_id"));
				user.setMbr_email(rs.getString("mbr_email"));
				user.setMbr_name(rs.getString("mbr_name"));
				user.setMbr_pw(rs.getString("mbr_pw"));
				user.setMbr_tel(rs.getString("mbr_tel"));
				user.setMbr_ag_email(rs.getString("mbr_ag_email"));
				user.setMbr_ag_sms(rs.getString("mbr_ag_sms"));
				user.setMbr_level(rs.getInt("mbr_level"));
				user.setResult(result);
			} else
				result = -1; // 로그인 실패 (아이디없음)

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
		return user;

	}

	// ---아이디 비밀번호 찾기 기능
	// --------------------------------------------------------------------------------------------------------
	// email과 name을 받아서 id 찾기
	public String findId(String mbr_email, String mbr_name) throws SQLException {

		String sql = "SELECT mbr_id FROM member WHERE LOWER(mbr_email)= ? AND mbr_name = ?";
		String mbr_id = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbr_email.toLowerCase());
			pstmt.setString(2, mbr_name);
			rs = pstmt.executeQuery();
			System.out.println("...Dao findId rightbefore if => " + mbr_name);

			if (rs.next()) {
				mbr_id = rs.getString(1);
				System.out.println("...Dao findId mbr_id => " + mbr_id);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			if (rs != null)
				rs.close();
		}

		System.out.println("...Dao findId after => " + mbr_id);
		return mbr_id;
	}

	// id와 email을 받아서 비밀번호 찾기
	public int findPw(String mbr_id, String mbr_email) throws SQLException {

		String sql = "SELECT mbr_pw FROM member WHERE LOWER(mbr_id)=? AND LOWER(mbr_email)=?";
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbr_id.toLowerCase());
			pstmt.setString(2, mbr_email.toLowerCase());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result++;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			if (rs != null)
				rs.close();
		}

		return result;
	}

	// 임시 비밀번호 발급
	public String randomPw(int len) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		int idx = 0;
		StringBuffer sb = new StringBuffer();
		System.out.println("charSet.length :::: " + charSet.length);

		for (int i = 0; i < len; i++) {
			idx = (int) (charSet.length * Math.random()); // 10 * 생성된 난수를 Int로 추출 (소숫점제거)
			System.out.println("idx :::: " + idx);
			sb.append(charSet[idx]);
		}
		return sb.toString();
	}

	// 임시 비밀번호를 DB에 update
	public int updateRPw(String mbr_id, String tempPw) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE member SET mbr_pw=? WHERE mbr_id=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tempPw);
			pstmt.setString(2, mbr_id);
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
	// ---/아이디 비밀번호 찾기 기능
	// --------------------------------------------------------------------------------------------------------

	// 회원정보수정
	// userUpdateForm.jsp--------------------------------------------------------------------------------
	// select로 먼저 DB값을 가져옴
	public MemberDto userInfoSelect(String mbr_id) throws SQLException {
		MemberDto member = new MemberDto();
		String sql = "select mbr_id, mbr_email, mbr_name, mbr_pw, mbr_tel from member where mbr_id=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbr_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member.setMbr_id(rs.getString(1));
				member.setMbr_email(rs.getString(2));
				member.setMbr_name(rs.getString(3));
				member.setMbr_pw(rs.getString(4));
				member.setMbr_tel(rs.getString(5));
				// memDto.setMbr_ag_email(rs.getString(6));
				// memDto.setMbr_ag_sms(rs.getString(7));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			if (rs != null)
				rs.close();
		}
		return member;
	}

	// 회원정보수정------------------------------------------------------------------------------------------------------------------------
	public int userInfoUpdate(MemberDto member) throws SQLException {
		int result = 0;
		// result = userInfoSelect(memDto.getMbr_id());
		String sql = "update member set mbr_email=?, mbr_name=?, mbr_pw=?, mbr_tel=?, mbr_ag_email=?, mbr_ag_sms=? where mbr_id=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, member.getMbr_email());
			pstmt.setString(2, member.getMbr_name());
			pstmt.setString(3, member.getMbr_pw());
			pstmt.setString(4, member.getMbr_tel());
			pstmt.setString(5, member.getMbr_ag_email());
			pstmt.setString(6, member.getMbr_ag_sms());
			pstmt.setString(7, member.getMbr_id());

			result = pstmt.executeUpdate();

		} catch (Exception e) {

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return result;

	}

	// 회원탈퇴 - mbr_status=0
	public int userOut(String mbr_id, String mbr_pw) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql1 = "SELECT mbr_pw FROM member where mbr_id=?";
		String sql = "UPDATE member SET mbr_status=0, mbr_ag_email='0',mbr_ag_sms='0' WHERE mbr_id=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, mbr_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbpasswd = rs.getString("mbr_pw");
				if (dbpasswd.equals(mbr_pw)) {
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

	// 회원 예약목록
	public List<BooksDto> userBooksList(String mbr_id) throws SQLException {
		List<BooksDto> userBooksList = new ArrayList<BooksDto>();
		String sql = "SELECT b.bks_idx, o.ofc_name, o.ofctype_name, o.picture1, b.bks_count, b.bks_fromdate, b.bks_todate FROM books b, office o where mbr_id = ? and b.ofc_code = o.ofc_code and b.ofctype_code=o.ofctype_code order by bks_idx DESC";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbr_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BooksDto books = new BooksDto();
				books.setBks_idx(rs.getInt("bks_idx"));
				books.setOfc_name(rs.getString("ofc_name"));
				books.setOfctype_name(rs.getString("ofctype_name"));
				books.setPicture1(rs.getString("picture1"));
				books.setBks_count(rs.getInt("bks_count"));
				books.setBks_fromdate(rs.getString("bks_fromdate"));
				books.setBks_todate(rs.getString("bks_todate"));
				userBooksList.add(books);
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
		return userBooksList;
	}

	// 회원 예약 취소
	public int delete_Books(int bks_idx,String mbr_id) throws SQLException {
		int result = 0;
		String sql = "delete from books where bks_idx=? and mbr_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bks_idx);
			pstmt.setString(2, mbr_id);
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

	// 회원 찜목록
	public List<OfficeDto> userPicksList(String mbr_id) throws SQLException {
		List<OfficeDto> userPicksList = new ArrayList<OfficeDto>();

		String sql = "SELECT o.ofc_name, o.ofctype_name, o.picture1, o.ofc_location, o.ofc_tel, p.ofc_code, p.ofctype_code FROM picks p, office o where mbr_id = ? and p.ofc_code = o.ofc_code and p.ofctype_code=o.ofctype_code";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbr_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OfficeDto office = new OfficeDto();
				office.setOfc_name(rs.getString("ofc_name"));
				office.setOfc_code(rs.getInt("ofc_code"));
				office.setOfctype_code(rs.getInt("ofctype_code"));
				office.setOfctype_name(rs.getString("ofctype_name"));
				office.setOfc_location(rs.getString("ofc_location"));
				office.setOfc_tel(rs.getString("ofc_tel"));
				office.setPicture1(rs.getString("picture1"));
				userPicksList.add(office);
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
		return userPicksList;

	}

	//찜 취소
	public int delete_Picks(int ofc_code, int ofctype_code, String mbr_id) throws SQLException{
		int result = 0;
		String sql = "delete from picks where ofc_code=? and ofctype_code=? and mbr_id=?";
		System.out.println("Dao delete_Picks start...");

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ofc_code);
			pstmt.setInt(2, ofctype_code);
			pstmt.setString(3, mbr_id);
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		System.out.println("Dao delete_Picks result->" + result);
		return result;
	}
	
	
	//찜목록 추가
	public int picksInsert(PicksDto picks) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="insert into picks values(?,?,?)";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, picks.getMbr_id());	
			pstmt.setInt(2, picks.getOfc_code());
			pstmt.setInt(3, picks.getOfctype_code());
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

}
