package constants;

public enum ErrorsMsg {
    invalid_number("Неверно введен номер"),
    wrong_code("Неверно введен код"),
    obligatory_field("Обязательное поле");

    private String value;
    private ErrorsMsg(String value) {
        this.value = value; }

    public String getErrorMsg(){
        return value;
    }
}
