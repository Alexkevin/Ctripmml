package ctrip.pmml.model;

import org.dmg.pmml.FieldName;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.FieldValue;
import java.util.ArrayList;
import java.util.Map;


/**
 * Created by shuangyangwang on 2016/8/29.
 * @author feyman
 * @version 1.0
 * @since 1.0
 */
public class GettingScore extends PreprocessData {

    private String[] data;
    private Evaluator evaluator;

    public GettingScore(String[] data, Evaluator evaluator) {
        super(data, evaluator);
    }

    public ArrayList<Double> gettingProbability(Evaluator evaluator){
        /**
            Predict all target label probabilities
            @param evaluator pmml model
            @return probability score of each label
         */
        Map<FieldName, FieldValue> testData = super.testData();

        ArrayList<Double> score = new ArrayList();

        Map<FieldName,?> finalResults = evaluator.evaluate(testData);

        for(FieldName t : finalResults.keySet()){

            if (finalResults.get(t) instanceof Double) {
                score.add((Double) finalResults.get(t));
            }
        }
        return score;
    }

    public Double gettingProbability(Evaluator evaluator,int targetLabelIndex){
        /**
         Predict target label probability
         @param evaluator pmml model
         @param targetLabelIndex the index of target label that you want to predict
         @return probability score of each label
         */
        ArrayList<Double> scoreArray = this.gettingProbability(evaluator);
        Double targetScore = scoreArray.get(targetLabelIndex);
        return targetScore;

    }

}
