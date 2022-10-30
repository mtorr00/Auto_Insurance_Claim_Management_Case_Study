package com.gs.martintorrey.aicmbe2.controller;

import com.gs.martintorrey.aicmbe2.entity.ClaimEntity;
import com.gs.martintorrey.aicmbe2.service.ClaimServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/claims")
public class ClaimController {
    @Autowired
    public ClaimServiceImpl claimService;

    @GetMapping("/")
    public String home() {return "<HTML><h1>CLAIMS BLANK WELCOME PAGE</h1></HTML>";}

    @GetMapping("/admin/{username}/claims")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ClaimEntity> adminGetClaim(@PathVariable String username){return this.claimService.getClaims(username, "admin");}

    @GetMapping("/user/{username}/claims")
    @PreAuthorize("hasRole('USER')")
    public List<ClaimEntity> userGetClaim(@PathVariable String username){return this.claimService.getClaims(username, "user");}

    @PostMapping("/user/{username}/claim/add")
    @PreAuthorize("hasRole('USER')")
    public ClaimEntity addClaim(@PathVariable String username, @RequestBody ClaimEntity claim){return this.claimService.createClaim(username,claim,"user");}

    @PutMapping("/user/{username}/claim/edit")
    @PreAuthorize("hasRole('USER')")
    public ClaimEntity userEditClaim(@PathVariable String username, @RequestBody ClaimEntity claim){return this.claimService.saveClaim(username,claim,"user");}

    @PutMapping("/admin/{username}/claim/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ClaimEntity adminEditClaim(@PathVariable String username,@RequestBody ClaimEntity claim){return this.claimService.saveClaim(username, claim,"admin");}

    @DeleteMapping("/admin/{username}/claim/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void adminDeleteClaim(@PathVariable String username, @RequestBody ClaimEntity claim){this.claimService.deleteClaim(username,claim,"admin");}

    @DeleteMapping("/user/{username}/claim/delete")
    @PreAuthorize("hasRole('USER')")
    public void userDeleteClaim(@PathVariable String username, @RequestBody ClaimEntity claim){this.claimService.deleteClaim(username,claim,"user");}

}
