package ctrip.pmml.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by shuangyangwang on 2016/8/29.
 */
public class LoadingData {
    public static String[][] getData(String csvFilePath,String csvSplitBy,String line){
        String [][] result = null;
        try(BufferedReader br = new BufferedReader(new FileReader(csvFilePath))){
            int i = 0;
            while ((line = br.readLine()) != null){
                result[i] = line.split(csvSplitBy);
                i++;
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
}
