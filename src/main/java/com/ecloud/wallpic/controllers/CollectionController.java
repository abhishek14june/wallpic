package com.ecloud.wallpic.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecloud.wallpic.datamodels.PicCollection;
import com.ecloud.wallpic.datamodels.PictureItem;
import com.ecloud.wallpic.services.CollectionService;

@RestController
public class CollectionController {

	@Autowired
	CollectionService collectionService;
	
	@Cacheable(value="pic_collection", key = "'mykey'")
	@GetMapping("collections/all")
	List<PicCollection> getPeoplePhots(){
	    List<PicCollection>	userCollections = collectionService.fetchAllCollectionsOfAUser("abhi14june");
	    return userCollections;
	}
	
	@GetMapping("collections/{collectionId}/photos")
	List<PictureItem> getCollectionPhotos(@PathVariable("collectionId") String collectionId){
		List<PictureItem> response = new ArrayList<PictureItem>();
	    List<PicCollection>	userCollections = collectionService.fetchAllCollectionsOfAUser("abhi14june");
	    for(PicCollection collection : userCollections) {
	    	if(collection.getId().equalsIgnoreCase(collectionId)) {
	    		response.addAll(collectionService.fetchAllImagesOfaCollection(collection));
	    	}
	    }
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
	
}
