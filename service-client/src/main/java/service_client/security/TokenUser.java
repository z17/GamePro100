package service_client.security;

public final class TokenUser {
    private final Long id;
    private final String login;
    private final String group;

    public TokenUser(Long id, String login, String group) {
        this.id = id;
        this.login = login;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getGroup() {
        return group;
    }
}
