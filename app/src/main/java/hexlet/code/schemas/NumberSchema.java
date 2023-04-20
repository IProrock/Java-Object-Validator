package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

//    private boolean isNullValid = true;
    private boolean isNegativeValid = false;
    private int[] range = new int[2];

    @Override
    public boolean isValid(Object intExpected) {

        if (intExpected == null) {
            return isNullValid ? true : false;
        }

        if (!(intExpected instanceof Integer)) {
            return false;
        }

        if ((Integer) intExpected <= 0 && !isNullValid) {
            return false;
        }

        if ((range[0] != 0 && range[1] != 0)) {
            if ((Integer) intExpected < range[0] || (Integer) intExpected > range[1]) {
                return false;
            }
        }

        return true;
    }

    public NumberSchema positive() {

        this.isNegativeValid = false;

        return this;
    }

//    public NumberSchema required() {
//
//        this.isNullValid = false;
//
//        return this;
//    }

    public NumberSchema range(int lowLimit, int upperLimit) {

        this.range[0] = lowLimit;
        this.range[1] = upperLimit;

        return this;
    }
}
