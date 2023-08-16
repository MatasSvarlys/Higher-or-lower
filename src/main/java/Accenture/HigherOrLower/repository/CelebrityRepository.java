package Accenture.HigherOrLower.repository;

import Accenture.HigherOrLower.Celebrity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CelebrityRepository extends JpaRepository<Celebrity, Long> {
    List<Celebrity> findByCountryIgnoreCase(String countryCode);
    int findGoogleSearchCountByName(String name);
}
