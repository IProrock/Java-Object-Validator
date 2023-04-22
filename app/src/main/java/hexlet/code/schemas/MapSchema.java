package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    private int sizeOf;
    private Map<String, BaseSchema> mapSchema;

    public MapSchema sizeof(int mapSize) {
        this.sizeOf = mapSize;

        return this;
    }

    @Override
    public boolean isValid(Object mapExpected) {

        if (mapExpected == null && !isNullValid) {
            return false;
        }

        if (!(mapExpected instanceof Map<?, ?>)) {
            return isNullValid ? true : false;
        }

        if ((sizeOf > 0) && (((Map<?, ?>) mapExpected).size() != sizeOf)) {
            return false;
        }

        if (mapSchema != null) {
            return mapDeepCheck((Map<String, Object>) mapExpected);
        }

        return true;
    }

    public MapSchema shape(Map<String, BaseSchema> definedMapSchema) {
        this.mapSchema = definedMapSchema;
        super.mapSchema = this;

        return this;
    }

    private boolean mapDeepCheck(Map<String, Object> mapToValidate) {
        for (String key : mapSchema.keySet()) {
            if (!(mapSchema.get(key).isValid(mapToValidate.get(key)))) {
                return false;
            }
        }
        return true;
    }
}
