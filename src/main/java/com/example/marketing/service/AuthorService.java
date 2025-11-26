package com.example.marketing.service;

import java.util.List;
import com.example.marketing.dto.AuthorRequestDTO;
import com.example.marketing.dto.AuthorResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {

	AuthorResponseDTO create(AuthorRequestDTO request);

	AuthorResponseDTO update(Integer id, AuthorRequestDTO request);

	AuthorResponseDTO findById(Integer id);

	Page<AuthorResponseDTO> findAll(Pageable pageable);


	void delete(Integer id);
}
