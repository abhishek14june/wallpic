package com.ecloud.wallpic.controllers;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.ecloud.wallpic.datamodels.*;
import com.ecloud.wallpic.helpers.DataHelper;
import com.ecloud.wallpic.helpers.FileHelper;
import com.ecloud.wallpic.services.WallHavenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecloud.wallpic.services.CollectionService;

@RestController
public class CollectionController {

	@Autowired
	CollectionService collectionService;

	@Autowired
	WallHavenService wallHavenService;

	@Autowired
	FileHelper fileHelper;

	@Autowired
	DataHelper dataHelper;
	
	@Cacheable(value="pic_collection", key = "'mykey'")
	@GetMapping("collections/all")
	List<PicCollection> getPeoplePhots(){
		List<PicCollection> collections = new ArrayList<>();
//	    List<PicCollection>	userCollections = collectionService.fetchAllCollectionsOfAUser("abhi14june");
//	    return userCollections;
		List<Category> categories = null ;
		try{
			categories = getAllCategories();
			for(Category item : categories){
				PicCollection picItem = new PicCollection();
				picItem.setId(item.getId()+"");
				picItem.setTitle(item.getName());
				PhotoUrl coverPhoto = new PhotoUrl();
				coverPhoto.setRegular("https://w.wallhaven.cc/full/l3/wallhaven-l3xoor.jpg");
				picItem.setCoverPhoto(coverPhoto);
				collections.add(picItem);
			}
		}
		catch (Exception e){

		}
	return collections;
	}
	
	@GetMapping("collections/{collectionId}/photos")
	List<PictureItem> getCollectionPhotos(@PathVariable("collectionId") int collectionId){
		List<PictureItem> response = dataHelper.searchByCategoryId(collectionId);
		Collections.shuffle(response);
		response = response.stream().limit(100).collect(Collectors.toList());
//	    List<PicCollection>	userCollections = collectionService.fetchAllCollectionsOfAUser("abhi14june");
//	    for(PicCollection collection : userCollections) {
//	    	if(collection.getId().equalsIgnoreCase(collectionId)) {
//	    		response.addAll(collectionService.fetchAllImagesOfaCollection(collection));
//	    	}
//	    }
	    return response;
	}
	
	
	@GetMapping("collections/all/photos")
	List<PictureItem> getAllPhotos(){
		List<PictureItem> response = new ArrayList<PictureItem>();
	    List<PicCollection>	userCollections = collectionService.fetchAllCollectionsOfAUser("abhi14june");
	    for(PicCollection collection : userCollections) {
	    	
	    	response.addAll(collectionService.fetchAllImagesOfaCollection(collection));
	    }
	    return response;
	}

	@GetMapping("categories/all")
	List<Category> getAllCategories() throws InterruptedException, IOException {
		return wallHavenService.fetchAllWallHavenCategories();
	}
	@GetMapping("tag/{tagId}")
	List<PictureItem> getAllTagPhotos(@PathVariable("tagId") int tagId) throws InterruptedException, IOException {
		return wallHavenService.fetchAllPicsOfTag(tagId);
	}
}
