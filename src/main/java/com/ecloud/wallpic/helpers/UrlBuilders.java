package com.ecloud.wallpic.helpers;

import com.ecloud.wallpic.configuration.WallhavenConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecloud.wallpic.configuration.UnsplashConfig;
import com.ecloud.wallpic.configuration.UrlConstants;
import com.ecloud.wallpic.configuration.WallpicConstants;

@Component
public class UrlBuilders {

	@Autowired
	UnsplashConfig config;
	public String getCollectionRequestUrl(String username) {
		StringBuilder sb= new StringBuilder();
		sb.append(UrlConstants.URL_BASE).append(UrlConstants.USERS).append(username).append("/").
		append(UrlConstants.COLLECTIONS).append("?").append(UrlConstants.CLIENT_ID).append(config.getClientId());
		return sb.toString();
	}
	public  String getCollectionPhotosPerPageUrl(String id, int pageNumber) {
		StringBuilder sb= new StringBuilder();
		sb.append(UrlConstants.URL_BASE).append(UrlConstants.COLLECTIONS).append(id).append("/").append(UrlConstants.PHOTOS).append("?").append(UrlConstants.PER_PAGE)
		.append("=").append(WallpicConstants.PER_PAGE_MAX).append("&").append(UrlConstants.PAGE).append("=").append(pageNumber)
		.append("&").append(UrlConstants.CLIENT_ID).append(config.getClientId());
		return sb.toString();
	}

	public String getImageSearchViaTagURL(int tagId,int pageNumber){
		StringBuilder sb = new StringBuilder();
		sb.append(WallhavenConstants.TAG_SEARCH_BASE)
				.append(tagId)
				.append(WallhavenConstants.PAGE_PARAM)
				.append(pageNumber);
		return sb.toString();
	}
}
