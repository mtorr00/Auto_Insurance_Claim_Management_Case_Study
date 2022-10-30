package com.gs.martintorrey.aicmbe2.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;


@Entity
@Table(name="Claims")
@Data
@NoArgsConstructor
public class ClaimEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String filedBy;

    private String filedByEmail;

    @NotBlank
    private String incidentDetails;

    @NotBlank
    private String status;

    private String urlsSupportingDocs[];

}
