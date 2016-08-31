package ctrip.pmml.model;

import org.dmg.pmml.DataType;
import org.dmg.pmml.FieldName;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.FieldValue;
import org.jpmml.evaluator.FieldValueUtil;
import org.jpmml.evaluator.InputField;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shuangyangwang on 2016/8/29.
 * @author feyman
 * @version 1.0
 * @since 1.0
 */

public class PreprocessData {
    private String[] data;
    private Evaluator evaluator;

    public PreprocessData() {
    }

    public PreprocessData(String[] data, Evaluator evaluator) {
        this.data = data;
        this.evaluator = evaluator;
    }

    public Map<FieldName, FieldValue> testData() {
        /**
         * Prepare test data
         * @return input data for prediction
         */
        Map<FieldName, FieldValue> arguments = new LinkedHashMap<>();
        List<InputField> inputs = this.evaluator.getActiveFields();
        for (InputField input : inputs) {
            FieldName activeName = input.getName();
            int i = inputs.indexOf(input);
            System.out.println(i);
            FieldValue activeValue = null;
            try {
                if (input.getDataType().equals(DataType.DOUBLE)) {
                    activeValue = FieldValueUtil.create(Double.parseDouble(this.data[i]));
                }else{
                    activeValue = FieldValueUtil.create(this.data[i]);
                }


            }catch (Exception e){
                activeValue = FieldValueUtil.create(0.0);
            }
            arguments.put(activeName, activeValue);

        }

        return arguments;
    }
}
