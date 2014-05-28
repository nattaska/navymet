package com.ss.sq.common.entity;


public class MenuDto {
	private String id;
	private String text;
	private String link;
	private MenuDto MenuDto;

	public MenuDto() {
	}
	
	public MenuDto(String id,String text,String link) {
		this();
		this.id = id;
		this.text = text;
		this.link = link;
	}
	
	public MenuDto(String id,String text,String link, MenuDto MenuDto) {
		this(id, text,link);
		this.MenuDto = MenuDto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public MenuDto getMenuDto() {
		return MenuDto;
	}

	public void setMenuDto(MenuDto MenuDto) {
		this.MenuDto = MenuDto;
	}

}
