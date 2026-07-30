package com.sist.mapper;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.GoodsVO;

import java.util.*;

public interface GoodsMapper {
	@Select("SELECT no,goods_name,goods_price,goods_poster "
			+ "FROM goods_all "
			+ "ORDER BY no ASC "
			+ "OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY")
	public List<GoodsVO> goodsListData(int start);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM goods_all")
	public int goodsTotalPage();
	
	@Select("SELECT no,goods_name,goods_sub,goods_price,goods_discount,"
			+ "goods_first_price,goods_delivery,goods_poster "
			+ "FROM goods_all "
			+ "WHERE no=#{no}")
	public GoodsVO goodsDetailData(int no);
	
}
