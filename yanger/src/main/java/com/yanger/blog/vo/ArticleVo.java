package com.yanger.blog.vo;

import java.io.Serializable;
import java.util.Date;

import com.yanger.mybatis.annotations.Column;

import lombok.Data;

/**
 *
 * 表article的VO对象,通过com.yanger.generator包代码工具自动生成<br/>
 * 对应表名：article
 *
 */
@Data
public class ArticleVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 对应字段：article_id,备注：主键id */
	private Integer articleId;

	/** 对应字段：module,备注：模块 */
	private String module;
	
	/** 对应字段：type,备注：所属类型 */
	private String type;

	/** 对应字段：classify,备注：分类（具体） */
	private String classify;

	/** 对应字段：title,备注：标题 */
	private String title;

	/** 对应字段：author,备注：作者 */
	private String author;

	/** 对应字段：rux_words,备注：关键字 */
	private String ruxWords;

	/** 对应字段：summary,备注：简介 */
	private String summary;

	/** 对应字段：content,备注：内容 */
	private String content;

	/** 对应字段：art_img_path,备注：图片路径 */
	private String artImgPath;

	/** 对应字段：likes,备注：喜欢数量 */
	private Integer likes;

	/** 对应字段：views,备注：浏览数量 */
	private Integer views;

	/** 对应字段：commons,备注：评论数量 */
	private Integer commons;

	/** 对应字段：user_id,备注：用户id */
	private Integer userId;

	/** 对应字段：status,备注：数据状态 */
	private String status;

	/** 对应字段：insert_time,备注：创建时间 */
	private Date insertTime;

	/** 对应字段：update_time,备注：更新时间 */
	private Date updateTime;

}
