package hexlet.code;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ValidatorTest {
    Validator validator;
    StringSchema stringSchema;
    NumberSchema numberSchema;

    @BeforeEach
    public void prepare() {
        validator = new Validator();
        stringSchema = validator.string();
        numberSchema = validator.number();
    }

    @Test
    public void validatorTestWithoutConditions() {
        assertThat(stringSchema.isValid("")).isEqualTo(true);
        assertThat(stringSchema.isValid(null)).isEqualTo(true);
    }

    @Test
    public void validatorRequiredTest() {

        stringSchema.required();

        assertThat(stringSchema.isValid("what does the fox say")).isEqualTo(true);
        assertThat(stringSchema.isValid("hexlet")).isEqualTo(true);
        assertThat(stringSchema.isValid(null)).isEqualTo(false);
        assertThat(stringSchema.isValid(5)).isEqualTo(false);
        assertThat(stringSchema.isValid("")).isEqualTo(false);
    }

    @Test
    public void validatorContainsTest() {

        assertThat(stringSchema.contains("wh").isValid("what does the fox say")).isEqualTo(true);
        assertThat(stringSchema.contains("what").isValid("what does the fox say")).isEqualTo(true);
        assertThat(stringSchema.contains("whatthe").isValid("what does the fox say")).isEqualTo(false);
        assertThat(stringSchema.isValid("what does the fox say")).isEqualTo(false);

        assertThat(stringSchema.isValid("")).isEqualTo(false);
    }

    @Test
    public void validatorMinLengthTest() {
        assertThat(stringSchema.minLength(6).isValid("abcefg")).isEqualTo(true);
        assertThat(stringSchema.minLength(6).isValid("asdanjknkjn")).isEqualTo(true);
        assertThat(stringSchema.minLength(6).isValid("asd")).isEqualTo(false);
        assertThat(stringSchema.minLength(6).isValid("")).isEqualTo(false);
        assertThat(stringSchema.contains("ABC").minLength(6).isValid("deABCf")).isEqualTo(true);
        assertThat(stringSchema.minLength(6).isValid("abcdefgh")).isEqualTo(false);

    }

    @Test
    public void validatorNumTestWithioutConditions() {
        assertThat(numberSchema.isValid(null)).isEqualTo(true);
        assertThat(numberSchema.positive().isValid(null)).isEqualTo(true);
    }

    @Test
    public void validatorNumTestRequiredPositive() {

        numberSchema.positive();
        numberSchema.required();

        assertThat(numberSchema.isValid(null)).isEqualTo(false);
        assertThat(numberSchema.isValid(10)).isEqualTo(true);
        assertThat(numberSchema.isValid("5")).isEqualTo(false);
        assertThat(numberSchema.isValid(-10)).isEqualTo(false);
        assertThat(numberSchema.isValid(0)).isEqualTo(false);

    }

    @Test
    public void validatorNumTestRquiredPositiveRange() {
        numberSchema.positive();
        numberSchema.required();
        numberSchema.range(5, 10);

        assertThat(numberSchema.isValid(5)).isEqualTo(true);
        assertThat(numberSchema.isValid(10)).isEqualTo(true);
        assertThat(numberSchema.isValid(4)).isEqualTo(false);
        assertThat(numberSchema.isValid(11)).isEqualTo(false);
    }

}
