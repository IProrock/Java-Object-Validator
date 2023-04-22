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

        if ((Integer) intExpected <= 0 && !isNegativeValid) {
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
        super.numSchema = this;

        return this;
    }

    @Override
    public NumberSchema required() {

        super.required();
        super.numSchema = this;

        return this;
    }

    public NumberSchema range(int lowLimit, int upperLimit) {

        this.range[0] = lowLimit;
        this.range[1] = upperLimit;
        super.numSchema = this;

        return this;
    }
}
