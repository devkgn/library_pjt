package com.goodee.library.book;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.library.util.UploadFileService;


@Controller
@RequestMapping("/book")
public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UploadFileService uploadFileService;
	
	// 도서 등록 화면 이동
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String createBookForm(){
		logger.info("[BookController] createBookForm()");
		String nextPage = "book/create";
		return nextPage;
	}
	
	// 도서 등록 기능
	@RequestMapping(method=RequestMethod.POST)
	public String createBookConfirm(BookVo vo, @RequestParam("file") MultipartFile file) {
		logger.info("[BookController] createBookConfirm()");
		String nextPage = "book/create_fail";
		String savedFileName = uploadFileService.upload(file);
		if(savedFileName != null) {
			vo.setB_thumbnail(savedFileName);
			int result = bookService.createBookConfirm(vo);
			if(result > 0)
				nextPage = "book/create_success";
		}
		return nextPage;
	}
	
	// 도서 목록 조회 기능
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView selectBookList(BookVo vo, ModelAndView mav) {
		logger.info("[BookController] selectBookList()");
		String nextPage = "book/listup";
		vo.setTotalCount(bookService.selectBookCount(vo.getB_name()));
		List<BookVo> bookVos = bookService.selectBookList(vo);
		mav.addObject("bookVos",bookVos);
		mav.addObject("pagingVo",vo);
		mav.setViewName(nextPage);
		return mav;
	}
		
	// 도서 상세 이동
	@RequestMapping(value="/{b_no}",method=RequestMethod.GET)
	public ModelAndView bookDetail(@PathVariable int b_no, ModelAndView mav) {
		logger.info("[BookController] bookDetail()");
		String nextPage = "book/detail";
		BookVo vo = bookService.bookDetail(b_no);
		mav.addObject("bookVo",vo);
		mav.setViewName(nextPage);
		return mav;
	}
	
	// 도서 수정 화면
	@RequestMapping(value="/modify/{b_no}", method=RequestMethod.GET)
	public ModelAndView modifyBookForm(@PathVariable int b_no, ModelAndView mav) {
		logger.info("[BookController] modifyBookForm()");
		String nextPage = "book/modify";
		BookVo bookVo = bookService.modifyBookForm(b_no);
		mav.addObject("bookVo",bookVo);
		mav.setViewName(nextPage);
		return mav;		
	}
	
	// 도서 수정 기능
	@RequestMapping(value="/modify/{b_no}", method=RequestMethod.POST)
	public String modifyBookConfirm(BookVo vo, @RequestParam("file") MultipartFile file) {
		logger.info("[BookController] modifyBookConfirm()");
		String nextPage = "book/modify_success";
		if(!file.getOriginalFilename().equals("")) {
			String savedFileName = uploadFileService.upload(file);
			if(savedFileName != null) {
				vo.setB_thumbnail(savedFileName);
			}
		}
		int result = bookService.modifyBookConfirm(vo);
		
		if(result <= 0)
			nextPage = "book/modify_fail";
		return nextPage;
	}
	
	// 도서 삭제 기능
	@RequestMapping(value="/{b_no}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteBookConfirm(@PathVariable int b_no){
		logger.info("[BookController] deleteBookConfirm()");
		String result = "200";
		if(bookService.deleteBookConfirm(b_no) > 0)
			result = "400";
		return ResponseEntity.ok(result);
	}
}
