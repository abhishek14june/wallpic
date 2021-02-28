package com.ecloud.wallpic.datamodels;

public class PictureItem {

	
	private String id;
	private PhotoUrl url;
	private User user;
	private String description;
	private String altDescription;
	private int width;
	private int height;
	private String blurHash;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public PhotoUrl getUrl() {
		return url;
	}
	public void setUrl(PhotoUrl url) {
		this.url = url;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAltDescription() {
		return altDescription;
	}
	public void setAltDescription(String altDescription) {
		this.altDescription = altDescription;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getBlurHash() {
		return blurHash;
	}
	public void setBlurHash(String blurHash) {
		this.blurHash = blurHash;
	}
	
}
