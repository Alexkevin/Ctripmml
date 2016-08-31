package ctrip.pmml.model;

import com.opencsv.CSVReader;
import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.*;
import org.jpmml.model.PMMLUtil;
import org.xml.sax.SAXException;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.*;

/**
 * Created by shuangyangwang on 2016/8/29.
 * The test demo for iris data with LogisticRegression
 */
public class PMMLDemoTest {
    public static void main(String[] args) throws IOException, JAXBException, SAXException {
        //Loading data
        CSVReader reader = new CSVReader(new FileReader("d:\\Users\\shuangyangwang\\Desktop\\offline\\Iris.csv"));
        List<String[]> data = reader.readAll();
        data.remove(0);
        reader.close();
        System.out.println(data.size());


        //Loading model
        InputStream is = new FileInputStream("d:\\Users\\shuangyangwang\\Desktop\\Test\\LogisticRegressionIris.pmml");
        PMML model = PMMLUtil.unmarshal(is);
        ModelEvaluatorFactory mef = ModelEvaluatorFactory.newInstance();
        ModelEvaluator<?> modelEvaluator = mef.newModelEvaluator(model);
        modelEvaluator.verify();


        //Predicting probability
        List<ArrayList<Double>> listArray = new ArrayList<>();
        for (String[] s : data) {
            PreprocessData ppd = new PreprocessData(s, modelEvaluator);
            Map<FieldName, FieldValue> testData = ppd.testData();
            GettingScore scoreE = new GettingScore(s, modelEvaluator);
            ArrayList<Double> result = scoreE.gettingProbability(modelEvaluator);
            listArray.add(result);

        }
        for (ArrayList<Double> a: listArray){
            System.out.println(a.get(1));
        }

    }


}
