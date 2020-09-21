package dao.books;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dao.office.OfficeDto;


public class BooksDao {
	private static BooksDao instance; // instance private
	private BooksDao() {}
	public static BooksDao getInstance() {
		if (instance == null) {instance = new BooksDao(); }
		return instance;
	}
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)
					ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) { System.out.println(e.getMessage());	}
		return conn;
		}	
	
	public int insert(BooksDto books) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;  
		int bks_idx = 1;
		ResultSet rs = null;
		System.out.println("BooksDao insertBooks start...");
		String sql2 = "SELECT nvl(max(bks_idx),1) FROM books";
		System.out.println("BooksDao seq_bks_idx.NEXTVAL sql2->"+sql2);
		String sql = "INSERT INTO BOOKS VALUES(?,?,?,?,?,?,?)";
		System.out.println("BooksDao insertBooks sql->"+sql);
		try {
			conn = getConnection();
			// seq_bks_idx.NEXTVAL GET
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if (rs.next()) bks_idx = bks_idx + rs.getInt(1);
			System.out.println("BooksDao insert bks_idx->"+bks_idx);
			
			rs.close();
			pstmt.close();		
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bks_idx);
			pstmt.setString(2, books.getMbr_id());	
			pstmt.setInt(3, books.getOfc_code());
			pstmt.setInt(4, books.getOfctype_code());
			pstmt.setInt(5, books.getBks_count());
			pstmt.setString(6, books.getBks_fromdate());
			pstmt.setString(7, books.getBks_todate());
			result = pstmt.executeUpdate();
			System.out.println("BooksDao insert result=>"+result);
			/*result = bks_idx;*/
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
			result = 0;
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;		
	}
}
