package org.codetechn.auth.payload.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUpRequest {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20)
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20)
    private String number;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20)
    private String Password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
