package com.goodee.library.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	AdminService adminService;
	
	// 회원가입 화면 이동
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String openMemberCreate() {
		logger.info("[AdminController] openMemberCreate()");
		String nextPage = "admin/create";
		return nextPage;
	}
	
	// 회원가입 처리
	@RequestMapping(method=RequestMethod.POST)
	public String createMember(AdminVo vo) {
		logger.info("[AdminController] createMember()");
		String nextPage = "admin/create_success";
		int result = adminService.createMember(vo);
		if(result <= 0) {
			nextPage = "admin/create_fail";
		}
		return nextPage;
	}
	
	// 로그인 화면 이동
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String openloginForm() {
		logger.info("[AdminController] loginForm()");
		String nextPage = "admin/login_form";
		return nextPage;
	}
	
	// 로그인 기능 동작
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginConfirm(AdminVo vo, HttpSession session) {
		logger.info("[AdminController] loginConfirm()");
		String nextPage = "admin/login_success";
		AdminVo loginedAdminVo = adminService.loginConfirm(vo);
		
		if(loginedAdminVo == null) {
			nextPage = "admin/login_fail";
		} else {
			session.setAttribute("loginedAdminVo", loginedAdminVo);
			session.setMaxInactiveInterval(60*1);
		}
		return nextPage;
	}
	
	// 로그아웃 기능 동작
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logoutConfirm(HttpSession session) {
		logger.info("[AdminController] logoutConfirm()");
		String nextPage = "redirect:/";
		session.invalidate();
		return nextPage;
	}
	
	// 회원 목록 이동
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listupAdmin(ModelAndView mav) {
		logger.info("[AdminController] listupAdmin()");
		String nextPage = "admin/listup";
		List<AdminVo> adminVos = adminService.listupAdmin();
		mav.setViewName(nextPage);
		mav.addObject("adminVos",adminVos);
		return mav;
	}
	
	//회원 정보 수정 화면 이동
	@RequestMapping(value="/{a_m_id}", method=RequestMethod.GET)
	public String modifyAdmin(HttpSession session) {
		logger.info("[AdminController] modifyAdmin()");
		String nextPage = "admin/modify_form";
		AdminVo loginedAdminVo = (AdminVo)session.getAttribute("loginedAdminVo");
		if(loginedAdminVo == null) {
			nextPage = "redirect:/admin/login";
		}
		return nextPage;
	}
	
	//회원 정보 수정 기능 동작
	@RequestMapping(value="/{a_m_id}", method=RequestMethod.POST)
	public String modifyAdminConfirm(AdminVo vo, HttpSession session) {
		logger.info("[AdminController] modifyAdminConfirm()");
		String nextPage = "admin/modify_success";
		int result = adminService.modifyAdminConfirm(vo);
		if(result > 0) {
			AdminVo loginedAdminVo
			= adminService.getLoginedAdminVo(vo.getA_m_no());
			session.setAttribute("loginedAdminVo", loginedAdminVo);
			session.setMaxInactiveInterval(60*30);
		} else {
			nextPage = "admin/modify_fail";
		}
		return nextPage;
	}
	
	// 비밀 번호 설정 화면 이동
	@RequestMapping(value="/findPassword", method=RequestMethod.GET)
	public String findPasswordForm() {
		logger.info("[AdminController]findPasswordForm();");
		String nextPage = "admin/find_password_form";
		return nextPage;
	}
	
	// 비밀 번호 설정 기능
	@RequestMapping(value="/findPassword", method=RequestMethod.POST)
	public String findPasswordConfirm(AdminVo vo) {
		logger.info("[AdminController] findPasswordConfirm()");
		String nextPage = "admin/find_password_success";
		int result = adminService.findPasswordConfirm(vo);
		if(result <= 0)
			nextPage = "admin/find_password_fail";
		return nextPage;
	}
	
}
