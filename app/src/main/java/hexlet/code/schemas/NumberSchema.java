package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        addCheck("required", value -> true);
    }


    public NumberSchema positive() {

        addCheck("positive", value ->
                ((Integer) value > 0));

        return this;
    }

    public NumberSchema required() {
        addCheck("required", value ->
                (value instanceof Integer));

        return this;
    }

    public NumberSchema range(int lowLimit, int upperLimit) {

        addCheck("range", value ->
                ((Integer) value >= lowLimit)
            && (Integer) value <= upperLimit);

        return this;
    }
}
