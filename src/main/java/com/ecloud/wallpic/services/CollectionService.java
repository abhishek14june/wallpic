package com.ecloud.wallpic.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecloud.wallpic.configuration.UrlConstants;
import com.ecloud.wallpic.configuration.WallpicConstants;
import com.ecloud.wallpic.datamodels.PhotoUrl;
import com.ecloud.wallpic.datamodels.PicCollection;
import com.ecloud.wallpic.datamodels.PictureItem;
import com.ecloud.wallpic.datamodels.User;
import com.ecloud.wallpic.helpers.HttpHelper;
import com.ecloud.wallpic.helpers.UrlBuilders;

@Component
public class CollectionService {

	@Autowired
	UrlBuilders urlBuilders;
	@Autowired
	HttpHelper httpHelper;
	public List<PicCollection> fetchAllCollectionsOfAUser(String userId){
		List<PicCollection> response = null;
		String reqUrl = urlBuilders.getCollectionRequestUrl(userId);
		JSONArray apiResponse =httpHelper.callForJsonArrayResponse(reqUrl);
		System.out.println(apiResponse);
		response = processAllCollectionResponse(apiResponse);			
		return response;
	}
	
	public List<PictureItem> fetchAllImagesOfaCollection(PicCollection request){
		List<PictureItem> response = new ArrayList<>();
		if(null != request && request.getTotalPhotos() >0) {
			int totalPages = request.getTotalPhotos()/WallpicConstants.PER_PAGE_MAX;
			for(int i=0; i<totalPages+1; i++) {
				List<PictureItem> temp = getImages(request.getId(),i+1);
				if(null != temp)
				response.addAll(temp);
			}
		}
		return response;
	}
	private List<PictureItem> getImages(String id,int pageNumber) {
		List<PictureItem> response = null;
		String reqUrl = urlBuilders.getCollectionPhotosPerPageUrl(id,pageNumber);
		JSONArray apiResponse = httpHelper.callForJsonArrayResponse(reqUrl);
		if(null != apiResponse) {
			response = processPictureItem(apiResponse);
		}
		return response;
	}

	private List<PictureItem> processPictureItem(JSONArray apiResponse) {
		List<PictureItem> response = new ArrayList<>();
		for(int i = 0; i< apiResponse.length();i++) {
			JSONObject item = apiResponse.getJSONObject(i);
			PictureItem temp = getPictureItem(item);
			if(null != temp)
			response.add(temp);
		}
		return response;
	}

