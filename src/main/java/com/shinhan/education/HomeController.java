package com.shinhan.education;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	

	//사용자가 get 방식으로 요청함
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//Model : data를 저장하기 위한 객체 : request.setAttribute();와 같음
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("myname", "은정-eunjeong");
		model.addAttribute("myage", 26);
		model.addAttribute("mycar", new Car("BMW520", 7000));
		
		//ViewResolver에게 home을 주면 다음과 같이 접두사, 접미사를 붙임 => 접두사 + "home" + 접미사
		//view가 결정된다. : WEB-INF/vies/home.jsp
		//view에서 model을 이용해서 코드
		return "home";
	}
	
}
