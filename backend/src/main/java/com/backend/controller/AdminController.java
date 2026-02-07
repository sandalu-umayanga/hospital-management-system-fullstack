package com.backend.controller;

import com.backend.dto.LoginDto;
import com.backend.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private ResponseDto responseDto;

    @Autowired
    private com.backend.util.JwtUtils jwtUtils;

    @PostMapping("/loginAdmin")
    public ResponseEntity<ResponseDto> loginAdmin(@RequestBody LoginDto loginDto) {
        // Hardcoded admin for simplicity, or could be expanded to use database
        if ("admin@hospital.com".equals(loginDto.getEmail()) && "Admin@123".equals(loginDto.getPassword())) {
            responseDto.setCode("10");
            responseDto.setMessage("Admin logged in successfully");
            responseDto.setData(loginDto);
            responseDto.setToken(jwtUtils.generateToken(loginDto.getEmail(), "ADMIN"));
            return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
        } else {
            responseDto.setCode("03");
            responseDto.setMessage("Invalid admin credentials");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }
    }
}
