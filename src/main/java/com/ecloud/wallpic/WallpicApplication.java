package com.ecloud.wallpic;

import com.ecloud.wallpic.datamodels.PictureItem;
import com.ecloud.wallpic.datamodels.Tag;
import com.ecloud.wallpic.helpers.DataHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.ecloud.wallpic.services.CollectionService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableCaching
public class WallpicApplication {

	@Autowired
	CollectionService collService;

	@Autowired
	DataHelper dataHelper;
	
	public static void main(String[] args) {
		SpringApplication.run(WallpicApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	 @Bean
	    public CacheManager cacheManager() {
	        return new ConcurrentMapCacheManager("pic_collection");
	    }

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			//dataHelper.processCategories();
//			long l = System.currentTimeMillis();
//
//			List<PictureItem> images = dataHelper.searchByCategoryId(1);
//			System.out.println("Total time elapsed "+(System.currentTimeMillis()-l)/1000);
//			System.out.println("total images for category "+images.size());
//		System.out.println(new JSONArray(response));
//			
//			List<PicCollection> col=collService.fetchAllCollectionsOfAUser("abhi14june");
//			for(PicCollection item : col) {
//				System.out.println("Fetching ----"+"id : "+item.getId()+" title : "+item.getTitle()+" total photos: "+item.getTotalPhotos());
//				
//			}
		};
	}
	public void writeToFile(List<Tag> tags,String categoryId){
		String filePath = "C:\\abhishek\\";
		String fileName = categoryId+"_images.json";
		File file = new File(filePath+fileName);
		FileWriter fileWriter = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		OutputStream outputStream = null;
		try{
			fileWriter = new FileWriter(file);
			ObjectMapper objectMapper = new ObjectMapper();
			String data = objectMapper.writeValueAsString(tags);


			fileWriter.write(data);
//			oos = new ObjectOutputStream(bos);
//			outputStream = new FileOutputStream(file);
//
//			oos.writeObject(tags);
//			byte[] bytes = bos.toByteArray();
//
//			outputStream.write(bytes);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				fileWriter.flush();
				fileWriter.close();

//				bos.close();
//				oos.close();
//				outputStream.close();
			}
			catch (Exception e){
				System.out.println("Error in closing streams");
			}


		}
	}

}
