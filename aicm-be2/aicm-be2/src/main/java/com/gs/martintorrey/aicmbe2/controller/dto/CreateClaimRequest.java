package com.gs.martintorrey.aicmbe2.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@Data
public class CreateClaimRequest {
    @Schema(example="14005")
    @NotBlank
    private Long id;

    @Schema(example="username")
    @NotBlank
    private String filedBy;

    @Schema(example="username@email.com")
    @NotBlank
    private String filedByEmail;

    @Schema(example="incident happened like this. it was bad.")
    @NotBlank
    private String incidentDetails;

    @Schema(example="pending")
    @NotBlank
    private String status;

    @Schema(example="{link1, link2}")
    private ArrayList<String> urlsSupportingDocs;
}
