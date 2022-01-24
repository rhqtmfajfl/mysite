package com.poscoict.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.BoardRepository;
import com.poscoict.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	// 새글, 답글 달기
	public boolean addContents(BoardVo vo) {  //addContents는 어디서 사용되는 거지?
		if(vo.getGroupNo() != null ) {
			increaseGroupOrderNo(vo);
		}		
		return boardRepository.insert(vo); //여기서는 boardRepository에 insert 해준다.
	}
	
	// 글보기
	public BoardVo getContents(Long no){ //여기가 뷰이다.
		return null;
	}
	
	// 글 수정 하기 전,
	public BoardVo getContents(Long no, Long userNo){
		return null;
	}
	
	// 글 수정
	public Boolean updateContents(BoardVo vo) {
		return false;
	}
	
	// 글 삭제
	public Boolean deleteContents(Long no, Long userNo) {
		return false;
	}
	
//	public List<BoardVo> list(){
//		
//		return boardRepository.find();
//	}
	
	// 글 리스트(찾기결과)
	public  Map<String, Object> getContentsList() {
		Map<String, Object> map = new HashMap<>();  //여기가 리스트 이다.
		
		
		
		
		
		map.put("list", boardRepository.find());
		System.out.println("asdfasdfasdfasdfasdfasdf");
		System.out.println(boardRepository.find());

		System.out.println(boardRepository.find());

		System.out.println(boardRepository.find());

		System.out.println(boardRepository.find());

		System.out.println(boardRepository.find());
		System.out.println("asdfasdfasdfasdf");

//		System.out.println();
//		map.put("totalCount", 0);
//		map.put("...", map);
	
		return map;
	}
	
	private boolean increaseGroupOrderNo(BoardVo vo){
		return false;
		}
	
}