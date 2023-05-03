package hexlet.code.schemas;

//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

//    private boolean isNegativeValid = false;
//    private int[] range = new int[2];

    public NumberSchema() {
        addCheck("required", value ->
                (value instanceof Object)
                        || (value == null));
    }


//    @Override
//    public boolean isValid(Object intExpected) {
//
//        if (intExpected == null) {
//            return isNullValid ? true : false;
//        }
//
//        if (!(intExpected instanceof Integer)) {
//            return false;
//        }
//
//        if ((Integer) intExpected <= 0 && !isNegativeValid) {
//            return false;
//        }
//
//        if ((range[0] != 0 && range[1] != 0)) {
//            if ((Integer) intExpected < range[0] || (Integer) intExpected > range[1]) {
//                return false;
//            }
//        }
//
//        return true;
//    }

    public NumberSchema positive() {

        addCheck("positive", value ->
                ((Integer) value > 0));

//        this.isNegativeValid = false;
//        super.numSchema = this;
//
        return this;
    }

//    @Override
    public NumberSchema required() {
        addCheck("required", value ->
                (value instanceof Integer));
//
//        super.required();
//        super.numSchema = this;
//
        return this;
    }

    public NumberSchema range(int lowLimit, int upperLimit) {

        addCheck("range", value ->
                ((Integer) value >= lowLimit)
            && (Integer) value <= upperLimit);

//        this.range[0] = lowLimit;
//        this.range[1] = upperLimit;
//        super.numSchema = this;
//
        return this;
    }
}
