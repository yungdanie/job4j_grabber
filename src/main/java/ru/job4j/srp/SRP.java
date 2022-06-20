package ru.job4j.srp;

public abstract class SRP {

    /** При преобразовании времени мы используем обычный класс, возвращая String, при изменении возвращаемого формата
     * могут возникнуть проблемы в последующем методе loadTime(), который например, будет загружать
     * преобразованное время в БД. Что нарушает принцип SRP.
     *
     * При этом все методы имеют модификатор "public", внутренняя логика не инкапслируется,
     * что тоже нарушает принцип SRP.
     */

    public String getTime(String time) {
        return Response.modifyTime(time);
    }

    public void loadTime() {
        getTime("time");
    }

    /** При изменении кода класса Response, который отвечает за другую ответственность,
     * нам необходимо будет изменить код и в этом классе, что является нашурением принципа SRP.
     */
    Response response = new Response();

    public void changeResponse(String name) {
        response.setName(name);
    }


    /**
     * Классу предоставляется слишком много несвязанных между собой методов, что нарушает принцип SRP.
     */
    public void print() {
    }

    public int calculateSum(Object object) {
        return 0;
    }

    public String sumAccounts(Object object) {
        return null;
    }




}
