package com.shinhan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller	//DispatcherServlet -> ��û�� �޾Ƽ� Controller�� ã�´�.
@RequestMapping("/first")	//calss-level1 �Ʒ� �޼��� level ��û���� ��������� �ۼ��Ѵ�.
public class SampleController {
	
	//���� Ŭ�������� �ΰŸ� ����ž�
	//System.out.println()�� ����ϸ� ���߿� �ٽ� ������� ��.
	//logger�� ����ϸ� ���߿� ���ظ� �ٲ��ָ� ��.
	//������ �� ���� �ٲ��ָ� �ֿܼ� ��µ��� �����Ƿ�
	//syso���� �� ���ϰ� ����� �� ����
	//src/main/resources/log4j.xml �� root�� ���� �α� ���� ���� ����
	Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	//5) ��û �ּҰ� ������, ��û����� Get��
		@RequestMapping(value = {"/paramTest"},
				//userid�� ���� �ݵ�� hello���� ��. (hello�϶��� ����� ��)
				//userpass�� �ݵ�� �־�� ��, ���� �������
				//email�� �������
				params = {"userid=����", "userpass", "!email"},	
				method = RequestMethod.GET)
		//���ڸ� ���� �� �Ķ������ �̸��� �޴� ������ �̸��� ������ @RequestParam�� �����ص� ��
		//age�� �Ķ���Ϳ��� ���� �� age2�� �ް� ������ ������ �� ����. ��, �Ķ���� age�� age2�� �θ��ž�(�����ž�)
		public String test5(Model model, 
							@RequestParam String userid, 
							@RequestParam String userpass,
							String email2,
							String address,
							@RequestParam("age") int age2) {
			ModelAndView mv = new ModelAndView();
			model.addAttribute("subject", "�Ķ���Ϳ� ���� ��û - userid=���� ��, userpass�� �� ������� �����ϸ� ��, email�� ����");
			
			logger.info("userid :" + userid );
			logger.info("userpass :" +userpass );
			logger.info("email2 :" + email2 );
			logger.info("address :" + address );
			logger.info("age :" + age2 );
			logger.info("10�� ����� ���� :" + (age2-10) );
			
			return "sample/sampleView1";
		}
	
	//4) ��û �ּҰ� ������, ��û����� Get��
	@RequestMapping(value = {"/a.do", "b.do"}, method = RequestMethod.GET)
	public String test4(Model model) {
		ModelAndView mv = new ModelAndView();
		model.addAttribute("subject", "��û �ּҰ� �������� ���");
		return "sample/sampleView1";
	}
	
	//3) /first�� ��û������ �������� �Ѿ��.. ���� ������̼ǿ� ��θ� ���� ����X ����
	@RequestMapping
	public String test3(Model model) {
		ModelAndView mv = new ModelAndView();
		model.addAttribute("subject", "�����������ӿ�ũ?");
		return "sample/sampleView1";
	}

	//2) ModelAndView �̿��ϱ�
	@RequestMapping("/sample2")
	public ModelAndView test2() {
		logger.info("-----------sample2 ��û�� ����-----------");
		ModelAndView mv = new ModelAndView();
		mv.addObject("subject", "�����������ӿ�ũ��!");
		//view�� �̸� �����ϱ�
		mv.setViewName("sample/sampleView1");
		return mv;
	}
	
	//1) Model �̿��ϱ�
	@RequestMapping("/sample1")
	public String test1(Model model) {	//jsp�� ������ model(�Ű�����)�� ��
		//debug > info > warn > error > fatal
		//�α� ������ ���� info�̹Ƿ� �ΰ� ��� ���
		//warn���� �ٲٸ� info�� ��µ��� ����
		//������ Ȯ���� �� ���� �� �α׷����� ���߸� �ܼ�â ����ϰ� ��� ����
		logger.info("-----------sample1 ��û�� ����-----------");
		logger.warn("-----------sample1 ��û�� ����-----------");
		model.addAttribute("subject", "�����������ӿ�ũ");
		return "sample/sampleView1";
	}
	
}
