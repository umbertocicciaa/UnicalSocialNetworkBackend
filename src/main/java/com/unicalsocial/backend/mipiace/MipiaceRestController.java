package com.unicalsocial.backend.mipiace;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/Mipiace")
@Tag(name="Mipiace")
@SecurityRequirement(name = "Bearer Authentication")
public class MipiaceRestController {
    private final MipiaceService mipiaceService;

    @GetMapping(value = "/mipiace/{post_Id}")
    @CrossOrigin
    public ResponseEntity<EsisteMipiaceResponse> exists(@PathVariable("post_Id") int post_Id, Authentication authentication) {
        return ResponseEntity.ok(this.mipiaceService.existMipiace(post_Id,authentication));
    }
}
