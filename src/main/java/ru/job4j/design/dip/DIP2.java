package ru.job4j.design.dip;

/**
 * Нарушение DIP из-за типа возвращаемого значения конкретной реализации.
 * Такой код тяжело будет поддерживать и расширять.
 */

public class DIP2 {
     public static class Reporter {
          private String createReport() {
               return "new report";
          }

          public Document returnReport() {
               createReport();
               return new Document();
          }

     }

     public static class Document {

     }
}
