package com.cn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cn.dao.SolrSearch;
import com.cn.domain.ProductModel;

@Controller
public class ProductController {
	
	@Autowired
	private SolrSearch solrSearch;
	
	@RequestMapping(value="/{formName}")
	 public String loginForm(@PathVariable String formName){
		// 动态跳转页面
		return formName;
	}
	
	
	
	@RequestMapping("/search")
	public String search(@RequestParam("search") String search,Model model){
		List<ProductModel> product = solrSearch.selectProduct(search);
		model.addAttribute("product", product);
		return "content";
	} 
}
