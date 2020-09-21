package dao.books;

public class BooksDto {
	private int bks_idx;
	private String mbr_id;
	private int ofc_code;
	private int ofctype_code;
	private String bks_name;	
	private String bks_tel;
	private int bks_count;
	private String bks_fromdate;
	private String bks_todate;
	private String picture1;
	private String ofc_name;
	private String ofctype_name;
	
	public String getPicture1() {
		return picture1;
	}
	public void setPicture1(String picture1) {
		this.picture1 = picture1;
	}

	public String getOfc_name() {
		return ofc_name;
	}
	public void setOfc_name(String ofc_name) {
		this.ofc_name = ofc_name;
	}
	public String getOfctype_name() {
		return ofctype_name;
	}
	public void setOfctype_name(String ofctype_name) {
		this.ofctype_name = ofctype_name;
	}
	public int getBks_idx() {
		return bks_idx;
	}
	public void setBks_idx(int bks_idx) {
		this.bks_idx = bks_idx;
	}
	public String getMbr_id() {
		return mbr_id;
	}
	public void setMbr_id(String mbr_id) {
		this.mbr_id = mbr_id;
	}
	public int getOfc_code() {
		return ofc_code;
	}
	public void setOfc_code(int ofc_code) {
		this.ofc_code = ofc_code;
	}
	public int getOfctype_code() {
		return ofctype_code;
	}
	public void setOfctype_code(int ofctype_code) {
		this.ofctype_code = ofctype_code;
	}
	public String getBks_name() {
		return bks_name;
	}
	public void setBks_name(String bks_name) {
		this.bks_name = bks_name;
	}
	public String getBks_tel() {
		return bks_tel;
	}
	public void setBks_tel(String bks_tel) {
		this.bks_tel = bks_tel;
	}
	public int getBks_count() {
		return bks_count;
	}
	public void setBks_count(int bks_count) {
		this.bks_count = bks_count;
	}
	public String getBks_fromdate() {
		return bks_fromdate;
	}
	public void setBks_fromdate(String bks_fromdate) {
		this.bks_fromdate = bks_fromdate;
	}
	public String getBks_todate() {
		return bks_todate;
	}
	public void setBks_todate(String bks_todate) {
		this.bks_todate = bks_todate;
	}
	
	
}
