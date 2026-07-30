package com.sist.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.mapper.BoardMapper;
import com.sist.vo.BoardVO;

import lombok.RequiredArgsConstructor;
/*
 *  Mapper : 데이터베이스 연결
 *  Service : 여러개의 SQL문장을 조합 -> BI(기능 통합)
 *  Controller : Service에서 제공하는 결과값을 브라우저에 전송
 */
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	private final BoardMapper mapper;
	
	@Override
	public List<BoardVO> boardListData(int start) {
		// TODO Auto-generated method stub
		return mapper.boardListData(start);
	}

	@Override
	public int boardRowCount() {
		// TODO Auto-generated method stub
		return mapper.boardRowCount();
	}

	@Override
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}

	@Override
	public BoardVO boardDetailData(int no) {
		mapper.boardHitIncrement(no);
		
		return mapper.boardDetailData(no);
	}

	@Override
	@Transactional //=> AOP적용
	/*
	 * 	외부(메소드 접근전)	내부(메소드 안)
	 * 		|				|
	 * 	  인터셉트			  AOP
	 * 	(자동로그인)
	 * 
	 * public void boardReplyInsert(int pno, BoardVO vo)
	 * {
	 * 		@Before => session.openSession()
	 * 		try
	 * 		{
	 * 			conn.setAutoCommit(fasle)
	 * 
	 * 			BoardVO pvo = mapper.boardParentInfoData(pno);
	 * 			mapper.boardStepIncrement(pvo.getGroup_id(), pvo.getGroup_step());
	 * 			mapper.boardReplyInsert(vo);
				mapper.boardDepthIncrement(pno);
	 * 			
	 * 			conn.commit();
	 * 		}
	 * 		catch(EXception e)
	 * 		{
	 * 			conn.rollback()
	 * 		}
	 * 		finally
	 * 		{
	 * 			conn.setAutoCommit(true)
	 * 		}
	 * 
	 * }
	 */
	public void boardReplyInsert(int pno, BoardVO vo) {
		BoardVO pvo = mapper.boardParentInfoData(pno);
		mapper.boardStepIncrement(pvo.getGroup_id(), pvo.getGroup_step());
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(pno);
		vo.setDepth(0);
		mapper.boardReplyInsert(vo);
		mapper.boardDepthIncrement(pno);
		
	}

	@Override
	public void boardUpdate(BoardVO vo) {
		BoardVO tvo = mapper.boardDetailData(vo.getNo());
		if(tvo.getPwd().equals(vo.getPwd()))
		{
			mapper.boardUpdate(vo);
		}

	}

}
