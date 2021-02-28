package com.ecloud.wallpic.datamodels;

public class User {

	private String id;
	private String firstName;
	private String lastName;
	private String userName;
	private String twitterUsername;
	private String instaUsername;
	private String portfolioUrl;
	private String bio;
	private String profilePicUrl;
	private int totalPhotos;
	private int totalLikes;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTwitterUsername() {
		return twitterUsername;
	}
	public void setTwitterUsername(String twitterUsername) {
		this.twitterUsername = twitterUsername;
	}
	public String getInstaUsername() {
		return instaUsername;
	}
	public void setInstaUsername(String instaUsername) {
		this.instaUsername = instaUsername;
	}
	public String getPortfolioUrl() {
		return portfolioUrl;
	}
	public void setPortfolioUrl(String portfolioUrl) {
		this.portfolioUrl = portfolioUrl;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getProfilePicUrl() {
		return profilePicUrl;
	}
	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}
	public int getTotalPhotos() {
		return totalPhotos;
	}
	public void setTotalPhotos(int totalPhotos) {
		this.totalPhotos = totalPhotos;
	}
	public int getTotalLikes() {
		return totalLikes;
	}
	public void setTotalLikes(int totalLikes) {
		this.totalLikes = totalLikes;
	}
	
	
}
