package org.sevinc.SevincurlShortener.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUp {
    private  String fullName;
    private String email;
    private String password;
    private String passwordAgain;
}
