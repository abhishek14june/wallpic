package com.ecloud.wallpic.helpers;

import com.ecloud.wallpic.datamodels.Category;
import com.ecloud.wallpic.datamodels.Tag;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class FileHelper {

    public  List<Category> readCategoriesDataFromFile(String completeFilePath){
        System.out.println("Reading categories data from file "+completeFilePath);
        TypeReference<List<Category>> typeReference = new TypeReference<List<Category>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream(completeFilePath);
        List<Category> tempList = null;
        try{
            tempList = new ObjectMapper().readValue(inputStream,typeReference);
        }
        catch (Exception e){
            System.out.println("Exception in readDataFromFile \n "+e.getMessage());
        }
        return tempList;
    }
    public  List<Tag> readTagsDataFromFile(String completeFilePath){
        System.out.println("Reading categories data from file "+completeFilePath);
        TypeReference<List<Tag>> typeReference = new TypeReference<List<Tag>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream(completeFilePath);
        List<Tag> tempList = null;
        try{
            tempList = new ObjectMapper().readValue(inputStream,typeReference);
        }
        catch (Exception e){
            System.out.println("Exception in readDataFromFile \n "+e.getMessage());
        }
        return tempList;
    }
    public void writeToFile(List<Tag> tags, String categoryId){
        System.out.println("writeToFile for category : "+categoryId);
        String filePath = "C:\\abhishek\\";
        String fileName = categoryId+"_images.json";
        File file = new File(filePath+fileName);
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file);
            ObjectMapper objectMapper = new ObjectMapper();
            String data = objectMapper.writeValueAsString(tags);
            fileWriter.write(data);
            System.out.println("writeToFile for category written successfully");
        }
        catch (Exception e){
            System.out.println("Exception in writeToFile \n"+e.getMessage());
        }
        finally {
            try{
                if(null != fileWriter){
                    System.out.println("Closing fileWriter");
                    fileWriter.flush();
                    fileWriter.close();
                }
            }
            catch (Exception e){
                System.out.println("Error in closing streams");
            }
        }
    }
}
