package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public final class Validator {

    public StringSchema string() {
        StringSchema strSchema = new StringSchema();

        return strSchema;
    }

    public NumberSchema number() {
        NumberSchema numberSchema = new NumberSchema();

        return numberSchema;
    }

    public MapSchema map() {
        MapSchema mapSchema = new MapSchema();

        return  mapSchema;
    }
}
