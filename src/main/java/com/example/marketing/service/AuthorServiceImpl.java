package com.example.marketing.service;

import com.example.marketing.dto.AuthorRequestDTO;
import com.example.marketing.dto.AuthorResponseDTO;
import com.example.marketing.mapper.AuthorMapper;
import com.example.marketing.model.Author;
import com.example.marketing.repository.AuthorRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

	private final AuthorRepository repository;

	@Override
	public AuthorResponseDTO create(AuthorRequestDTO request) {
		Author entity = AuthorMapper.toEntity(request);
		repository.save(entity);
		return AuthorMapper.toResponse(entity);
	}

	@Override
	public AuthorResponseDTO update(Integer id, AuthorRequestDTO request) {
		Author entity = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Autor no encontrado"));
		AuthorMapper.copyToEntity(request, entity);
		repository.save(entity);
		return AuthorMapper.toResponse(entity);
	}

	@Override
	public AuthorResponseDTO findById(Integer id) {
		return repository.findById(id)
				.map(AuthorMapper::toResponse)
				.orElseThrow(() -> new RuntimeException("Autor no encontrado"));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<AuthorResponseDTO> findAll(Pageable pageable) {

		Page<Author> page = repository.findAll(pageable);

		return page.map(AuthorMapper::toResponse);
	}


	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}
}
