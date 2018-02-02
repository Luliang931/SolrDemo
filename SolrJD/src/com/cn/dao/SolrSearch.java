package com.cn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cn.domain.ProductModel;

@Repository
public interface SolrSearch {
	public List<ProductModel> selectProduct(String search);
}
