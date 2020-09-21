package dao.memberAdmin;

public class MemberArticle {
	private int art_idx;
	private String mbr_id;
	private String art_title;
	private String art_content;
	private String art_date;
	private int art_rn;
	
	public int getArt_rn() {
		return art_rn;
	}
	public void setArt_rn(int art_rn) {
		this.art_rn = art_rn;
	}
	public int getArt_idx() {
		return art_idx;
	}
	public void setArt_idx(int art_idx) {
		this.art_idx = art_idx;
	}
	public String getMbr_id() {
		return mbr_id;
	}
	public void setMbr_id(String mbr_id) {
		this.mbr_id = mbr_id;
	}
	public String getArt_title() {
		return art_title;
	}
	public void setArt_title(String art_title) {
		this.art_title = art_title;
	}
	public String getArt_content() {
		return art_content;
	}
	public void setArt_content(String art_content) {
		this.art_content = art_content;
	}
	public String getArt_date() {
		return art_date;
	}
	public void setArt_date(String art_date) {
		this.art_date = art_date;
	}
	
	
}
