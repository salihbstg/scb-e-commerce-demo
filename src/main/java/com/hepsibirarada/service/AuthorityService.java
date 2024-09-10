package com.hepsibirarada.service;

import com.hepsibirarada.model.Authority;
import com.hepsibirarada.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;
    public Authority findById(Long id){
        Optional<Authority> optionalAuthority=authorityRepository.findById(id);
        return optionalAuthority.orElse(null);
    }


}
