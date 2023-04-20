package hexlet.code;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Main {

    public static void main(String[] args) {

        Validator validator = new Validator();
        StringSchema schema = validator.string();
        System.out.println(schema.isValid("") + " Expected 1: True");
        System.out.println(schema.isValid(null) + " Exp 2: True");
        System.out.println(schema.isValid(5) + " Exp 2.1: False");

        schema.required();

        System.out.println(schema.isValid("what does the fox say") + " Exp 3: True");
        System.out.println(schema.isValid("hexlet") + " Exp 4: True");
        System.out.println(schema.isValid(null) + " Exp 5: False");
        System.out.println(schema.isValid(5) + " Exp 6: False");
        System.out.println(schema.isValid("") + " Exp 7: False");

        validator = new Validator();
        schema = validator.string();

        System.out.println((schema.contains("wh").isValid("what does the fox say")) + " Exp 8: True");
        System.out.println((schema.contains("what").isValid("what does the fox say")) + " Exp 9: True");
        System.out.println((schema.contains("whatthe").isValid("what does the fox say")) + " Exp 10: False");
        System.out.println((schema.isValid("what does the fox say")) + " Exp 11: False");

        System.out.println(schema.isValid("") + " Expected 12: False");
        System.out.println((schema.contains("wh").isValid(null)) + " Exp 13: False");


        Validator v = new Validator();

        NumberSchema numSchema = v.number();

// Пока не вызван метод required(), null считается валидным
        System.out.println(numSchema.isValid(null) + " NExp1: true");
        System.out.println(numSchema.positive().isValid(null) + " NExp2: true");

        numSchema.required();

        System.out.println(numSchema.isValid(null) + " NExp3: False");
        System.out.println(numSchema.isValid(10) + " NExp4: true"); // true
        System.out.println(numSchema.isValid("5") + " Nexp5: false"); // false
        System.out.println(numSchema.isValid(-10) + " Nexp6: false"); // false
//  Ноль - не положительное число
        System.out.println(numSchema.isValid(0) + " Nexp7: false"); // false

        numSchema.range(5, 10);

        System.out.println(numSchema.isValid(5) + " NExp8: true"); // true
        System.out.println(numSchema.isValid(10) + " Nexp9 : true"); // true
        System.out.println(numSchema.isValid(4) + " Nexp10: false"); // false
        System.out.println(numSchema.isValid(11) + " Nexp11: false"); // false

    }
}
