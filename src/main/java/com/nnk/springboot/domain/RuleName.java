package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;

@Entity
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	@Length(max = 125)
	private String name;
	@Length(max = 125)
	private String description;
	@Length(max = 125)
	private String json;
	@Length(max = 512)
	private String template;
	@Length(max = 125)
	private String sqlStr;
	@Length(max = 125)
	private String sqlPart;
	
	
	public RuleName() {
	}


	public RuleName(@Length(max = 125) String name, @Length(max = 125) String description,
			@Length(max = 125) String json, @Length(max = 512) String template, @Length(max = 125) String sqlStr,
			@Length(max = 125) String sqlPart) {
		this.name = name;
		this.description = description;
		this.json = json;
		this.template = template;
		this.sqlStr = sqlStr;
		this.sqlPart = sqlPart;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getJson() {
		return json;
	}


	public void setJson(String json) {
		this.json = json;
	}


	public String getTemplate() {
		return template;
	}


	public void setTemplate(String template) {
		this.template = template;
	}


	public String getSqlStr() {
		return sqlStr;
	}


	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}


	public String getSqlPart() {
		return sqlPart;
	}


	public void setSqlPart(String sqlPart) {
		this.sqlPart = sqlPart;
	}

	
	
}
