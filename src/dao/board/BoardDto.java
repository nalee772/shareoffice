package dao.board;

public class BoardDto {
	private String brd_id;
	private String brd_name;
	private String mbr_id;
	private String art_title;
	private String art_content;
	private String art_date;
	private int art_ref;
	private int art_refstep;
	private int art_reflevel;
	private int art_count;
	private int brd_read;
	private int brd_update;
	private int brd_reply;
	private int brd_write;
	private int art_idx;

	public int getArt_ref() {
		return art_ref;
	}

	public void setArt_ref(int art_ref) {
		this.art_ref = art_ref;
	}

	public int getArt_refstep() {
		return art_refstep;
	}

	public void setArt_refstep(int art_refstep) {
		this.art_refstep = art_refstep;
	}

	public int getArt_reflevel() {
		return art_reflevel;
	}

	public void setArt_reflevel(int art_reflevel) {
		this.art_reflevel = art_reflevel;
	}
	public String getArt_date() {
		return art_date;
	}

	public void setArt_date(String art_date) {
		this.art_date = art_date;
	}

	public String getMbr_id() {
		return mbr_id;
	}

	public void setMbr_id(String mbr_id) {
		this.mbr_id = mbr_id;
	}

	public String getBrd_id() {
		return brd_id;
	}

	public void setBrd_id(String brd_id) {
		this.brd_id = brd_id;
	}

	public String getBrd_name() {
		return brd_name;
	}

	public void setBrd_name(String brd_name) {
		this.brd_name = brd_name;
	}

	public int getBrd_read() {
		return brd_read;
	}

	public void setBrd_read(int brd_read) {
		this.brd_read = brd_read;
	}

	public int getBrd_update() {
		return brd_update;
	}

	public void setBrd_update(int brd_update) {
		this.brd_update = brd_update;
	}

	public int getBrd_reply() {
		return brd_reply;
	}

	public void setBrd_reply(int brd_reply) {
		this.brd_reply = brd_reply;
	}

	public int getBrd_write() {
		return brd_write;
	}

	public void setBrd_write(int brd_write) {
		this.brd_write = brd_write;
	}

	public int getArt_idx() {
		return art_idx;
	}

	public void setArt_idx(int art_idx) {
		this.art_idx = art_idx;
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

	public int getArt_count() {
		return art_count;
	}

	public void setArt_count(int art_count) {
		this.art_count = art_count;
	}
}
