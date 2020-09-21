package dao.office;

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

public class OfficeDao {
	private static OfficeDao instance;

	private OfficeDao() {
	}

	public static OfficeDao getInstance() {
		if (instance == null) {
			instance = new OfficeDao();
		}
		return instance;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext(); // Context객체를 미리 만들어놓고 사용하려고 작성
			// DataSource는 Connection을 관리하기 위한 객체
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public int getTotalCnt(String office_search, String searchCol) throws SQLException {
		Connection conn = null; // Connection은 DB에 있는 정보와 연결시켜주는 것
		Statement stmt = null; // Statement 객체의 executeQuery() 메소드를 호출하여 SQL 질의를 실행시킬 수 있다.
		ResultSet rs = null; // Select 문을 사용한 질의의 경우 성공 시 결과물로 ResultSet을 반환
		int tot = 0;
		String sql = "select count(*) from office where 1=1 AND OFC_DEL = 1";
		StringBuffer bur=new StringBuffer();
		bur.append(sql);
		
		if(office_search!=null && !office_search.equals("")) {
			if(searchCol!=null && !searchCol.equals("")) {
				if(searchCol.equals("ofc_name")) {
					bur.append(" and ofc_name like '%"+office_search+"%'");
				}else if(searchCol.equals("ofctype_code")) {
					bur.append(" and ofctype_code like '%"+office_search+"%'");
				}else {
					bur.append(" and ofc_location like '%"+office_search+"%'");
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
	
	public List<OfficeDto> officelist(int startRow, int endRow, String office_search,String searchCol) throws SQLException {
		List<OfficeDto> officelist = new ArrayList<OfficeDto>();
		Connection conn = null;
		// PreparedStatement 객체는 한 번 분석되면 재사용이 용이하다는 장점을 가지고 있다.
		// PreparedStatement는 statement보다 보안이 뛰어나다
		// PreparedStatement를 사용하면 위치홀더는 물음표(?)로 표현
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " SELECT ofc_code, ofc_name, ofctype_code, ofc_location, ofc_tel, ofctype_name, ofc_region, ofc_del "
				+ "FROM (select rownum rn ,a.* from (select * from office ";
		StringBuffer bur=new StringBuffer();
		
		bur.append(sql);
		if(office_search!=null && !office_search.equals("")) {
			if(searchCol!=null && !searchCol.equals("")) {
				if(searchCol.equals("ofc_name")) {
					System.out.println("searchCol->1");
			    	bur.append(" where ofc_name like '%"+office_search+"%'" +" order by ofc_code DESC) a )") ;
					bur.append(" WHERE rn between ? and ?"); 
				}else if(searchCol.equals("ofctype_code")) {
					System.out.println("searchCol->2");
					bur.append(" where ofctype_code like '%"+office_search+"%'" +" order by ofc_code DESC) a )") ;
					bur.append(" WHERE rn between ? and ?"); 
				}else {
					System.out.println("searchCol->3");
					bur.append(" where ofc_location like '%"+office_search+"%'" +" order by ofc_code DESC) a )") ;
					bur.append(" WHERE rn between ? and ?"); 
				}
			}
		}else {
			System.out.println("searchCol->4");
			// 완성 SQL이기 때문에 -->  ) a)
			bur.append(" order by ofc_code DESC ) a)  WHERE rn between ? and ?"); 
		}
		try {
			System.out.println("SQL--> bur : "+bur.toString());
			conn = getConnection();
			pstmt = conn.prepareStatement(bur.toString());
			pstmt.setInt(1, startRow); // setInt는 넘겨주는 인자 값이 int형이기 때문
			System.out.println("startRow->" + startRow);
			pstmt.setInt(2, endRow);
			System.out.println("endRow->" + endRow);
			rs = pstmt.executeQuery();			
			while (rs.next()) {
				OfficeDto office = new OfficeDto();
				office.setOfc_code(rs.getInt("ofc_code"));
				office.setOfc_name(rs.getString("ofc_name"));
				office.setOfctype_code(rs.getInt("ofctype_code"));
				office.setOfc_location(rs.getString("ofc_location"));
				office.setOfc_tel(rs.getString("ofc_tel"));
				office.setOfctype_name(rs.getString("ofctype_name"));
				office.setOfc_region(rs.getString("ofc_region"));
				office.setOfc_del(rs.getInt("ofc_del"));
				
				System.out.println("officelist rs.next ofc_code->"+rs.getInt("ofc_code"));
				System.out.println("officelist rs.next ofc_name->"+rs.getString("ofc_name"));
				
				officelist.add(office);
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

		return officelist;
	}
	
	public OfficeDto select(int ofc_code, int ofctype_code) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select ofc_code, ofc_name, ofctype_code, ofc_location, ofc_tel, ofctype_name, ofc_region,"
				+ "picture1, picture2, picture3, picture4, picture5, picture6, picture7, picture8, picture9, picture10"
				+ " from office where ofc_code=" + ofc_code + "and ofctype_code = " + ofctype_code;
		System.out.println("Officedao sql->"+sql);
		System.out.println("Officedao ofc_code->" + ofc_code);
		System.out.println("Officedao ofctype_code->" + ofctype_code);
		OfficeDto office = new OfficeDto();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("Officedao rs->"+sql);
			if (rs.next()) {
				System.out.println("Officedao rs.next ofc_code->"+rs.getInt("ofc_code"));
				System.out.println("Officedao rs.next ofc_name->"+rs.getString("ofc_name"));
				System.out.println("Officedao rs.next ofc_code->"+rs.getInt("ofctype_code"));
				System.out.println("Officedao rs.next ofc_name->"+rs.getString("ofctype_name"));
				office.setOfc_code(rs.getInt("ofc_code"));
				office.setOfc_name(rs.getString("ofc_name"));
				office.setOfctype_code(rs.getInt("ofctype_code"));
				office.setOfc_location(rs.getString("ofc_location"));
				office.setOfctype_name(rs.getString("ofctype_name"));
				office.setOfc_region(rs.getString("ofc_region"));
				office.setOfc_tel(rs.getString("ofc_tel"));
				office.setPicture1(rs.getString("picture1"));
				office.setPicture2(rs.getString("picture2"));
				office.setPicture3(rs.getString("picture3"));
				office.setPicture4(rs.getString("picture4"));
				office.setPicture5(rs.getString("picture5"));
				office.setPicture6(rs.getString("picture6"));
				office.setPicture7(rs.getString("picture7"));
				office.setPicture8(rs.getString("picture8"));
				office.setPicture9(rs.getString("picture9"));
				office.setPicture10(rs.getString("picture10"));
			}
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

		return office;
	}

	public int insert(OfficeDto office) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		// ResultSet rs = null;
		String sql = "INSERT INTO OFFICE(ofc_code, Ofctype_code, Ofc_name, Ofc_tel, Ofc_location, Ofctype_name, Ofc_region, "
				+ "picture1, picture2, picture3, picture4, picture5, picture6, picture7, picture8, picture9, picture10) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		System.out.println("Officedao sql->"+sql);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, office.getOfc_code());
			pstmt.setInt(2, office.getOfctype_code());
			pstmt.setString(3, office.getOfc_name());
			pstmt.setString(4, office.getOfc_tel());
			pstmt.setString(5, office.getOfc_location());
			pstmt.setString(6, office.getOfctype_name());
			pstmt.setString(7, office.getOfc_region());
			pstmt.setString(8, office.getPicture1());
			pstmt.setString(9, office.getPicture2());
			pstmt.setString(10, office.getPicture3());
			pstmt.setString(11, office.getPicture4());
			pstmt.setString(12, office.getPicture5());
			pstmt.setString(13, office.getPicture6());
			pstmt.setString(14, office.getPicture7());
			pstmt.setString(15, office.getPicture8());
			pstmt.setString(16, office.getPicture9());
			pstmt.setString(17, office.getPicture10());
			
			result = pstmt.executeUpdate();
			System.out.println(result);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
		}
		
		return result;
	}

	public int update(OfficeDto office) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update office set ofc_name=?, ofc_location=?, ofc_tel=?, Ofctype_name=?, Ofc_region=?, "
				+ " picture1=?, picture2=?, picture3=?, picture4=?, picture5=?, picture6=?, picture7=?, picture8=?, picture9=?, picture10=?"
				+ " where ofc_code=? and ofctype_code=?";
		System.out.println("update sql -> "+sql);
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, office.getOfc_name());
			pstmt.setString(2, office.getOfc_location());
			pstmt.setString(3, office.getOfc_tel());			
			pstmt.setString(4, office.getOfctype_name());
			pstmt.setString(5, office.getOfc_region());
			pstmt.setString(6, office.getPicture1());
			pstmt.setString(7, office.getPicture2());
			pstmt.setString(8, office.getPicture3());
			pstmt.setString(9, office.getPicture4());
			pstmt.setString(10, office.getPicture5());
			pstmt.setString(11, office.getPicture6());
			pstmt.setString(12, office.getPicture7());
			pstmt.setString(13, office.getPicture8());
			pstmt.setString(14, office.getPicture9());
			pstmt.setString(15, office.getPicture10());
			pstmt.setInt(16, office.getOfc_code());
			pstmt.setInt(17, office.getOfctype_code());

			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) conn.close();
			if (pstmt != null) pstmt.close();
		}

		return result;
	}

