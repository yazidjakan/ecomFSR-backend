package com.ecom.paiement_service.entity;


import com.ecom.paiement_service.enums.StatutPaiement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionId; // ID de transaction (ex: Stripe transaction ID)
    private Double amount;        // Montant du paiement
    private String currency;      // Devise (ex: USD, EUR)
    private String customerEmail; // Email du client
    private LocalDateTime paymentDate; // Date du paiement

    @Enumerated(EnumType.STRING)
    private StatutPaiement statutPaiement; // Statut du paiement

    // Autres champs comme les informations sur la carte de crédit ou Stripe token peuvent être ajoutés
}

