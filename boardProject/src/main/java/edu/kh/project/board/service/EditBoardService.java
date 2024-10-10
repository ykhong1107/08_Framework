package edu.kh.project.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.board.dto.Board;
import edu.kh.project.member.dto.Member;

public interface EditBoardService {

	/** 게시글 등록
	 * @param inputBoard
	 * @param images
	 * @return boardNo
	 */
	int boardInsert(Board inputBoard, List<MultipartFile> images);

	/** 게시글삭제
	 * @param boardNo
	 * @param memberNo
	 * @return
	 */
	int boardDelete(int boardNo, int memberNo);

	/** 게시글 수정
	 * @param inputBoard
	 * @param images
	 * @param deleteOrderList
	 * @return
	 */
	int boardUpdate(Board inputBoard, List<MultipartFile> images, String deleteOrderList);

}
