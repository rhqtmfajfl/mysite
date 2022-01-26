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
		
			boardRepository.updateOrderNo(vo.getGroupNo(), vo.getOrderNo());
		}		
		return boardRepository.insert(vo); //여기서는 boardRepository에 insert 해준다.
	}
	
	// 글보기
	public BoardVo getContents(Long no){ //여기가 뷰이다.
//		boardRepository.findall(no);
		BoardVo boardVo = boardRepository.findNo(no);
		//no, title, contents, g_no as groupNo, o_no as orderNo, depth, user_no as userNo
		//값들을 가지고 온다.
		if(boardVo != null) {
			boardRepository.updateHit(no);
		}
		return 		boardVo; //위에서 가져온 값을 boardVo 타입으로 가지고 온다.

	}
	
	// 글 수정 하기 전,
	public BoardVo getContents_modify(Long no){
		BoardVo vo = new BoardVo();
		
		vo.setNo(no);
		
		BoardVo modify_title_contente = boardRepository.findtitle_content(vo);
//		System.out.println("testsetsetset");
//		System.out.println(boardRepository.findtitle_content(vo) +"FINDTITLE_CONTENT");
		return modify_title_contente;
	}
	
	// 글 수정
	public Boolean updateContents(BoardVo vo) {
		boardRepository.update(vo);
		
		return true;
	}
	
	// 글 삭제
	public Boolean deleteContents(Long no, Long userNo) {
		BoardVo boardVo = new BoardVo();
		boardVo.setNo(no);
		boardVo.setUserNo(userNo);
		boardRepository.delete_title(boardVo);
		
		return true;
	}
	
//	public List<BoardVo> list(){
//		
//		return boardRepository.find();
//	}
	
	// 글 리스트(찾기결과)
	public  Map<String, Object> getContentsList() {
		Map<String, Object> map = new HashMap<>();  //여기가 리스트 이다.
		
		
		
		
		
		map.put("list", boardRepository.find());
		
//		System.out.println("list 목록 : " + boardRepository.find());
	

//		System.out.println();
//		map.put("totalCount", 0);
//		map.put("...", map);
	
		return map;
	}
	
	private boolean increaseGroupOrderNo(BoardVo vo){
		return false;
		}
	
}