package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    private Boolean isEmptyValid = true;


    public StringSchema() {
        addCheck("required",
                value ->
                        (value instanceof String)
                                || (value == null));
    }

    public StringSchema required() {
        addCheck("required",
                value ->
                        (value instanceof String)
                                && (!value.equals("")));
        return this;
    }


    public StringSchema contains(String symbols) {

        addCheck("contains",
                value ->
                        (((String) value).contains(symbols)));

        return this;
    }

    public StringSchema minLength(int minLen) {
        addCheck("minLength",
                value ->
                        ((String) value).length() >= minLen);
        return this;
    }
}
