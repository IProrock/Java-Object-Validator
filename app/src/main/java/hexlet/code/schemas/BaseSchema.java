package hexlet.code.schemas;

public class BaseSchema {

    boolean isNullValid = true;

    public BaseSchema required() {
        this.isNullValid = false;

        return this;
    }

    public boolean isValid(Object obj) {

        if (obj == null && !isNullValid) {
            return false;
        }

        return true;
    }

}
