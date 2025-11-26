package com.example.marketing.repository;
import com.example.marketing.model.SavedDashboardView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SavedDashboardViewRepository extends JpaRepository<SavedDashboardView, Integer> {
    List<SavedDashboardView> findByUser_UserId(Integer userId);
}