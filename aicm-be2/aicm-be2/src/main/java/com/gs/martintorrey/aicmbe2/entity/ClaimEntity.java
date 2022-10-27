package com.gs.martintorrey.aicmbe2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="Claims")
@Getter
@Setter
@NoArgsConstructor
public class ClaimEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String filedBy;
    private String incidentDetails;
    private String status;
    private ArrayList<String> urlsSupportingDocs;

}
