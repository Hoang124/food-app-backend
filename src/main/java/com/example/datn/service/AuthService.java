package com.example.datn.service;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.datn.entity.Account;
import com.example.datn.entity.RefreshToken;
import com.example.datn.entity.User;
import com.example.datn.enums.Role;
import com.example.datn.jwt.JwtTokenProvider;
import com.example.datn.repository.AccountRepository;
import com.example.datn.repository.RefreshTokenRepository;
import com.example.datn.repository.UserRepository;
import com.example.datn.request.AuthRequest;
import com.example.datn.request.RegisterRequest;
import com.example.datn.response.AuthResponse;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final AccountRepository accountRepository;
	private final RefreshTokenRepository tokenRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtService;
	private final AuthenticationManager authenticationManager;

	private User saveUser(RegisterRequest request) {
	    var user = User.builder()
	    		.name(request.getName())
		        .birthday(request.getBirthDay())
		        .phone_number(request.getPhoneNumber())
		        .address(request.getAddress())
		        .build();
		    userRepository.save(user);
		    return user;
	}
	
	
	public AuthResponse register(RegisterRequest request) {
		var user = saveUser(request);
		var account = Account.builder()
	        .email(request.getEmail())
	        .password(passwordEncoder.encode(request.getPassword()))
	        .role(Role.USER)
	        .user(user)
	        .build();
	    var savedUser = accountRepository.save(account);
	    var jwtToken = jwtService.generateToken(account);
	    var refreshToken = jwtService.generateRefreshToken(account);
	    saveUserToken(savedUser, jwtToken);
	    return AuthResponse.builder()
	        .accessToken(jwtToken)
	            .refreshToken(refreshToken)
	        .build();
	  }

	  public AuthResponse authenticate(AuthRequest request) {
	    authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(
	            request.getEmail(),
	            request.getPassword()
	        )
	    );
	    var account = accountRepository.findByEmail(request.getEmail())
	        .orElseThrow();
	    var jwtToken = jwtService.generateToken(account);
	    var refreshToken = jwtService.generateRefreshToken(account);
	    revokeAllUserTokens(account);
	    saveUserToken(account, jwtToken);
	    return AuthResponse.builder()
	        .accessToken(jwtToken)
	            .refreshToken(refreshToken)
	        .build();
	  }

	  private void saveUserToken(Account account, String jwtToken) {
	    var token = RefreshToken.builder()
	        .account(account)
	        .token(jwtToken)
	        .expired(false)
	        .revoked(false)
	        .build();
	    tokenRepository.save(token);
	  }
	  

	  private void revokeAllUserTokens(Account account) {
	    var validUserTokens = tokenRepository.findAllValidTokenByUser(account.getEmail());
	    if (validUserTokens.isEmpty())
	      return;
	    validUserTokens.forEach(token -> {
	      token.setExpired(true);
	      token.setRevoked(true);
	    });
	    tokenRepository.saveAll(validUserTokens);
	  }

	  public void refreshToken(
	          HttpServletRequest request,
	          HttpServletResponse response
	  ) throws IOException {
	    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	    final String refreshToken;
	    final String userEmail;
	    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
	      return;
	    }
	    refreshToken = authHeader.substring(7);
	    userEmail = jwtService.extractUsername(refreshToken);
	    if (userEmail != null) {
	      var user = this.accountRepository.findByEmail(userEmail)
	              .orElseThrow();
	      if (jwtService.isTokenValid(refreshToken, user)) {
	        var accessToken = jwtService.generateToken(user);
	        revokeAllUserTokens(user);
	        saveUserToken(user, accessToken);
	        var authResponse = AuthResponse.builder()
	                .accessToken(accessToken)
	                .refreshToken(refreshToken)
	                .build();
	        try {
				new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
			} catch (StreamWriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatabindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (java.io.IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
	    }
	  }
}
