package com.poscoict.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.BoardVo;

@Repository
public class BoardRepository {

	@Autowired
	private SqlSession sqlSession;

	//LIST FIND 목록 보여주는것
	public List<BoardVo> find() {
		return sqlSession.selectList("board.find");
	}

	// 사용자 no, title, contents 찾기
	public BoardVo findNo(Long no) {
		return sqlSession.selectOne("board.findNo", no);
	}

	public BoardVo findtitle_content(BoardVo vo2) {

		return sqlSession.selectOne("board.findtitle_content", vo2);
	}

	// 글 입력
	public boolean insert(BoardVo vo) {
		return 1 == sqlSession.insert("board.insert", vo);
	}

	///// 삭제
	public boolean delete_title(BoardVo bvo) {

		return 1 == sqlSession.delete("board.delete_title", bvo);
	}

	public boolean updateHit(Long no) {
		return 1 == sqlSession.update("board.updateHit", no);
	}

	public int updateOrderNo(Integer groupNo, Integer orderNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo);

		return sqlSession.update("board.updateOrderNo", map);
	}

	public boolean update(BoardVo vo) {

		return 1 == sqlSession.update("board.update", vo);
	}
}