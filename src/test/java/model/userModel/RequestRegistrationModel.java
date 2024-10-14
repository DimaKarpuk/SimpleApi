package model.userModel;

import lombok.Data;

@Data
public class RequestRegistrationModel {
    private String
            password,
            userName;
}