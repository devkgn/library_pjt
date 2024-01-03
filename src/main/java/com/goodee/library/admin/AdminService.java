package com.goodee.library.admin;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
	
	final static public int MEMBER_ALREADY_EXIST = 0;
	final static public int MEMBER_CREATE_SUCCESS = 1;
	final static public int MEMBER_CREATE_FAIL = -1;
	
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	JavaMailSenderImpl javaMailSenderImpl;
	
	// 회원가입
	public int createMember(AdminVo vo) {
		logger.info("[AdminService]createMember()");		
		boolean isMember = adminDao.isAdmin(vo.getA_m_id());
		
		if(!isMember) {
			int result = adminDao.insertAdmin(vo);

			if(result > 0) {
				return MEMBER_CREATE_SUCCESS;			
			} else {
				return MEMBER_CREATE_FAIL;
			}
			
		} else{
			return MEMBER_ALREADY_EXIST; 
		}
	}
	
	// 로그인
	public AdminVo loginConfirm(AdminVo vo) {
		logger.info("[AdminService] loginConfirm()");
		AdminVo loginedAdminVo = adminDao.selectAdmin(vo);
		if(loginedAdminVo != null)
			logger.info("[AdminService] ADMIN MEMBER LOGIN SUCCESS!!");
		else
			logger.info("[AdminService] ADMIN MEMBER LOGIN FAIL!!");
		return loginedAdminVo;
	}
	
	// 회원 목록 조회
	public List<AdminVo> listupAdmin(){
		logger.info("[AdminService] listupAdmin()");
		return adminDao.selectAdminList();
	}
	
	// 회원 정보 수정
	public int modifyAdminConfirm(AdminVo vo) {
		logger.info("[AdminService] modifyAdminConfirm()");
		return adminDao.updateAdmin(vo);
	}
	
	// 회원 단일 정보 조회
	public AdminVo getLoginedAdminVo(int a_m_no) {
		logger.info("[AdminService] getLoginedAdminVo()");
		return adminDao.selectAdmin(a_m_no);
	}
	
	// 비밀 번호 설정 기능
	public int findPasswordConfirm(AdminVo vo) {
		logger.info("[AdminService] findPasswordConfirm()");
		Map<String,String> map = new HashMap<String,String>();
		map.put("a_m_id", vo.getA_m_id());
		map.put("a_m_name", vo.getA_m_name());
		map.put("a_m_mail", vo.getA_m_mail());
		
		AdminVo selectedAdminVo = adminDao.selectAdmin(map);
		int result = 0;
		if(selectedAdminVo != null) {
			String newPassword = createNewPassword();
			result = adminDao.updatePassword(vo.getA_m_id(),newPassword);
			
			if(result > 0)
				sendNewPasswordByMail(vo.getA_m_mail(),newPassword);
		}
		return result;
	}
	
	// 새로운 비밀번호 메일로 보내기
	private String createNewPassword() {
		logger.info("[AdminService] createNewPassword()");
		char[] chars = new char[] {
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
				'u', 'v', 'w', 'x', 'y', 'z'
				};
		StringBuffer stringBuffer = new StringBuffer();
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.setSeed(new Date().getTime());
		
		int index = 0;
		int length = chars.length;
		for (int i = 0; i < 8; i++) {
			index = secureRandom.nextInt(length);
		
			if (index % 2 == 0) 
				stringBuffer.append(String.valueOf(chars[index]).toUpperCase());
			else
				stringBuffer.append(String.valueOf(chars[index]).toLowerCase());
		
		}
		return stringBuffer.toString();
	}
	
	private void sendNewPasswordByMail(String toMailAddr, String newPassword) {
		logger.info("[AdminMemberService] sendNewPasswordByMail()");
		
		final MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				mimeMessageHelper.setTo(toMailAddr);
				mimeMessageHelper.setSubject("[구디 도서관] 새 비밀번호 안내입니다.");
				mimeMessageHelper.setText("새 비밀번호 : " + newPassword, true);
			}
		};
		javaMailSenderImpl.send(mimeMessagePreparator);
	}
}
