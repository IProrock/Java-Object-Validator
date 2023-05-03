package hexlet.code.schemas;

import java.util.Map;
import java.util.Set;

public final class MapSchema extends BaseSchema {

    private Map<String, BaseSchema> mapSchema;

    public MapSchema() {
        addCheck("required", value -> true);
    }

    public MapSchema sizeof(int mapSize) {
        addCheck("sizeof", value ->
                ((Map) value).size() == mapSize);

        return this;
    }

    public MapSchema required() {
        addCheck("required", value ->
                (value instanceof Map));

        return this;
    }


    public MapSchema shape(Map<String, BaseSchema> definedMapSchema) {

        Set<String> keySet = definedMapSchema.keySet();

        addCheck("shape", value -> {
            for (String key : keySet) {
                if (!(definedMapSchema.get(key).isValid(((Map<String, Object>) value).get(key)))) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
