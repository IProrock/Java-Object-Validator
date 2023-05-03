package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {

    protected Map<String, Predicate> checks = new LinkedHashMap<>();

    public final void addCheck(String name, Predicate checkLogic) {
        checks.put(name, checkLogic);
    }

    public final boolean isValid(Object obj) {

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
