package com.sampe.sprint.boot.utility.service;

import java.io.Serializable;

/**
 * Filter the request to find the customer
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 21/10/2018 19:43:26
 */
public class CustomerFilter implements Serializable {

	/**
	 * 2322141986091863904L
	 */
	private static final long serialVersionUID = 2322141986091863904L;

	private Long id;
	private String name;
	private Long category;

	/**
	 * @return the category
	 */
	public Long getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Long category) {
		this.category = category;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerFilter [id=" + id + ", name=" + name + ", category=" + category + "]";
	}

}
