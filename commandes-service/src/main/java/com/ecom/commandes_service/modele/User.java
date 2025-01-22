package com.ecom.commandes_service.modele;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
}
