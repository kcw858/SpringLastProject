package com.sist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.mapper.FoodMapper;
import com.sist.vo.FoodVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService
{
	private final FoodMapper mapper; //생성자를 이용해서 구현된 맵퍼 글래스의 주소 받기 => @AutoWired가 포함되어있다

	@Override
	public List<FoodVO> foodListData(int start, int end) {
	
		return mapper.foodListData(start, end);
	}

	@Override
	public int foodTotalPage() {
		// TODO Auto-generated method stub
		return mapper.foodTotalPage();
	}

	@Override
	public FoodVO foodDetailData(int no) {
		// TODO Auto-generated method stub
		return mapper.foodDetailData(no);
	}

}
