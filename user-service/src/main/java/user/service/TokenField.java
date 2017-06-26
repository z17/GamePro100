package user.service;

public enum TokenField {
    ID("id"),
    LOGIN("login"),
    GROUP("group"),
    CREATE_DATE("token_create_date"),
    EXPIRATION_DATE("token_expiration_date");

    private final String value;

    TokenField(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
