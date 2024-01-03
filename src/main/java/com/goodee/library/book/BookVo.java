package com.goodee.library.book;

import com.goodee.library.util.PagingVo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookVo extends PagingVo{
	private int b_no;
	private String b_thumbnail;
	private String b_name;
	private String b_author;
	private String b_publisher;
	private String b_publish_year;
	private String b_reg_date;
	private String b_mod_date;
}
