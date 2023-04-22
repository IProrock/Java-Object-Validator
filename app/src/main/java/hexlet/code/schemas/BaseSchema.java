package hexlet.code.schemas;

public class BaseSchema {

    protected boolean isNullValid = true;
    protected StringSchema strSchema;
    protected NumberSchema numSchema;
    protected MapSchema mapSchema;


    public BaseSchema required() {
        this.isNullValid = false;

        return this;
    }

    public boolean isValid(Object obj) {

        if (obj == null && !isNullValid) {
            return false;
        }

        if (strSchema != null) {
            return strSchema.isValid(obj);
        }

        if (numSchema != null) {
            return numSchema.isValid(obj);
        }

        if (mapSchema != null) {
            return mapSchema.isValid(obj);
        }

        return true;
    }

}
