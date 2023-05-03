package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
//    private String contains = "";
    private int minLength = -1;
    private Boolean isEmptyValid = true;


    public StringSchema() {
        addCheck("required",
                value ->
                        (value instanceof String)
                                || (value == null));
//    //    this.isEmptyValid = false;
//    //    this.isNullValid = false;
//    //    super.strSchema = this;
//    //
//    //    return this;
    }

    public StringSchema required() {
        addCheck("required",
                value ->
                        (value instanceof String)
                                && (!value.equals("")));
//        this.isEmptyValid = false;
//        this.isNullValid = false;
//        super.strSchema = this;
//
        return this;
    }


    public StringSchema contains(String symbols) {

        addCheck("contains",
                value ->
                        (((String) value).contains(symbols)));

//        this.contains = symbols;
//        this.isNullValid = false;
//        super.strSchema = this;
//
        return this;
    }

//    @Override
//    public boolean isValid(Object expectedString) {
//
//        if (expectedString == null) {
//            return isNullValid ? true : false;
//        }
//
//        if ((!(expectedString instanceof String))) {
//            return false;
//        }
//
//        if (!(containsCheck((String) expectedString, this.contains))) {
//            return false;
//        }
//
//        if (expectedString.equals("") && !isEmptyValid) {
//            return false;
//        }
//
//        if (!(minLengthCheck((String) expectedString, this.minLength))) {
//            return false;
//        }
//
//        return true;
//    }

//    private static boolean containsCheck(String evaluatedStr, String substring) {
//        int subSize = substring.length();
//        int initialSize = evaluatedStr.length();
//
//        if (subSize == 0) {
//            return true;
//        }
//
//        if (initialSize < subSize) {
//            return false;
//        }
//
//        for (int i = 0; i <= initialSize - subSize; i++) {
//
//            if (evaluatedStr.substring(i, i + subSize).equals(substring)) {
//                return true;
//            }
//        }
//
//        return false;
//
//    }

    public StringSchema minLength(int minLen) {
        addCheck("minLength",
                value ->
                        ((String) value).length() >= minLen);
//        this.isNullValid = false;
//        this.minLength = minLen;
//        super.strSchema = this;
//
        return this;
    }

//    private static boolean minLengthCheck(String evaluatedStr, int minLength) {
//        if (evaluatedStr.length() < minLength) {
//            return false;
//        }
//        return true;
//    }

}
