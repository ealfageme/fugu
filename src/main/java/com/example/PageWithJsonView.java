package com.example;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.example.Entities.Menu;
import com.example.Entities.Restaurant;
import com.example.Entities.User;
import com.fasterxml.jackson.annotation.JsonView;

public class PageWithJsonView<T> extends PageImpl<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2670241571755638208L;

	public PageWithJsonView(Page<T> page) {
		super(page.getContent());
	}

	//@JsonView(Restaurant.Basic.class)
	@Override
	public int getTotalPages() {
		// TODO Auto-generated method stub
		return super.getTotalPages();
	}

	//@JsonView(SummaryView.class)
	@Override
	public long getTotalElements() {
		// TODO Auto-generated method stub
		return super.getTotalElements();
	}
	
	//@JsonView(SummaryView.class)
	@Override
	public int getNumberOfElements() {
		// TODO Auto-generated method stub
		return super.getNumberOfElements();
	}
	
	//@JsonView(SummaryView.class)
	@Override
	public List<T> getContent() {
		// TODO Auto-generated method stub
		return super.getContent();
	}

}
