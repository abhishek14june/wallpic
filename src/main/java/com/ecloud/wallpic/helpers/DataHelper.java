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
import java.util.ArrayList;
import java.util.List;

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

    public void processCategories(){
        System.out.println("Processing categories");
        List<Category> categories = fileHelper.readCategoriesDataFromFile(dataPath);

        for(Category category : categories){
            List<Tag> tags = category.getTag();
            if(category.getId() == 1 || category.getId() == 2 ||
                    category.getId() == 64 || category.getId() == 65){
                System.out.println("Skipping category");
                continue;
            }
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


}
