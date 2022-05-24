package ko.or.ddit.basic;

import org.apache.log4j.Logger;

public class Log4jTest {
	
	//Logger 클래스의 인스턴스를 구한다. (로그 기록을 남길 클래스를 지정해서)
	static Logger logger = Logger.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		//로그 기록 남기기
		//형식1) Logger 객체변수.로그레벨이름("출력할 메시지"); -> 로그레벨이름은 소문자로
		
		logger.trace("이것은 log4j의 [TRACE] 레벨의 출력");
		logger.debug("이것은 log4j의 [DEBUG] 레벨의 출력");
		logger.info("이것은 log4j의 [INFO] 레벨의 출력");
		logger.warn("이것은 log4j의 [WARN] 레벨의 출력");
		logger.error("이것은 log4j의 [EROOR] 레벨의 출력");
		logger.fatal("이것은 log4j의 [FATAL] 레벨의 출력");
		 
	}

}
