package org.ember.emberbackend.models;

import lombok.Getter;
import lombok.Setter;
import org.ember.emberbackend.enums.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class User {
    private String name;
    private String email;
    @Id
    private String setUserName;
    private Status status;
}
