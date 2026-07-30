package com.sist.service;

import java.util.List;

import com.sist.vo.GoodsVO;

public interface GoodsService {
	public List<GoodsVO> goodsListData(int start);
	
	public int goodsTotalPage();

	public GoodsVO goodsDetailData(int no);
}
