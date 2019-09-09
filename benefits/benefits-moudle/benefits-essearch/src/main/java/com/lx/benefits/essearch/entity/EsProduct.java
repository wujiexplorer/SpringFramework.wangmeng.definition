package com.lx.benefits.essearch.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Document(indexName = "product",shards = 1,replicas = 0)
@Data
public class EsProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	/** spu编码 */
	@Id
	private Long spuCode;
	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String goodsName;
	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String keywords;
	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String goodsSpecname;
	private Double lowPrice;

	private Long brandId;
	@Field(type = FieldType.Keyword)
	private String brandName;
	private Long categoryId;
	@Field(type = FieldType.Keyword)
	private String categoryName;
	private Long categoryId2;
	@Field(type = FieldType.Keyword)
	private String categoryName2;
	private Long categoryId3;
	@Field(type = FieldType.Keyword)
	private String categoryName3;
	private Long supplierId;
	@Field(type = FieldType.Keyword)
	private String supplierName;
	@Field(type = FieldType.Date)
	private Date statedTime;

	public EsProduct() {

	}

	public EsProduct(Long spuCode) {
		this.spuCode = spuCode;
	}

}
