package io.github.aakovalev.cbonds.wiremodel;

import lombok.Data;

@Data
public class Credentials {
    private final String login;
    private final String password;

    public Credentials(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
