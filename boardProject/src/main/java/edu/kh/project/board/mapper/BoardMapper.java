package edu.kh.project.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.project.board.dto.Board;

@Mapper
public interface BoardMapper {

	/** boardCode가 일치하는 게시글 중 삭제되지 않은 게시글 수 조회
	 * @param boardCode
	 * @return listCount
	 */
	int getListCount(int boardCode);

	/** 지정된 페이지 분량의 게시글 목록조회
	 * @param boardCode
	 * @param rowBounds
	 * @return boardList
	 */
	List<Board> selectBoardList(int boardCode, RowBounds rowBounds);

}
