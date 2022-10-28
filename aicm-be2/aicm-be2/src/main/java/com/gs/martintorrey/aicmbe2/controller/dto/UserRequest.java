package com.gs.martintorrey.aicmbe2.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserRequest {
    @Schema(example="johnalbesa@JOHNALBESAJOHNALBESA.com")
    private String email;

}
