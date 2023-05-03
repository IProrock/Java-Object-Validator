package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {

    protected boolean isNullValid = true;
    protected StringSchema strSchema;
    protected NumberSchema numSchema;
    protected MapSchema mapSchema;
    protected Map<String, Predicate> checks = new LinkedHashMap<>();

    public final void addCheck(String name, Predicate checkLogic) {
        checks.put(name, checkLogic);
    }

//    /**
//     * required() method should be overridden in case not only "not-null" check is necessary for object type.
//     * @return same object with isNullValid parameter set to false.
//     */
//    public BaseSchema required() {
//        this.isNullValid = false;
//
//        return this;
//    }

//    /**
//     * isValid() should be overridden according to object type validation requirements.
//     * @param obj
//     * @return boolean value with result of object validation
//     */
    public final boolean isValid(Object obj) {

//        if (obj == null && !isNullValid) {
//            return false;
//        }
//
//        if (strSchema != null) {
//            return strSchema.isValid(obj);
//        }
//
//        if (numSchema != null) {
//            return numSchema.isValid(obj);
//        }
//
//        if (mapSchema != null) {
//            return mapSchema.isValid(obj);
//        }
//
//        return true;
        if (obj == null) {
            return checks.get("required").test(obj);
        }

        for (Map.Entry<String, Predicate> entry : checks.entrySet()) {

            if (!(entry.getValue().test(obj))) {
                return false;
            }
        }

        return true;
    }


}
