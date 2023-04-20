package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    int sizeOf;

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

        return true;
    }
}
