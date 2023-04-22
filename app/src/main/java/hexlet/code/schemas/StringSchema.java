package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    private String contains = "";
    private int minLength = -1;
//    private Boolean isNullValid = true;
    private Boolean isEmptyValid = true;


    @Override
    public StringSchema required() {
        this.isEmptyValid = false;
        this.isNullValid = false;
        super.strSchema = this;

        return this;
    }

    public StringSchema contains(String symbols) {

        this.contains = symbols;
        this.isNullValid = false;
        super.strSchema = this;

        return this;
    }

    @Override
    public boolean isValid(Object expectedString) {

        if (expectedString == null) {
//            System.out.println("Nullcheck");
            return isNullValid ? true : false;
        }

        if ((!(expectedString instanceof String))) {
//            System.out.println("Instance");
            return false;
        }

        if (!(containsCheck((String) expectedString, this.contains))) {
//            System.out.println("Contains");
            return false;
        }

        if (expectedString.equals("") && !isEmptyValid) {
//            System.out.println("Empty");
            return false;
        }

        if (!(minLengthCheck((String) expectedString, this.minLength))) {
            return false;
        }

        return true;
    }

    private static boolean containsCheck(String evaluatedStr, String substring) {
        int subSize = substring.length();
        int initialSize = evaluatedStr.length();

        if (subSize == 0) {
            return true;
        }

        if (initialSize < subSize) {
            return false;
        }

        for (int i = 0; i <= initialSize - subSize; i++) {

            if (evaluatedStr.substring(i, i + subSize).equals(substring)) {
                return true;
            }
        }

        return false;

    }

    public StringSchema minLength(int minLen) {
        this.isNullValid = false;
        this.minLength = minLen;
        super.strSchema = this;

        return this;
    }

    private static boolean minLengthCheck(String evaluatedStr, int minLength) {
        if (evaluatedStr.length() < minLength) {
            return false;
        }
        return true;
    }

}
