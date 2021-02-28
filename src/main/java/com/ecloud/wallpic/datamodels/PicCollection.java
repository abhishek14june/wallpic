package com.ecloud.wallpic.datamodels;

import java.util.List;

public class PicCollection {

	private String id;
	private String title;
	private String description;
	private int totalPhotos;
	private PhotoUrl coverPhoto;
	private List<PictureItem> photosInCollection;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTotalPhotos() {
		return totalPhotos;
	}
	public void setTotalPhotos(int totalPhotos) {
		this.totalPhotos = totalPhotos;
	}
	public PhotoUrl getCoverPhoto() {
		return coverPhoto;
	}
	public void setCoverPhoto(PhotoUrl coverPhoto) {
		this.coverPhoto = coverPhoto;
	}
	public List<PictureItem> getPhotosInCollection() {
		return photosInCollection;
	}
	public void setPhotosInCollection(List<PictureItem> photosInCollection) {
		this.photosInCollection = photosInCollection;
	}
	
}