	private PictureItem getPictureItem(JSONObject item) {
		PictureItem response = new PictureItem();
		try {
			if(!item.isNull(WallpicConstants.ID))
				response.setId(item.getString(WallpicConstants.ID));
			if(!item.isNull(WallpicConstants.DESCRIPTION)) {
				response.setDescription(item.getString(WallpicConstants.DESCRIPTION));
			}
			else if(!item.isNull(WallpicConstants.ALT_DESCRIPTION)) {
				response.setDescription(item.getString(WallpicConstants.ALT_DESCRIPTION));
			}
			if(!item.isNull(WallpicConstants.WIDTH))
				response.setWidth(item.getInt(WallpicConstants.WIDTH));
			if(!item.isNull(WallpicConstants.HEIGHT))
				response.setHeight(item.getInt(WallpicConstants.HEIGHT));
			if(!item.isNull(WallpicConstants.BLURHASH))
				response.setBlurHash(item.getString(WallpicConstants.BLURHASH));
			if(!item.isNull(WallpicConstants.USER)) {
				User user =processUserMeta(item.getJSONObject(WallpicConstants.USER));
				if(null != user)
				response.setUser(user);
			}
			if(!item.isNull(WallpicConstants.URLS)) {
				PhotoUrl url =processUrl(item.getJSONObject(WallpicConstants.URLS));
				if(null != url)
				response.setUrl(url);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private PhotoUrl processUrl(JSONObject jsonObject) {
		PhotoUrl url = new PhotoUrl();
		if(!jsonObject.isNull(WallpicConstants.RAW))
			url.setRaw(jsonObject.getString(WallpicConstants.RAW));
		if(!jsonObject.isNull(WallpicConstants.FULL))
			url.setFull(jsonObject.getString(WallpicConstants.FULL));
		if(!jsonObject.isNull(WallpicConstants.SMALL))
			url.setSmall(jsonObject.getString(WallpicConstants.SMALL));
		if(!jsonObject.isNull(WallpicConstants.THUMB))
			url.setThumb(jsonObject.getString(WallpicConstants.THUMB));
		if(!jsonObject.isNull(WallpicConstants.REGULAR))
			url.setRegular(jsonObject.getString(WallpicConstants.THUMB));
		return url;
	}

	private User processUserMeta(JSONObject jsonObject) {
		User user = new User();
		try {
			if(!jsonObject.isNull(WallpicConstants.ID))
				user.setId(jsonObject.getString(WallpicConstants.ID));
			if(!jsonObject.isNull(WallpicConstants.TOTAL_LIKES))
				user.setTotalLikes(jsonObject.getInt(WallpicConstants.TOTAL_LIKES));
			if(!jsonObject.isNull(WallpicConstants.TOTAL_PHOTOS))
				user.setTotalPhotos(jsonObject.getInt(WallpicConstants.TOTAL_PHOTOS));
			if(!jsonObject.isNull(WallpicConstants.BIO))
				user.setBio(jsonObject.getString(WallpicConstants.BIO));
			if(!jsonObject.isNull(WallpicConstants.FIRST_NAME))
				user.setFirstName(jsonObject.getString(WallpicConstants.FIRST_NAME));
			if(!jsonObject.isNull(WallpicConstants.LAST_NAME))
				user.setLastName(jsonObject.getString(WallpicConstants.LAST_NAME));
			if(!jsonObject.isNull(WallpicConstants.INSTA_USERNAME))
				user.setInstaUsername(jsonObject.getString(WallpicConstants.INSTA_USERNAME));
			if(!jsonObject.isNull(WallpicConstants.TWITTER_USERNAME))
				user.setTwitterUsername(jsonObject.getString(WallpicConstants.TWITTER_USERNAME));
			if(!jsonObject.isNull(WallpicConstants.USERNAME))
				user.setUserName(jsonObject.getString(WallpicConstants.USERNAME));
			if(!jsonObject.isNull(WallpicConstants.PORTFOLIO_URL))
				user.setPortfolioUrl(jsonObject.getString(WallpicConstants.PORTFOLIO_URL));
			if(!jsonObject.isNull(WallpicConstants.PROFILE_IMAGE))
				user.setProfilePicUrl(getProfilePic(jsonObject.getJSONObject(WallpicConstants.PROFILE_IMAGE)));
			
			return user;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getProfilePic(JSONObject jsonObject) {
		String photUrl = null;
		if(!jsonObject.isNull(WallpicConstants.SMALL)) {
			photUrl = jsonObject.getString(WallpicConstants.SMALL);
			photUrl.replace("&h=32&w=32","&h=512&w=512");
			return photUrl;
		}
			
		return null;
	}

	private List<PicCollection> processAllCollectionResponse(JSONArray apiResponse) {
		List<PicCollection> response = new ArrayList<>();
		for(int i =0 ;i<apiResponse.length();i++) {
			JSONObject item = apiResponse.getJSONObject(i);
			PicCollection dataItem = processMetaForCollection(item);
			response.add(dataItem);
			
		}
		return response;
	}
	private PicCollection processMetaForCollection(JSONObject item) {
		try {
			if(null != item) {
				PicCollection response = new PicCollection();
				if(!item.isNull(WallpicConstants.ID))
				response.setId(item.getString(WallpicConstants.ID));
				if(!item.isNull(WallpicConstants.TITLE))
				response.setTitle(item.getString(WallpicConstants.TITLE));
				if(!item.isNull(WallpicConstants.DESCRIPTION))
				response.setDescription(item.getString(WallpicConstants.DESCRIPTION));
				if(!item.isNull(WallpicConstants.TOTAL_PHOTOS))
					response.setTotalPhotos(item.getInt(WallpicConstants.TOTAL_PHOTOS));
				if(!item.isNull(WallpicConstants.COVER_PHOTO)) {
					JSONObject coverPhotObject = item.getJSONObject(WallpicConstants.COVER_PHOTO);
					if(null != coverPhotObject && !coverPhotObject.isNull(WallpicConstants.URLS)) {
						PhotoUrl photUrlObject = getPhotoUrls(coverPhotObject.getJSONObject(WallpicConstants.URLS));
						response.setCoverPhoto(photUrlObject);
					}
				}
				return response;
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private PhotoUrl getPhotoUrls(JSONObject coverPhotObject) {
		try {
			PhotoUrl response = new PhotoUrl();
			if(!coverPhotObject.isNull(WallpicConstants.FULL))
			response.setFull(coverPhotObject.getString(WallpicConstants.FULL));
			if(!coverPhotObject.isNull(WallpicConstants.RAW))
			response.setRaw(coverPhotObject.getString(WallpicConstants.RAW));
			if(!coverPhotObject.isNull(WallpicConstants.REGULAR))
			response.setRegular(coverPhotObject.getString(WallpicConstants.REGULAR));
			if(!coverPhotObject.isNull(WallpicConstants.SMALL))
			response.setSmall(coverPhotObject.getString(WallpicConstants.SMALL));
			if(!coverPhotObject.isNull(WallpicConstants.THUMB))
			response.setThumb(coverPhotObject.getString(WallpicConstants.THUMB));
			return response;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
