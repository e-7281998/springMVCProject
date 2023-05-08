package com.shinhan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller	//DispatcherServlet -> 요청을 받아서 Controller를 찾는다.
@RequestMapping("/first")	//calss-level1 아래 메서드 level 요청들의 공통사항을 작성한다.
public class SampleController {
	
	//현재 클래스에서 로거를 남길거야
	//System.out.println()을 사용하면 나중에 다시 지워줘야 함.
	//logger를 사용하면 나중에 수준만 바꿔주면 됨.
	//레벨을 더 낮게 바꿔주면 콘솔에 출력되지 않으므로
	//syso보다 더 편리하게 사용할 수 있음
	//src/main/resources/log4j.xml 의 root에 가서 로그 레벨 조절 가능
	Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	//5) 요청 주소가 여러개, 요청방식이 Get임
		@RequestMapping(value = {"/paramTest"},
				//userid의 값은 반드시 hello여야 함. (hello일때만 여기로 옴)
				//userpass는 반드시 있어야 함, 값은 상관없음
				//email은 없어야함
				params = {"userid=은정", "userpass", "!email"},	
				method = RequestMethod.GET)
		//인자를 받을 때 파라미터의 이름과 받는 인자의 이름이 같으면 @RequestParam은 생략해도 됨
		//age를 파라미터에서 받을 때 age2로 받고 싶으면 생략할 수 없다. 즉, 파라미터 age를 age2로 부를거야(받을거야)
		public String test5(Model model, 
							@RequestParam String userid, 
							@RequestParam String userpass,
							String email2,
							String address,
							@RequestParam("age") int age2) {
			ModelAndView mv = new ModelAndView();
			model.addAttribute("subject", "파라메터에 의한 요청 - userid=은정 만, userpass는 값 상관없이 존재하면 됨, email은 제외");
			
			logger.info("userid :" + userid );
			logger.info("userpass :" +userpass );
			logger.info("email2 :" + email2 );
			logger.info("address :" + address );
			logger.info("age :" + age2 );
			logger.info("10살 어려진 나이 :" + (age2-10) );
			
			return "sample/sampleView1";
		}
	
	//4) 요청 주소가 여러개, 요청방식이 Get임
	@RequestMapping(value = {"/a.do", "b.do"}, method = RequestMethod.GET)
	public String test4(Model model) {
		ModelAndView mv = new ModelAndView();
		model.addAttribute("subject", "요청 주소가 여러개인 경우");
		return "sample/sampleView1";
	}
	
	//3) /first로 요청했을때 이쪽으로 넘어옴.. 다음 어노테이션에 경로를 따로 설정X 때문
	@RequestMapping
	public String test3(Model model) {
		ModelAndView mv = new ModelAndView();
		model.addAttribute("subject", "스프링프레임워크?");
		return "sample/sampleView1";
	}

	//2) ModelAndView 이용하기
	@RequestMapping("/sample2")
	public ModelAndView test2() {
		logger.info("-----------sample2 요청을 받음-----------");
		ModelAndView mv = new ModelAndView();
		mv.addObject("subject", "스프링프레임워크라구!");
		//view의 이름 저장하기
		mv.setViewName("sample/sampleView1");
		return mv;
	}
	
	//1) Model 이용하기
	@RequestMapping("/sample1")
	public String test1(Model model) {	//jsp랑 소통을 model(매개변수)로 함
		//debug > info > warn > error > fatal
		//로그 레벨이 현재 info이므로 두개 모두 출력
		//warn으로 바꾸면 info는 출력되지 않음
		//데이터 확인이 다 끝난 후 로그레벨을 낮추면 콘솔창 깔끔하게 사용 가능
		logger.info("-----------sample1 요청을 받음-----------");
		logger.warn("-----------sample1 요청을 받음-----------");
		model.addAttribute("subject", "스프링프레임워크");
		return "sample/sampleView1";
	}
	
}
