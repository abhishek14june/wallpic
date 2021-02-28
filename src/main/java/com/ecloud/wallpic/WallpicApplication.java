package com.ecloud.wallpic;

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

@SpringBootApplication
@EnableCaching
public class WallpicApplication {

	@Autowired
	CollectionService collService;
	
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
//		System.out.println(new JSONArray(response));
//			
//			List<PicCollection> col=collService.fetchAllCollectionsOfAUser("abhi14june");
//			for(PicCollection item : col) {
//				System.out.println("Fetching ----"+"id : "+item.getId()+" title : "+item.getTitle()+" total photos: "+item.getTotalPhotos());
//				
//			}
		};
	}

}