	public int delete(int ofc_code, int ofctype_code) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE office SET ofc_del='0' WHERE ofc_code=? and ofctype_code=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ofc_code);
			pstmt.setInt(2, ofctype_code);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) conn.close();
			if (pstmt != null) pstmt.close();
		}
		
		return result;
	}
	
	public int getTotalCnt2(String ofc_region) throws SQLException {
		Connection conn = null; // Connection은 DB에 있는 정보와 연결시켜주는 것
		PreparedStatement pstmt = null; // Statement 객체의 executeQuery() 메소드를 호출하여 SQL 질의를 실행시킬 수 있다.
		ResultSet rs = null; // Select 문을 사용한 질의의 경우 성공 시 결과물로 ResultSet을 반환
		int tot = 0;
		String sql = "select count(max(ofc_code)) from Office where 1=1 ";
		StringBuffer buf = new StringBuffer();
		buf.append(sql);
		if (ofc_region != null && !ofc_region.equals("")) {
			System.out.println("111111111111111");
			buf.append(" and ofc_region=? group by ofc_code");
		} else {
			System.out.println("2222222");
			buf.append(" group by ofc_code");
		}
		try {
			System.out.println("buf.toString() : " + buf.toString());
			conn = getConnection();
			pstmt = conn.prepareStatement(buf.toString());
			if (ofc_region != null && !ofc_region.equals("")) {
				pstmt.setString(1, ofc_region);
			}
			rs = pstmt.executeQuery();
			if (rs.next())
				tot = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
		}

		return tot;
	}
	
	public List<OfficeDto> officelist2(int startRow, int endRow, String ofc_region) throws SQLException {
		List<OfficeDto> officelist = new ArrayList<OfficeDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " select * from (select rownum rn,a. *from (select ofc_code,max(ofc_name) as ofc_name, "
				+ "max(ofctype_code) as ofctype_code ,max(ofctype_name) as ofctype_name, max(ofc_location) as ofc_location, max(ofc_del) as ofc_del, max(picture1) as picture1, max(ofc_tel) as ofc_tel from (select a.* from (select * from office where 1=1  ";
		StringBuffer buf = new StringBuffer();
		buf.append(sql);
		if (ofc_region != null && !ofc_region.equals("")) {
			buf.append(
					"	and ofc_region=? order by ofc_code) a) group by ofc_code) a)  WHERE rn between ? and ?");
		} else {
			buf.append("	order by ofc_code) a)  group by ofc_code) a) WHERE rn between ? and ?");
		}

		System.out.println("buf.toString() : " + buf.toString());
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(buf.toString());
			if (ofc_region != null && !ofc_region.equals("")) {
				pstmt.setString(1, ofc_region);
				System.out.println("ofc_region->" + ofc_region);
				pstmt.setInt(2, startRow); // setInt는 넘겨주는 인자 값이 int형이기 때문
				System.out.println("startRow->" + startRow);
				pstmt.setInt(3, endRow);
				System.out.println("endRow->" + endRow);
			} else {
				System.out.println("ofc_region->" + ofc_region);
				pstmt.setInt(1, startRow); // setInt는 넘겨주는 인자 값이 int형이기 때문
				System.out.println("startRow->" + startRow);
				pstmt.setInt(2, endRow);
				System.out.println("endRow->" + endRow);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OfficeDto office = new OfficeDto();
				office.setOfc_code(rs.getInt("ofc_code"));
				office.setOfc_name(rs.getString("ofc_name"));
				office.setOfctype_code(rs.getInt("ofctype_code"));
				office.setOfctype_name(rs.getString("ofctype_name"));
				office.setOfc_location(rs.getString("ofc_location"));
				office.setOfc_tel(rs.getString("ofc_tel"));
				office.setPicture1(rs.getString("picture1"));
				office.setOfc_del(rs.getInt("ofc_del"));
				System.out.println("officelist rs.next ofc_code->" + rs.getInt("ofc_code"));
				System.out.println("officelist rs.next ofc_name->" + rs.getString("ofc_name"));
				System.out.println("officelist rs.next ofctype_code->" + rs.getInt("ofctype_code"));
				System.out.println("officelist rs.next ofc_del->" + rs.getInt("ofc_del"));

				officelist.add(office);
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

		return officelist;
	}
	
	public OfficeDto select1(int ofc_code) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from office where ofc_code = ?" ;
		OfficeDto office = new OfficeDto();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ofc_code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				office.setOfc_code(rs.getInt("ofc_code"));
				office.setOfc_name(rs.getString("ofc_name"));
				office.setOfc_tel(rs.getString("ofc_tel"));
				office.setOfc_location(rs.getString("ofc_location"));
				office.setOfc_region(rs.getString("ofc_region"));
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
		return office;
	}
	
	
	public List<OfficeDto> typeList(int ofc_code) throws SQLException {
		List<OfficeDto> typeList = new ArrayList<OfficeDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " select * from office where OFC_CODE = ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ofc_code);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OfficeDto office = new OfficeDto();
				office.setOfctype_name(rs.getString("ofctype_name"));
				office.setOfctype_code(rs.getInt("ofctype_code"));
				office.setPicture1(rs.getString("picture1"));
				office.setPicture2(rs.getString("picture2"));
				typeList.add(office);
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
		return typeList;
	}

	public List<OfficeDto> imageList(int ofc_code, int ofctype_code) throws SQLException {
		// TODO Auto-generated method stub
				List<OfficeDto> imageList = new ArrayList<OfficeDto>();
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = " select * from office where OFC_CODE = ? and ofctype_code = ? order by ofc_code DESC";
				
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, ofc_code);
					pstmt.setInt(2, ofctype_code);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						OfficeDto office = new OfficeDto();
						office.setPicture1(rs.getString("picture1"));
						office.setPicture2(rs.getString("picture2"));
						office.setPicture3(rs.getString("picture3"));
						office.setPicture4(rs.getString("picture4"));
						office.setPicture5(rs.getString("picture5"));
						office.setPicture6(rs.getString("picture6"));
						office.setPicture7(rs.getString("picture7"));
						office.setPicture8(rs.getString("picture8"));
						office.setPicture9(rs.getString("picture9"));
						office.setPicture10(rs.getString("picture10"));
						imageList.add(office);
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
				return imageList;
			}
}