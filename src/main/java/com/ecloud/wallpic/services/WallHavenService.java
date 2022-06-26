package com.ecloud.wallpic.services;

import com.ecloud.wallpic.configuration.WallhavenConstants;
import com.ecloud.wallpic.configuration.WallpicConstants;
import com.ecloud.wallpic.datamodels.Category;
import com.ecloud.wallpic.datamodels.Tag;
import com.ecloud.wallpic.helpers.HttpHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Component
public class WallHavenService {
    @Autowired
    HttpHelper httpHelper;
    private String dataPath = "/data/existingdata.json";
    public List<Category> fetchAllWallHavenCategories() throws InterruptedException, IOException {
        TypeReference<List<Category>> typeReference = new TypeReference<List<Category>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream(dataPath);
        List<Category> tempList = new ObjectMapper().readValue(inputStream,typeReference);
        List<Category> respone = new ArrayList<>();
        Map<Integer,Category> responseMap = new HashMap<>();
        for(Category tempItem : tempList){
            responseMap.put(tempItem.getId(),tempItem);
        }
//        for(int tagNum=1001;tagNum<=1500;tagNum++){
//            Thread.sleep(2000);
//            String reqUrl = WallhavenConstants.TAG_BASE_URL +tagNum;
//            System.out.println(reqUrl);
//            JSONObject jsonResponse = httpHelper.callForJsonObjectResponse(reqUrl);
//            Category item = processCategories(jsonResponse);
//            if(null != item){
//                Category existing = responseMap.get(item.getId());
//                if(null != existing){
//                    List<Tag> existingTags = existing.getTag();
//                    existingTags.addAll(item.getTag());
//                    existing.setTag(existingTags);
//                }
//                else{
//                    responseMap.put(item.getId(), item);
//                }
//            }
//
//        }
        Collection<Category> values = responseMap.values();
        respone = new ArrayList<>(values);
        System.out.println("response size "+respone.size());
        return respone;
    }

    private Category processCategories(JSONObject jsonResponse) {
        Category category = null;
        //JSONObject response = jsonResponse.getJSONObject("body");
        if(null!=jsonResponse && jsonResponse.has(WallhavenConstants.DATA)){
            JSONObject data = jsonResponse.getJSONObject(WallhavenConstants.DATA);
            if(data.has(WallhavenConstants.PURITY)
                    && WallhavenConstants.PURITY_SFW.equalsIgnoreCase(data.getString(WallhavenConstants.PURITY))){
                if(tagIsUsefull(data.getInt(WallhavenConstants.ID))){
                    category = new Category();
                    category.setId(data.getInt(WallhavenConstants.CATEGORY_ID));
                    category.setName(data.getString(WallhavenConstants.CATEGORY));
                    category.setPurity(data.getString(WallhavenConstants.PURITY));
                    Tag tag = new Tag();
                    tag.setId(data.getInt(WallhavenConstants.ID));
                    tag.setName(data.getString(WallhavenConstants.TAG_NAME));
                    List<Tag> tempTagList = new ArrayList<>();
                    tempTagList.add(tag);
                    category.setTag(tempTagList);
                }

            }
        }
        return category;
    }

    private boolean tagIsUsefull(int tagId) {
        boolean response = false;
        String reqUrl = WallhavenConstants.TAG_SEARCH_BASE+tagId+WallhavenConstants.PURITY_PARAM;
        System.out.println(reqUrl);
        JSONObject jsonResponse = httpHelper.callForJsonObjectResponse(reqUrl);
        if(null != jsonResponse && jsonResponse.has(WallhavenConstants.META)){
            JSONObject meta = jsonResponse.getJSONObject(WallhavenConstants.META);
            int total = meta.getInt(WallhavenConstants.TOTAL);
            if(total>=WallhavenConstants.THRESHOLD_TOTAL)
                response = true;
        }
        return response;
    }
}
