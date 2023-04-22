package hexlet.code;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class ValidatorTest {
    Validator validator;
    StringSchema stringSchema;
    NumberSchema numberSchema;
    MapSchema mapSchema;

    @BeforeEach
    public void prepare() {
        validator = new Validator();
        stringSchema = validator.string();
        numberSchema = validator.number();
        mapSchema = validator.map();
    }

    @Test
    public void validatorStrWoConditionsTest() {
        assertThat(stringSchema.isValid("")).isEqualTo(true);
        assertThat(stringSchema.isValid(null)).isEqualTo(true);
    }

    @Test
    public void validatorStrRequiredTest() {

        stringSchema.required();

        assertThat(stringSchema.isValid("what does the fox say")).isEqualTo(true);
        assertThat(stringSchema.isValid("hexlet")).isEqualTo(true);
        assertThat(stringSchema.isValid(null)).isEqualTo(false);
        assertThat(stringSchema.isValid(5)).isEqualTo(false);
        assertThat(stringSchema.isValid("")).isEqualTo(false);
    }

    @Test
    public void validatorStrContainsTest() {

        assertThat(stringSchema.contains("wh").isValid("what does the fox say")).isEqualTo(true);
        assertThat(stringSchema.contains("what").isValid("what does the fox say")).isEqualTo(true);
        assertThat(stringSchema.contains("whatthe").isValid("what does the fox say")).isEqualTo(false);
        assertThat(stringSchema.isValid("what does the fox say")).isEqualTo(false);

        assertThat(stringSchema.isValid("")).isEqualTo(false);
    }

    @Test
    public void validatorStrMinLengthTest() {
        assertThat(stringSchema.minLength(6).isValid("abcefg")).isEqualTo(true);
        assertThat(stringSchema.minLength(6).isValid("asdanjknkjn")).isEqualTo(true);
        assertThat(stringSchema.minLength(6).isValid("asd")).isEqualTo(false);
        assertThat(stringSchema.minLength(6).isValid("")).isEqualTo(false);
        assertThat(stringSchema.contains("ABC").minLength(6).isValid("deABCf")).isEqualTo(true);
        assertThat(stringSchema.minLength(6).isValid("abcdefgh")).isEqualTo(false);

    }

    @Test
    public void validatorNumWoConditionsTest() {
        assertThat(numberSchema.isValid(null)).isEqualTo(true);
        assertThat(numberSchema.positive().isValid(null)).isEqualTo(true);
    }

    @Test
    public void validatorNumRequiredPositiveTest() {

        numberSchema.positive();
        numberSchema.required();

        assertThat(numberSchema.isValid(null)).isEqualTo(false);
        assertThat(numberSchema.isValid(10)).isEqualTo(true);
        assertThat(numberSchema.isValid("5")).isEqualTo(false);
        assertThat(numberSchema.isValid(-10)).isEqualTo(false);
        assertThat(numberSchema.isValid(0)).isEqualTo(false);

    }

    @Test
    public void validatorNumRequiredPositiveRangeTest() {
        numberSchema.positive();
        numberSchema.required();
        numberSchema.range(5, 10);

        assertThat(numberSchema.isValid(5)).isEqualTo(true);
        assertThat(numberSchema.isValid(10)).isEqualTo(true);
        assertThat(numberSchema.isValid(4)).isEqualTo(false);
        assertThat(numberSchema.isValid(11)).isEqualTo(false);
    }

    @Test
    void validatorMapWoConditionsTest() {
        assertThat(mapSchema.isValid(null)).isEqualTo(true);
        assertThat(mapSchema.isValid("Not MAP")).isEqualTo(true);
    }

    @Test
    void validatorMapRequiredTest() {

        Map<String, String> data = new HashMap<>();
        data.put("k1", "v1");

        mapSchema.required();

        assertThat(mapSchema.isValid(null)).isEqualTo(false);
        assertThat(mapSchema.isValid("Not MAP")).isEqualTo(false);
        assertThat(mapSchema.isValid(new HashMap())).isEqualTo(true);
        assertThat(mapSchema.isValid(data)).isEqualTo(true);
    }

    @Test
    void validatorMapRequiredSizeTest() {

        Map<String, String> data = new HashMap<>();
        data.put("k1", "v1");

        mapSchema.required();
        mapSchema.sizeof(2);

        assertThat(mapSchema.isValid(data)).isEqualTo(false);

        data.put("k2", "v2");

        assertThat(mapSchema.isValid(data)).isEqualTo(true);

        data.put("k3", "v3");

        assertThat(mapSchema.isValid(data)).isEqualTo(false);
    }

    @Test
    void validatorDeepMapTest() {



        Map<String, BaseSchema> schemas = new HashMap<>();

        schemas.put("name", validator.string().required());

        schemas.put("age", validator.number().positive());
        mapSchema.shape(schemas);


        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertThat(mapSchema.isValid(human1)).isEqualTo(true); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(mapSchema.isValid(human2)).isEqualTo(true); // true

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(mapSchema.isValid(human3)).isEqualTo(false); // false

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertThat(mapSchema.isValid(human4)).isEqualTo(false); // false
    }

}
