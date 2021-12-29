package constants;

public enum ErrorsMsg {
    invalid_number("Неверно введен номер"),
    wrong_code("Неверно введен код"),
    obligatory_field("Обязательное поле"),
    maximum_length__surname_field("Превышена максимальная длина фамилии"),
    incorrect_email("Некорректный ввод. Заполните поле по шаблону example@domain.com"),
    registration_merchant_msg("Ваша заявка получена. Наш менеджер свяжется с Вами."),
    obligatory_field_merchant("Обязательное поле!"),
    maximum_length_surname_field_merchant("Превышена максимальная длина фамилии!"),
    incorrect_email_merchant("Введите валидный e-mail!"),
    invalid_number_merchant("Неверно введен номер!"),
    incorrect_inn_merchant("Должно быть не более 12 символов!"),
    only_digits_merchant("Только цифры!"),

    ;

    private String value;
    private ErrorsMsg(String value) {
        this.value = value; }

    public String getErrorMsg(){
        return value;
    }
}
