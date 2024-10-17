package edu.kh.project.sse.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.sse.dto.Notification;

public interface SseService {

	
	/** 알림 삽입 후 알림받을 회원번호 + 알림개수반환
	 * @param notification
	 * @return
	 */
	Map<String, Object> insertNotification(Notification notification);

	/** 로그인한 회원의 알림목록조회
	 * @param memberNo
	 * @return 
	 */
	List<Notification> selectNotificationList(int memberNo);

}
