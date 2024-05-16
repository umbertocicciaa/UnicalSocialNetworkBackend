package com.unicalsocial.backend.mipiace;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/Mipiace")
@Tag(name="Mipiace")
public class MipiaceRestController {
    private final MipiaceService mipiaceService;

    @GetMapping(value = "/mipiace/{post_Id}/{user_id}")
    @CrossOrigin
    public ResponseEntity<Boolean> exists(@PathVariable("post_Id") int post_Id, @PathVariable("user_id") int user_id) {
        return ResponseEntity.ok(this.mipiaceService.existMipiace(user_id,post_Id));
    }
}
