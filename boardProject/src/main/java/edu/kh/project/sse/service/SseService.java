package edu.kh.project.sse.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.member.dto.Member;
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

	/** 현재 로그인한 회원이 받은 알림 중 읽지않은 알림개수 조회
	 * @param memberNo
	 * @return
	 */
	int notReadCheck(int memberNo);

	/** 알림 삭제
	 * @param notificationNo
	 */
	void deleteNotification(int notificationNo);

	/**
	 * 알림 읽음 여부 변경(N->Y)
	 * @param notificationNo
	 */
	void updateNotification(int notificationNo);



}
