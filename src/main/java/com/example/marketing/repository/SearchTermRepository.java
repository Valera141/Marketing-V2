package com.example.marketing.repository;

import com.example.marketing.model.SearchTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SearchTermRepository extends JpaRepository<SearchTerm, Integer> {
    
    // Busca todos los términos asociados a una campaña específica
    List<SearchTerm> findByCampaign_CampaignId(Integer campaignId);
}