package com.cn.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.domain.ProductModel;
@Service("solrSearch")
public class SolrSearchImpl implements SolrSearch {

	@Autowired
	private SolrServer solrServer;
	
	@Override
	public List<ProductModel> selectProduct(String search) {

		List<ProductModel> list = new ArrayList<>();
		
		//查询
		SolrQuery solrQuery = new SolrQuery();
				
		//查询条件
		//关键词
		solrQuery.setQuery(search);
				
		//设置默认域
		solrQuery.set("df","product_keywords");
		
		//高亮
		//1.打开高亮开关
		solrQuery.setHighlight(true);
		//2.指定高亮域
		solrQuery.addHighlightField("product_name");
		solrQuery.addHighlightField("product_description");
		//3.设置前缀  后缀
		solrQuery.setHighlightSimplePre("<span style='color:red'>");
		solrQuery.setHighlightSimplePost("</span>");
		
		//执行查询
		QueryResponse response = null;
		try {
			response = solrServer.query(solrQuery);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		
		SolrDocumentList docs = response.getResults();
		
		
		//返回高亮域 的结果集
		/**
		 * Map K id    V map
		 * Map K feild V list
		 * list list.get(0)
		 */
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		
		for (SolrDocument doc : docs) {
			ProductModel p = new ProductModel();
			System.out.println("-------------------------------");
			System.out.println(doc.get("id"));
			System.out.println(doc.get("product_name"));
			System.out.println(doc.get("product_price"));
			System.out.println(doc.get("product_catalog_name"));
			System.out.println(doc.get("product_description"));
			System.out.println(doc.get("product_picture"));
			System.out.println("-------------------------------");
			
			
			Map<String, List<String>> map = highlighting.get(doc.get("id"));
			List<String> productNameList = map.get("product_name");
			List<String> productDescriptionList = map.get("product_description");
			
			p.setPid(doc.get("id").toString());
			if (productNameList != null) {
				p.setName(productNameList.get(0));
			}else {
				p.setName(doc.get("product_name").toString());
			}
			p.setPrice(Float.parseFloat(doc.get("product_price").toString()));
			p.setCatalog_name(doc.get("product_catalog_name").toString());
			if (productDescriptionList != null) {
				p.setDescription(productDescriptionList.get(0));
			}else{
				p.setDescription(doc.get("product_description").toString());
			}
			p.setPicture(doc.get("product_picture").toString());
			list.add(p);
		}
		
		return list;
	}

}
