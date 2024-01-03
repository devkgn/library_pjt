package com.goodee.library.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {

	private static final Logger logger = LoggerFactory.getLogger(AdminDao.class);
	private final String namespace = "com.goodee.library.admin.AdminDao.";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 아이디 중복 검사
	public boolean isAdmin(String a_m_id){
		int result = -1;
		try {
			result = sqlSession.selectOne(namespace+"isAdmin",a_m_id);
		}catch(Exception e) {
			logger.error(e.toString());
		}
		if(result > 0)
			return true;
		else 
			return false;
	}
	
	// 회원 정보 등록
	public int insertAdmin(AdminVo vo) {
		logger.info("[AdminDao]insertAdmin");
		vo.setA_m_pw(passwordEncoder.encode(vo.getA_m_pw()));
		int result = -1;
		result = sqlSession.insert(namespace+"insertAdmin",vo);
		return result;
	}
	
	//로그인 인증 처리
	public AdminVo selectAdmin(AdminVo vo) {
		logger.info("[AdminDao]selectAdmin()");
		List<AdminVo> adminVos = new ArrayList<AdminVo>();
		try {
			adminVos = sqlSession.selectList(namespace+"selectAdmin",vo.getA_m_id());
			if(passwordEncoder.matches(vo.getA_m_pw(),adminVos.get(0).getA_m_pw()) == false) {
				adminVos.clear();
			}
		}catch(Exception e) {
			logger.error(e.toString());
		}
		return adminVos.size() > 0 ? adminVos.get(0) : null;
	}
	
	// 회원 목록 조회
	public List<AdminVo> selectAdminList(){
		logger.info("[AdminDao] selectAdminList()");
		List<AdminVo> adminVos = new ArrayList<AdminVo>();
		try {
			adminVos = sqlSession.selectList(namespace+"selectAdminList");
		}catch(Exception e) {
			logger.error(e.toString());
		}
		return adminVos;
	}
	
	// 회원 정보 수정
	public int updateAdmin(AdminVo vo) {
		logger.info("[AdminDao] modifyAdminConfirm()");
		int result = -1;
		try {
			result = sqlSession.update(namespace+"modifyAdminConfirm",vo);
		}catch(Exception e) {
			logger.error(e.toString());
		}
		return result;
	}
	
	// 회원 단일 정보 조회
	public AdminVo selectAdmin(int a_m_no) {
		logger.info("[AdminDao] selectAdmin()");
		AdminVo adminVo = new AdminVo();
		try {
			adminVo = sqlSession.selectOne(namespace+"selectAdminOne",a_m_no);
		}catch(Exception e) {
			logger.error(e.toString());
		}
		return adminVo;
	}
	
	// 회원 정보 조회 (비밀 번호 설정 여부)
	public AdminVo selectAdmin(Map<String,String> map) {
		logger.info("[AdminDao] selectAdmin()");
		AdminVo adminVo = new AdminVo();
		try {
			adminVo = sqlSession.selectOne(namespace+"selectAdminForPassword",map);
		}catch(Exception e) {
			logger.error(e.toString());
		}
		return adminVo;
	}
	
	// 비밀번호 데이터베이스에서 수정
	public int updatePassword(String a_m_id, String newPassword) {
		logger.info("[AdminDao] updatePassword()");
		int result = -1;
		Map<String,String> map = new HashMap<String,String>();
		map.put("a_m_id", a_m_id);
		map.put("a_m_password", passwordEncoder.encode(newPassword));
		try {
			result = sqlSession.update(namespace+"updatePassword",map);
		}catch(Exception e) {
			logger.error(e.toString());
		}
		return result;
	}
}
