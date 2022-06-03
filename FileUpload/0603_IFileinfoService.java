package kr.or.ddit.basic.fileupload.service;

import java.util.List;

import kr.or.ddit.basic.fileupload.vo.FileInfoVO;

public interface IFileinfoService {

		/**
		 * fileVO에 담겨진 자료를 DB에 insert하는 메소드
		 * @param fileVo DB에 insert할 자료가 저장된 FileInfoVO객체
		 * @return 작업 성공 : 1, 작업 실패 : 0
		 * @throws SQLException
		 */
		public int insertFileinfo(FileInfoVO fileVo);
		
		/**
		 * DB에 있는 전체 파일 목록을 가져와 List에 담아 반환하는 메소드
		 * @return 파일 정보가 저장된 List객체
		 * @throws SQLException
		 */
		public List<FileInfoVO> getAllFileinfo();
		
		/**
		 * fileNo를 인수값으로 받아서 해당 파일 정보를 VO에 담아서 반환하는 메소드
		 * @param fileNo 검색할 file 번호
		 * @return 검색된 파일 정보가 저장된 FileInfoVO객체
		 * @throws SQLException
		 */
		public FileInfoVO getFileinfo(int fileNo);
}
