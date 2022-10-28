package com.gs.martintorrey.aicmbe2.controller.dto;

import java.util.ArrayList;

public record ClaimDto(Long id, String filedBy, String filedByEmail, String incidentDetails, String status,
                       ArrayList<String> urlsSupportingDocs) {
}
