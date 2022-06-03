package kr.or.ddit.basic.fileupload.service;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.basic.fileupload.dao.FileinfoDaoImpl;
import kr.or.ddit.basic.fileupload.dao.IFileinfoDao;
import kr.or.ddit.basic.fileupload.vo.FileInfoVO;
import util.SqlMapClientFactory;

public class FileinfoServiceImpl implements IFileinfoService {
	private SqlMapClient smc;
	private IFileinfoDao dao;
	
	private static FileinfoServiceImpl service;
	
	private FileinfoServiceImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
		dao = FileinfoDaoImpl.getInstance();
	}
	
	public static FileinfoServiceImpl getInstance() {
		if(service == null) service = new FileinfoServiceImpl();
		return service;
	}

	@Override
	public int insertFileinfo(FileInfoVO fileVo) {
		int cnt = 0;
		
		try {
			cnt = dao.insertFileinfo(smc, fileVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<FileInfoVO> getAllFileinfo() {
		List<FileInfoVO> fileList = null;
		
		try {
			fileList = dao.getAllFileinfo(smc);
		} catch (SQLException e) {
			fileList = null;
			e.printStackTrace();
		}
		return fileList;
	}

	@Override
	public FileInfoVO getFileinfo(int fileNo) {
		FileInfoVO fileVo = null;
		
		try {
			fileVo = dao.getFileinfo(smc, fileNo);
		} catch (SQLException e) {
			fileVo = null;
		}
		return fileVo;
	}

}
