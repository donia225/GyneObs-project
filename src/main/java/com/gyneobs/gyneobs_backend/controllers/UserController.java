package com.gyneobs.gyneobs_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gyneobs.gyneobs_backend.dto.CreateSecretaryRequest;
import com.gyneobs.gyneobs_backend.dto.UserResponse;
import com.gyneobs.gyneobs_backend.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/api/users")
@RequiredArgsConstructor
public class UserController {
@Autowired 
private final UserService userService;


@PostMapping("/secretaires")
public ResponseEntity<UserResponse> createSecretary( 
    @Valid @RequestBody CreateSecretaryRequest request){
    return ResponseEntity.status(HttpStatus.CREATED)
                         .body(userService.createSecretary(request));
}

@GetMapping("/allSecretaires")
public ResponseEntity<List<UserResponse>> getSecretaires(){  
  return ResponseEntity.ok(userService.listAllSecretaires());
}


@GetMapping ("/secretaires/{id}")
public ResponseEntity<UserResponse> getSecretaryById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getSecretaryById(id));
}

// @PatchMapping("/secretaires/{id}/activate")
// public ResponseEntity<UserResponse> activateSecretary(@PathVariable Long id) {
 
//         return ResponseEntity.ok(userService.activateUser(id));
//     }
   
// @PatchMapping ("/secretaires/{id}/deactivate")
// public ResponseEntity<UserResponse> deactivateSecretary(@PathVariable Long id) {
//     return ResponseEntity.ok(userService.deactivateUser(id));
// }
// @PutMapping 
// public String updateUser() {
//     return "Update user";
// }

// @PutMapping 
// public String updateUserPassword() {
//     return "Update user password";
// }

@DeleteMapping("/secretaires/{id}")
public String deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return "User deleted successfully";
}










}
