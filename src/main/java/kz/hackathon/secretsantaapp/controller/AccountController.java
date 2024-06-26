package kz.hackathon.secretsantaapp.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.hackathon.secretsantaapp.dto.accountSettings.ChangePasswordRequest;
import kz.hackathon.secretsantaapp.dto.accountSettings.UpdateLoginEmailRequest;
import kz.hackathon.secretsantaapp.dto.registration.JwtAuthenticationResponse;
import kz.hackathon.secretsantaapp.model.user.User;
import kz.hackathon.secretsantaapp.service.AuthenticationService;
import kz.hackathon.secretsantaapp.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/settings")
@Tag(name="account-settings-controller")
@SecurityRequirement(name = "bearerAuth")
public class AccountController {
    private final AuthenticationService authenticationService;
    private final CustomUserDetailService customUserDetailService;

    @PostMapping("/update-login-email")
    public ResponseEntity<?> updateLoginEmail(@RequestBody UpdateLoginEmailRequest request) {
        try {
            JwtAuthenticationResponse jwtResponse = authenticationService.updateLoginEmail(request);
            return ResponseEntity.ok(jwtResponse); // Return the response with the new tokens
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            authenticationService.changePassword(request);
            return ResponseEntity.ok().body("Password changed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<?> deleteAccount() {
        try {
            User currentUser = customUserDetailService.getCurrentUser();
            authenticationService.deleteUserByUsername(currentUser.getEmail());
            return ResponseEntity.ok().body("Account deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
