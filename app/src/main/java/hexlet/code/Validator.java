package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {
    StringSchema strSchema;
    NumberSchema numberSchema;
    MapSchema mapSchema;

    public StringSchema string() {
        this.strSchema = new StringSchema();

        return strSchema;
    }

    public NumberSchema number() {
        this.numberSchema = new NumberSchema();

        return numberSchema;
    }

    public MapSchema map() {
        this.mapSchema = new MapSchema();

        return  mapSchema;
    }
}
