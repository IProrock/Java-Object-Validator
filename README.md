### Hexlet tests and linter status:
[![Actions Status](https://github.com/IProrock/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/IProrock/java-project-78/actions)

[![Maintainability](https://api.codeclimate.com/v1/badges/67b973929c0d70607abd/maintainability)](https://codeclimate.com/github/IProrock/java-project-78/maintainability)

[![Test Coverage](https://api.codeclimate.com/v1/badges/67b973929c0d70607abd/test_coverage)](https://codeclimate.com/github/IProrock/java-project-78/test_coverage)

# Validator
This is Java library for validating Object according to difined schema.
Schema is set by user.

**Supported data types and checks:**
- String:
    - Type mismatch check
    - Not "null" and String is not empty check
    - Check that string contains specified substring
    - Check that string is not shorter than specified length
- Integer
    - Type mismatch validation
    - Not "null" check
    - Check that value is positive (>0)
    - Check that value is within specified range
- Map
    - Type mismatch validation
    - Not "null" check
    - Map size validation according to set value
    - Map structure validation according to defined schema

## Getting started
**String**
```ts
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;

Validator v = new Validator();

StringSchema schema = v.string();

// Пока на вызван метод required(), null и пустая строка считаются валидным
schema.isValid(""); // true
schema.isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(""); // false
schema.isValid(5); // false
schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true

schema.contains("wh").isValid("what does the fox say"); // true
schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

schema.isValid("what does the fox say"); // false
// Здесь уже false, так как добавлена еще одна проверка contains("whatthe")
```
**Integer**
```ts
import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;

Validator v = new Validator();

NumberSchema schema = v.number();

// Пока не вызван метод required(), null считается валидным
schema.isValid(null); // true
schema.positive().isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid("5"); // false
schema.isValid(10) // true

// Потому что ранее мы вызвали метод positive()
schema.isValid(-10); // false
//  Ноль — не положительное число
schema.isValid(0); // false

schema.range(5, 10);

schema.isValid(5); // true
schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false
```
***Map***
```ts
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;

Validator v = new Validator();

MapSchema schema = v.map();

schema.isValid(null); // true

schema.required();

schema.isValid(null) // false
schema.isValid(new HashMap()); // true
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true
```
***Map structure validation***
```ts
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator v = new Validator();

MapSchema schema = v.map();

// shape позволяет описывать валидацию для значений объекта Map по ключам
Map<String, BaseSchema> schemas = new HashMap<>();

// Определяем схемы валидации для значений свойств "name" и "age"
// Имя должно быть строкой, обязательно для заполнения
schemas.put("name", v.string().required());
// Возраст должен быть положительным числом
schemas.put("age", v.number().positive());
schema.shape(schemas);

// Проверяем объекты
Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "Maya");
human2.put("age", null);
schema.isValid(human2); // true

Map<String, Object> human3 = new HashMap<>();
human3.put("name", "");
human3.put("age", null);
schema.isValid(human3); // false

Map<String, Object> human4 = new HashMap<>();
human4.put("name", "Valya");
human4.put("age", -5);
schema.isValid(human4); // false
```