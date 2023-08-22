package Accenture.HigherOrLower.repository;

import Accenture.HigherOrLower.model.Celebrity;
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
    @Query("SELECT c FROM Celebrity c WHERE c.id = :id")
    Celebrity findCelebrityById(@Param("id") int id);
    List<Celebrity> findAll();
    //public Celebrity findCelebrityById(int id);
    List<Celebrity> findCelebrityByCountry(String country);
}
