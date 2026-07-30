package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.sist.vo.*;

import lombok.RequiredArgsConstructor;

import com.sist.service.*;

@Controller
@RequiredArgsConstructor
public class FoodController {
	private final FoodService fService;
	
	/*
	 *  1. 전송 => ? 변수
	 *  2. 커맨드 => VO (회원가입,수정 등..)
	 *  3. 내장 객체
	 *  	1) HttpSession
	 *  	2) cookie => 저장 : response
	 *  				 읽기 : request
	 *  
	 */
	@GetMapping("food/detail_before.do")
	public String food_detail_before(int no,HttpServletResponse response,RedirectAttributes ra)
	{
		//쿠키 생성
		Cookie cookie = new Cookie("food_"+no, String.valueOf(no));
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		ra.addAttribute("no", no); //"redirect:../food/detail.do?no="+no   뒤에 no를 붙혀서 보내준다
		return "redirect:../food/detail.do";
		
		//조회수 증가/ 쿠키 저장된 값 출력 (back()사용 x)
	}
	
	 @GetMapping("food/detail.do")
	 /*
	   거의 get방식
	   ajax,axios,form은 get,post 선택
	  */
	 public String food_detail(int no,Model model)
	 {
		 FoodVO vo = fService.foodDetailData(no);
		 
		 model.addAttribute("vo",vo);
		 model.addAttribute("main_jsp","../food/detail.jsp");
		 return "main/main";
	 }
}
