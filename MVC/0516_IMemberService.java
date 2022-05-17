package mvcService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import mvcVo.MemberVO;

/**
 * Service 객체는 DAO에 설정된 메소드를 원하는 작업에 맞게 호출하여 결과를 받아오고,
 * 받아온 결과 자료를 Controller에게 보내주는 역할을 한다.
 * 
 * 보통 DAO와 메소드 구조가 비슷하다.
 * 
 * @author Hoo
 *
 */

public interface IMemberService {

	/**
	 * MemberVO 객체에 담겨진 자료를 DB에 insert하는 메소드
	 * 
	 * @param memVo DB에 insert할 자료가 저장된 MemberVO객체
	 * @return insert 작업 성공 : 1, 작업 실패 : 0
	 */
	
	public int insertMember(MemberVO memVo);
	
	/**
	 * 회원 ID를 인수값으로 받아서 해당 회원 정보를 삭제하는 메도스
	 * 
	 * @param memId memId 삭제할 회원 ID
	 * @return 성공 : 1, 실패 : 0
	 */
	
	public int deleteMember(String memId);
	
	/**
	 * MemberVO자료를 이용하여 회원 정보를 update하는 메소드
	 * 
	 * @param memVo update할 회원 정보가 저장된 MemberVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember(MemberVO memVo);
	
	/**
	 * DB 전체 회원 정보를 가져와서 List에 담아 반환하는 메소드
	 * 
	 * @return MemberVO객체가 저장된 List
	 */
	public List<MemberVO> getAllMember();
	
	/**
	 * 회원 ID를 인수값으로 받아서 해당 회원 ID의 개수를 반환하는 메소드
	 * 
	 * @param memId 검색할 회원 ID
	 * @return 검색된 회원 ID 개수
	 */
	public int getMemberCount(String memId);
}
