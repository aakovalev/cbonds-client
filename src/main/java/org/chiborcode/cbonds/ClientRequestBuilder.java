package org.chiborcode.cbonds;

public class ClientRequestBuilder {
    private String user;
    private String password;

    public ClientRequest build() {
        ClientRequest request = new ClientRequest();
        request.setAuth(new Credentials(user, password));
        return request;
    }

    public ClientRequestBuilder withCredentials(String user, String password) {
        this.user = user;
        this.password = password;
        return this;
    }
}


