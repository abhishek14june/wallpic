package com.ecloud.wallpic.helpers;

import com.ecloud.wallpic.configuration.WallhavenConstants;
import com.ecloud.wallpic.datamodels.Category;
import com.ecloud.wallpic.datamodels.PictureItem;
import com.ecloud.wallpic.datamodels.Tag;
import com.ecloud.wallpic.services.WallHavenService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
public class DataHelper {

    @Autowired
    FileHelper fileHelper;
    @Autowired
    UrlBuilders urlBuilders;

    @Autowired
    HttpHelper httpHelper;

    @Autowired
    WallHavenService wallHavenService;

    private String dataPath = "/data/existingdata.json";
    private String resourceBase = "/data/";

    public void processCategories(){
        System.out.println("Processing categories");
        List<Category> categories = fileHelper.readCategoriesDataFromFile(dataPath);

        for(Category category : categories){
            List<Tag> tags = category.getTag();
            if(category.getId() == 1 || category.getId() == 2 ||
                    category.getId() == 64 || category.getId() == 65
                        || category.getId() == 3 || category.getId() == 4){
                System.out.println("Skipping category");
                continue;
            }
            System.out.println("Processing Caegory "+category.getName());
            System.out.println("totalTags to be updated "+tags.size());
            int counter = 0;
            List<Tag> updatedTags = new ArrayList<>();
            for(Tag tag : tags){
                counter++;

               List<PictureItem> pictureItems = wallHavenService.fetchAllPicsOfTag(tag.getId());
               tag.setPhotos(pictureItems);
               updatedTags.add(tag);
               System.out.println("Pending tags "+(tags.size()-counter));
               fileHelper.writeToFile(updatedTags,category.getId()+"");
               System.out.println("File updated");
            }
        }
    }
    public Map<Integer, Map<Integer,List<PictureItem>>> prepCategoryTagMap(){
        Map<Integer, Map<Integer,List<PictureItem>>> response = new HashMap<>();

        List<Category> categories = fileHelper.readCategoriesDataFromFile(dataPath);
        for(Category category : categories){
            //category 1 : tag 7,8,9
            //category 2 : tag 3,5,6
            Map<Integer,List<PictureItem>> pictureItemMap = new HashMap<>();
            String filePath = resourceBase+category.getId()+"_images.json";
            List<Tag> tags =fileHelper.readTagsDataFromFile(filePath);
            if(null != tags){
                for(Tag tag : tags){
                    pictureItemMap.put(tag.getId(),tag.getPhotos());
                }
            }
            if(pictureItemMap.size()>0){
                response.put(category.getId(),pictureItemMap);
            }
        }

        return response;
    }

    public List<PictureItem> searchByCategoryId(int categoryId){
        List<PictureItem> response = new ArrayList<>();
        Map<Integer, Map<Integer,List<PictureItem>>> categoryMap= prepCategoryTagMap();
        Map<Integer,List<PictureItem>> images = categoryMap.get(categoryId);
        Set<Integer> keyset = images.keySet();
        for(Integer index : keyset){
            response.addAll(images.get(index));
        }
        return response;
    }

}
