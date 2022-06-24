package ru.job4j.design.ocp;

/**
 * Если мы захотим добавить новый метод, например "createXML", то нам придется в одном классе
 * создавать два метода, один будет создавать HTML, другой - XML, что означает что поведение сущности будет расширено
 * не за счет создания новой сущности, а за счет изменения кода, что неправильно!.
 */

public class OCP2 {

    public String createHTML() {
        return """
                <b>HTML text<//b>
                """;
    }

    public String createXML() {
        return """
                  <?xml version="1.0" encoding="UTF-8" ?>
                  <!DOCTYPE greeting [
                    <!ELEMENT greeting (#PCDATA)>
                  ]>
                  <greeting>Hello, world!</greeting>
                  """;
    }
}
