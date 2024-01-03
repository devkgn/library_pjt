package com.goodee.library.book;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	private static final Logger logger = LoggerFactory.getLogger(BookService.class);
	
	final static public int BOOK_CREATE_SUCCESS = 1;
	final static public int BOOK_CREATE_FAIL = -1;
	
	@Autowired
	BookDao bookDao;
	
	// 신규 도서 등록
	public int createBookConfirm(BookVo vo) {
		logger.info("[BookService] createBookService()");
		if(bookDao.insertBook(vo) > 0)
			return BOOK_CREATE_SUCCESS;
		else 
			return BOOK_CREATE_FAIL;
	}
	
	// 도서 목록 조회
	public List<BookVo> selectBookList(BookVo vo){
		logger.info("[BookService] selectBookList()");
		return bookDao.selectBookList(vo);
	}
	
	// 도서 목록 개수 조회
	public int selectBookCount(String b_name) {
		logger.info("[BookService] selectBookCount()");
		return bookDao.selectBookCount(b_name);
	}
	
	// 도서 상세 조회
	public BookVo bookDetail(int b_no) {
		logger.info("[BookService] bookDetail()");
		return bookDao.selectBook(b_no);
	}
	
	// 도서 수정용 폼 조회 
	public BookVo modifyBookForm(int b_no) {
		logger.info("[BookService] modifyBookForm()");
		return bookDao.selectBook(b_no);
	}
	
	// 도서 수정
	public int modifyBookConfirm(BookVo vo) {
		logger.info("[BookService] modifyBookConfirm()");
		return bookDao.updateBook(vo);
	}
	
	// 도서 삭제
	public int deleteBookConfirm(int b_no) {
		logger.info("[BookService] deleteBookConfirm()");
		return bookDao.deleteBook(b_no);
	}
}
