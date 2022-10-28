package com.gs.martintorrey.aicmbe2.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Claim {

    private Long id;

    private String filedBy;
    private String filedByEmail;
    private String incidentDetails;
    private String status;
    private ArrayList<String> urlsSupportingDocs = new ArrayList<>();

}
