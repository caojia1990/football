package com.eastng.football.web.view;

/**
 * 延迟加载树VO
 * @author caojia
 */
public class Tree<T> {
	
	//节点Id
	private String id;
	
	//节点名称
	private String text;
	
	//节点状态
	private String state;
	
	//属性
	private T attributes;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the attributes
	 */
	public T getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(T attributes) {
		this.attributes = attributes;
	}
	
	
}
