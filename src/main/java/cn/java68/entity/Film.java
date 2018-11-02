package cn.java68.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 电影实体
 * @author Administrator
 *
 */
@Entity
@Table(name="t_film")
@Data
public class Film {

	@Id
	@GeneratedValue
	private Integer id; // 编号
	

	@Column(length=200)
	private String name; // 视频名称

	private String url;//视频地址
	

	private Integer catId; // 所属视频归属类名
	
	@Lob
	@Column(columnDefinition="TEXT")
	private String content; // 视频简介
	
	@Column(length=300)
	private String imageName; // 视频封面
	



	
	
}
