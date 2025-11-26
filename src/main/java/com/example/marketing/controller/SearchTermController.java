package com.example.marketing.controller;

import com.example.marketing.dto.SearchTermRequestDTO;
import com.example.marketing.dto.SearchTermResponseDTO;
import com.example.marketing.mapper.SearchTermMapper;
import com.example.marketing.model.Campaign;
import com.example.marketing.model.SearchTerm;
import com.example.marketing.repository.CampaignRepository;
import com.example.marketing.repository.SearchTermRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/search-terms")
@RequiredArgsConstructor
public class SearchTermController {

    private final SearchTermRepository searchTermRepository;
    private final CampaignRepository campaignRepository;

    @PostMapping
    public ResponseEntity<SearchTermResponseDTO> create(@Valid @RequestBody SearchTermRequestDTO request) {
        Campaign campaign = campaignRepository.findById(request.campaignId())
                .orElseThrow(() -> new EntityNotFoundException("Campaña no encontrada"));

        SearchTerm entity = SearchTermMapper.toEntity(request, campaign);
        return new ResponseEntity<>(SearchTermMapper.toResponse(searchTermRepository.save(entity)), HttpStatus.CREATED);
    }

    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<SearchTermResponseDTO>> getByCampaign(@PathVariable Integer campaignId) {
        List<SearchTermResponseDTO> terms = searchTermRepository.findByCampaign_CampaignId(campaignId)
                .stream().map(SearchTermMapper::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(terms);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!searchTermRepository.existsById(id)) throw new EntityNotFoundException("Término no encontrado");
        searchTermRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}