package org.sevinc.SevincurlShortener.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordForm {
    private String fullName;
    private String password;
    private String passwordAgain;

}
