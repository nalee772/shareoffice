package dao.memberUser;



public class MemberDto {

	private String mbr_id; // 회원 아이디
	private String mbr_email; // 회원 이메일
	private String mbr_name; // 회원 이름
	private String mbr_pw; // 회원 비밀번호
	private String mbr_tel; // 회원 전화번호
	private String mbr_ag_email;// 회원 이메일 수신동의(1=동의, 0=비동의)
	private String mbr_ag_sms;	//회원 sms 수신동의	  (1=동의, 0=비동의)
	private int mbr_status;		//회원 상태 (1=활동, 0=탈퇴, 2=정지 3=추방)
	private int mbr_level;		//회원 레벨 (1=회원, 2=관리자)
	
	
	
	
	public String getMbr_id() {
		return mbr_id;
	}
	public void setMbr_id(String mbr_id) {
		this.mbr_id = mbr_id;
	}
	public String getMbr_email() {
		return mbr_email;
	}
	public void setMbr_email(String mbr_email) {
		this.mbr_email = mbr_email;
	}
	public String getMbr_name() {
		return mbr_name;
	}
	public void setMbr_name(String mbr_name) {
		this.mbr_name = mbr_name;
	}
	public String getMbr_pw() {
		return mbr_pw;
	}
	public void setMbr_pw(String mbr_pw) {
		this.mbr_pw = mbr_pw;
	}
	public String getMbr_tel() {
		return mbr_tel;
	}
	public void setMbr_tel(String mbr_tel) {
		this.mbr_tel = mbr_tel;
	}
	public String getMbr_ag_email() {
		return mbr_ag_email;
	}
	public void setMbr_ag_email(String mbr_ag_email) {
		this.mbr_ag_email = mbr_ag_email;
	}
	public String getMbr_ag_sms() {
		return mbr_ag_sms;
	}
	public void setMbr_ag_sms(String mbr_ag_sms) {
		this.mbr_ag_sms = mbr_ag_sms;
	}
	public int getMbr_status() {
		return mbr_status;
	}
	public void setMbr_status(int mbr_status) {
		this.mbr_status = mbr_status;
	}
	public int getMbr_level() {
		return mbr_level;
	}
	public void setMbr_level(int mbr_level) {
		this.mbr_level = mbr_level;
	}


}
