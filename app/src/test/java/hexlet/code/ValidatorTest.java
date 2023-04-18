package hexlet.code;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ValidatorTest {
    Validator validator;
    StringSchema schema;

    @BeforeEach
    public void prepare() {
        validator = new Validator();
        schema = validator.string();
    }

    @Test
    public void validatorTestWithoutConditions() {
        assertThat(schema.isValid("")).isEqualTo(true);
        assertThat(schema.isValid(null)).isEqualTo(true);
    }

    @Test
    public void validatorRequiredTest() {

        schema.required();

        assertThat(schema.isValid("what does the fox say")).isEqualTo(true);
        assertThat(schema.isValid("hexlet")).isEqualTo(true);
        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid(5)).isEqualTo(false);
        assertThat(schema.isValid("")).isEqualTo(false);
    }

    @Test
    public void validatorContainsTest() {

        assertThat(schema.contains("wh").isValid("what does the fox say")).isEqualTo(true);
        assertThat(schema.contains("what").isValid("what does the fox say")).isEqualTo(true);
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isEqualTo(false);
        assertThat(schema.isValid("what does the fox say")).isEqualTo(false);

        assertThat(schema.isValid("")).isEqualTo(false);
    }

    @Test
    public void validatorMinLengthTest() {
        assertThat(schema.minLength(6).isValid("abcefg")).isEqualTo(true);
        assertThat(schema.minLength(6).isValid("asdanjknkjn")).isEqualTo(true);
        assertThat(schema.minLength(6).isValid("asd")).isEqualTo(false);
        assertThat(schema.minLength(6).isValid("")).isEqualTo(false);
        assertThat(schema.contains("ABC").minLength(6).isValid("deABCf")).isEqualTo(true);
        assertThat(schema.minLength(6).isValid("abcdefgh")).isEqualTo(false);

    }

}
