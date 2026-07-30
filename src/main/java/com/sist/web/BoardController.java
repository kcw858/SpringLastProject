package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.service.BoardService;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.GetProxy;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.vo.*;

@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService bService;
	
	@GetMapping("board/list.do")
	public String board_list(String page, Model model)
	{
		if(page == null)
			page="1";
		
		int curpage = Integer.parseInt(page);
		final int ROWSIZE=10;
		int start = (curpage*ROWSIZE)-ROWSIZE;
		
		List<BoardVO> list = bService.boardListData(start);
		int count = bService.boardRowCount();
		int totalpage = (int)(Math.ceil(count/10.0));
		count = count-((curpage*ROWSIZE)-ROWSIZE);
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("count", count);
		model.addAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		model.addAttribute("main_jsp", "../board/list.jsp");
		return "main/main";
	}
	
	@GetMapping("board/insert.do")
	public String board_insert(String page, Model model)
	{
		model.addAttribute("main_jsp", "../board/insert.jsp");
		return "main/main";
	}
	
	@PostMapping("board/insert_ok.do")
	public String board_insert_ok(BoardVO vo)
	{
		bService.boardInsert(vo);
		return "redirect:../board/list.do";
	}
	
	@GetMapping("board/detail.do")
	public String board_detail(int no,Model model)
	{
		BoardVO vo = bService.boardDetailData(no);
		
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../board/detail.jsp");
		return "main/main";
	}
}
