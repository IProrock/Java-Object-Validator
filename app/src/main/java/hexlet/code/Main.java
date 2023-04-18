package hexlet.code;
import hexlet.code.schemas.StringSchema;

public class Main {

    public static void main(String[] args) {

        Validator validator = new Validator();
        StringSchema schema = validator.string();
        System.out.println(schema.isValid("") + " Expected 1: True");
        System.out.println(schema.isValid(null) + " Exp 2: True");

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


    }
}
