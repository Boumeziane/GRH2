/*package com.rh.grh.service.impl;

import com.rh.grh.dto.AuthRequest;
import com.rh.grh.dto.AuthResponse;
import com.rh.grh.entity.Account;
import com.rh.grh.entity.Role;
import com.rh.grh.repository.AccountRepository;
import com.rh.grh.config.JwtTokenProvider;
import com.rh.grh.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public AuthResponse login(AuthRequest request) {
        Account account = accountRepository.findByUsername(request.getUsername())
                .filter(acc -> passwordEncoder.matches(request.getPassword(), acc.getPassword()))
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        String token = jwtTokenProvider.generateToken(account.getUsername(), account.getRole().name());

        return new AuthResponse(
                account.getId(),
                account.getUsername(),
                account.getRole(),
                token
        );
    }

    @Override
    public AuthResponse register(AuthRequest request) {
        if (accountRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        Account account = new Account();
        account.setUsername(request.getUsername());
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setRole(Role.EMPLOYE); // par défaut

        Account saved = accountRepository.save(account);

        String token = jwtTokenProvider.generateToken(saved.getUsername(), saved.getRole().name());

        return new AuthResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getRole(),
                token
        );
    }

    @Override
    public boolean usernameExists(String username) {
        return accountRepository.existsByUsername(username);
    }
}
*/