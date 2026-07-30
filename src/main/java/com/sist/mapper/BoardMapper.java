package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

public interface BoardMapper {
	@Select("SELECT no,subject,name,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,hit,group_tab "
			+ "FROM springreplyboard "
			+ "ORDER BY group_id DESC,group_step ASC "
			+ "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
	public List<BoardVO> boardListData(int start);
	
	@Select("SELECT COUNT(*) FROM springreplyboard")
	public int boardRowCount();
	
	@Insert("INSERT INTO springreplyboard(no,name,subject,content,pwd,group_id) "
			+ "VALUES(srb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},"
			+ "(SELECT NVL(MAX(group_id)+1,1) FROM springreplyboard))")
	public void boardInsert(BoardVO vo);
	
	// 상세보기
	@Update("UPDATE springreplyboard SET "
			+ "hit = hit+1 "
			+ "WHERE no=#{no}")
	public void boardHitIncrement(int no);
	@Select("SELECT no,name,subject,content,TO_CHAR(regdate,'yyyy-mm-dd') as dbday,hit "
			+ "FROM springreplyboard "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	
	//답변하기 ===> Transaction
	//1. 상위 데이터를 읽기
	@Select("SELECT group_id,group_step,group_tab "
			+ "FROM springreplyboard "
			+ "WHERE no=#{no}")
	public BoardVO boardParentInfoData(int no);
	//2. UPDATE
	@Update("UPDATE springreplyboard SET "
			+ "group_step = group_step+1 "
			+ "WHERE group_id=#{group_id} AND group_step > #{group_step}")
	public void boardStepIncrement(@Param("gourp_id") int group_id,@Param("group_step") int group_step);
	//3. INSERT
	@Insert("INSERT INTO springreplyboard(no,name,subject,content,pwd,group_id,group_step,group_tab,root,depth) "
			+ "VALUES(srb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},"
			+ "#{group_id},#{group_step},#{group_tab},#{root},#{depth})")
	public void boardReplyInsert(BoardVO vo);
	//4. UPDATE
	@Update("UPDATE springreplyboard SET "
			+ "depth = depth+1 "
			+ "WHERE no =#{no}")
	public void boardDepthIncrement(int no);
	/*
	 * group_id => 답변 모음
	 * sroup_step => 답변안에 출력 순서
	 * tab => 간격 조정
	 * 
	 * root : 어느 게시물의 답변인지
	 * depth : 답변이 몇개인지
	 */
}
