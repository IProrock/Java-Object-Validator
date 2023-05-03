package hexlet.code.schemas;

import java.util.Map;
import java.util.Set;

public final class MapSchema extends BaseSchema {

    private int sizeOf;
    private Map<String, BaseSchema> mapSchema;

    public MapSchema() {
        addCheck("required", value ->
                (value == null)
                        || (value instanceof Object));
    }

    public MapSchema sizeof(int mapSize) {
        addCheck("sizeof", value ->
                ((Map) value).size() == mapSize);
//        this.sizeOf = mapSize;

        return this;
    }

    public MapSchema required() {
        addCheck("required", value ->
                (value instanceof Map));

        return this;
    }

//    @Override
//    public boolean isValid(Object mapExpected) {
//
//        if (mapExpected == null && !isNullValid) {
//            return false;
//        }
//
//        if (!(mapExpected instanceof Map<?, ?>)) {
//            return isNullValid ? true : false;
//        }
//
//        if ((sizeOf > 0) && (((Map<?, ?>) mapExpected).size() != sizeOf)) {
//            return false;
//        }
//
//        if (mapSchema != null) {
//            return mapDeepCheck((Map<String, Object>) mapExpected);
//        }
//
//        return true;
//    }

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
//        this.mapSchema = definedMapSchema;
//        super.mapSchema = this;

        return this;
    }

//    private boolean mapDeepCheck(Map<String, Object> mapToValidate) {
//        for (String key : mapSchema.keySet()) {
//            if (!(mapSchema.get(key).isValid(mapToValidate.get(key)))) {
//                return false;
//            }
//        }
//        return true;
//    }
}
