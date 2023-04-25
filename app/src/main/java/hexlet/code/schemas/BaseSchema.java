package hexlet.code.schemas;

public class BaseSchema {

    protected boolean isNullValid = true;
    protected StringSchema strSchema;
    protected NumberSchema numSchema;
    protected MapSchema mapSchema;

    /**
     * required() method should be overridden in case not only "not-null" check is necessary for object type.
     * @return same object with isNullValid parameter set to false.
     */
    public BaseSchema required() {
        this.isNullValid = false;

        return this;
    }

    /**
     * isValid() should be overridden according to object type validation requirements.
     * @param obj
     * @return boolean value with result of object validation
     */
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
