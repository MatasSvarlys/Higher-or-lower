package Accenture.HigherOrLower.repository;

import Accenture.HigherOrLower.Celebrity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CelebrityRepository extends JpaRepository<Celebrity, Long> {
    List<Celebrity> findByCountryIgnoreCase(String countryCode);
    @Query("SELECT c.googleSearchCount FROM Celebrity c WHERE c.name = :name")
    int findGoogleSearchCountByName(@Param("name") String name);
}
