package kr.or.ddit.basic.session;

/*
	DB 테이블에 있는 컬럼을 기준으로 데이터를 객체화할 클래스
	DB 테이블의 컬럼명이 VO 클래스의 멤버변수가 된다.
	DB 테이블의 컬럼값들과 
 */

public class iBatis_MemberVO {
	//ctrl shift y -> 소문자로 바꾸기
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_tel;
	private String mem_addr;

	//VO 클래스의 별도의 생성자를 만들 때에는 기본 생성자도 반드시 같이 만들어 준다.
	
//	public MemberVO(String mem_id, String mem_pass, String mem_name, String mem_tel, String mem_addr) {
//		super();
//		this.mem_id = mem_id;
//		this.mem_pass = mem_pass;
//		this.mem_name = mem_name;
//		this.mem_tel = mem_tel;
//		this.mem_addr = mem_addr;
//	}
	
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}
	
	@Override
	public String toString() {
		return "MemberVO [mem_id=" + mem_id + ", mem_pass=" + mem_pass + ", mem_name=" + mem_name + ", mem_tel="
				+ mem_tel + ", mem_addr=" + mem_addr + "]";
	}
	
}
