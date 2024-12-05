package com.adriankich.email.model;

import com.adriankich.email.enums.EmailStatus;
import com.adriankich.email.enums.EmailType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "email_messages")
public class Email {

    @Id
    private String id;

    @Indexed
    private String sender;

    @Indexed
    private String receiver;

    private String subject;

    private String text;

    private EmailType type;

    private EmailStatus status;

    @Field("payment_id")
    @Indexed
    private Long paymentId;
}
