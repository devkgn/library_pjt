package com.goodee.library.book;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {
	
	private static final Logger logger = LoggerFactory.getLogger(BookDao.class);
	private final String namespace = "com.goodee.library.book.bookDao.";
	
	@Autowired
	private SqlSession sqlSession;
	
	// 신규 도서 등록
	public int insertBook(BookVo vo) {
		logger.info("[BookDao] insertBook()");
		int result = -1;
		try {
			result = sqlSession.insert(namespace+"insertBook",vo);			
		}catch(Exception e) {
			logger.error(e.toString());
		}
		return result;
	}
	
	// 도서 목록 조회
	public List<BookVo> selectBookList(BookVo vo){
		logger.info("[BookDao] selectBookList()");
		List<BookVo> result = new ArrayList<BookVo>();
		try {
			result = sqlSession.selectList(namespace+"selectBookList",vo);
		}catch(Exception e) {
			logger.error(e.toString());
		}
		return result;
	}
	
	// 도서 목록 개수 조회
	public int selectBookCount(String b_name) {
		logger.info("[BookDao] selectBookCount()");
		int result = 0;
		try {
			result = sqlSession.selectOne(namespace+"selectBookCount",b_name);
		}catch(Exception e) {
			logger.error(e.toString());
		}
		return result;
	}
	
	// 도서 상세 조회
	public BookVo selectBook(int b_no) {
		logger.info("[BookDao] selectBook()");
		BookVo vo = new BookVo();
		try {
			vo = sqlSession.selectOne(namespace+"selectBook",b_no);
		}catch(Exception e) {
			logger.error(e.toString());
		}
		return vo;
	}
	
	// 도서 수정
	public int updateBook(BookVo vo) {
		logger.info("[BookDao] updateBook()");
		int result = -1;
		try {
			result = sqlSession.update(namespace+"updateBook",vo);
		}catch(Exception e) {
			logger.error(e.toString());
		}
		return result;
	}
	
	// 도서 삭제
	public int deleteBook(int b_no) {
		logger.info("[BookDao] deleteBookConfirm()");
		int result = -1;
		try {
			result = sqlSession.delete(namespace+"deleteBook",b_no);
		}catch(Exception e) {
			logger.error(e.toString());
		}
		return result;
	}
}
