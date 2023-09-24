package com.products.controller;


import com.products.dto.APIResponse;
import com.products.dto.AuthRequest;
import com.products.dto.SignInResponse;
import com.products.dto.UserDTO;
import com.products.entity.User;
import com.products.exceptions.InvalidCredentialsException;
import com.products.exceptions.ResourceNotFoundException;
import com.products.mapper.UserMapper;
import com.products.repository.UserRepository;
import com.products.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;

    @Autowired
    UserMapper userMapper ;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping(value = "/signup",produces = "application/json" , consumes = "application/json")
    public ResponseEntity<APIResponse> signupB2BUserAndCreateVendor(@RequestBody UserDTO userDTO) {

        // Encode the password before saving it
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);

        UserDTO response = userMapper.entityToDTO(repository.save(userMapper.dtoToEntity(userDTO)));
        APIResponse<String> responseDTO = APIResponse
                .<String>builder()
                .status(200)
                .data("Signup successfull")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }



    @PostMapping(path = "/authenticate",produces = "application/json" , consumes = "application/json")
    public ResponseEntity<APIResponse> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        User b2bUser ;
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));


        b2bUser = repository.findByUserName(authRequest.getUserName()).orElseThrow(
                () -> new ResourceNotFoundException("B2bUser not found with mobileNumber:" + authRequest.getUserName()));

        // The passwordEncoder.encode() method will produce different output for the same input on each execution,
        // as it uses a randomized salt to hash the password. However, during the login process, the encoded password stored in the database is retrieved
        // and compared with the user's entered password using the passwordEncoder.matches() method.
        // This method takes two arguments: the first argument is the raw password entered by the user, and the second argument is the encoded password retrieved from the database.
        // The matches() method internally extracts the salt from the encoded password and uses it to hash the raw password entered by the user, and then compares the resulting hash with the stored hash.
        // This way, even though the hashed password stored in the database is different each time the encode() method is called, the matches() method can still correctly validate the user's password during login
        if (!passwordEncoder.matches(authRequest.getPassword(), b2bUser.getPassword())) {
            throw new InvalidCredentialsException("invalid username/password");
        }

        try {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtUtil.generateToken(authRequest.getUserName(), b2bUser);

            SignInResponse signInResponse = SignInResponse.builder()
                    .userId(b2bUser.getUserId())
                    .roleId(b2bUser.getRoleId())
                    .jwtToken(jwtToken)
                    .build();

            APIResponse<SignInResponse> responseDTO = APIResponse
                    .<SignInResponse>builder()
                    .status(200)
                    .data(signInResponse)
                    .build();
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Exception occurred while generateToken , Exception message {}", ex.getMessage());
            throw new InvalidCredentialsException("invalid username/password");
        }
    }


}
