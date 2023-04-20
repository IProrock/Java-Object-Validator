package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {
    StringSchema strSchema;
    NumberSchema numberSchema;

    public StringSchema string() {
        this.strSchema = new StringSchema();

        return strSchema;
    }

    public NumberSchema number() {
        this.numberSchema = new NumberSchema();

        return numberSchema;
    }
}
