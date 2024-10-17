package io.github.aakovalev.cbonds;

import lombok.Data;

@Data
class Credentials {
    private final String login;
    private final String password;

    public Credentials(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
