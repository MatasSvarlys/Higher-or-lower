package Accenture.HigherOrLower;

import java.util.ArrayList;
import java.util.List;

import Accenture.HigherOrLower.repository.CelebrityRepository;
import org.springframework.stereotype.Component;

@Component
public class CelebrityDatabase {

    private final CelebrityRepository celebrityRepository;

    public CelebrityDatabase(CelebrityRepository celebrityRepository) {
        this.celebrityRepository = celebrityRepository;

        // Adding all celebrities(WIP)
        celebrityRepository.save(new Celebrity("Kristaps Porzingis", "Latvia", 109100));
        celebrityRepository.save(new Celebrity("Arvydas Sabonis", "Lithuania", 60100));
        celebrityRepository.save(new Celebrity("Cukmens", "Latvia", 370));
        celebrityRepository.save(new Celebrity("Andrius Mamontovas", "Lithuania", 4500));
        celebrityRepository.save(new Celebrity("Lauris Reiniks", "Latvia", 3300));
        celebrityRepository.save(new Celebrity("Edita Vilkeviciute", "Lithuania", 2300));
        celebrityRepository.save(new Celebrity("Raimonds Pauls", "Latvia", 11000));
        celebrityRepository.save(new Celebrity("Antanas Smetona", "Lithuania", 3200));
        celebrityRepository.save(new Celebrity("Samanta Tina", "Latvia", 2900));
        celebrityRepository.save(new Celebrity("Algirdas", "Lithuania", 3500));
        //10/100
        celebrityRepository.save(new Celebrity("Arturs Irbe", "Latvia", 1300));
        celebrityRepository.save(new Celebrity("Dalia Grybauskaite", "Lithuania", 5300));
        celebrityRepository.save(new Celebrity("Vaira Vike-Freiberga", "Latvia", 3900));
        celebrityRepository.save(new Celebrity("Gediminas", "Lithuania", 7000));
        celebrityRepository.save(new Celebrity("Ernests Gulbis", "Latvia", 4600));
        celebrityRepository.save(new Celebrity("George Maciunas", "Lithuania", 1900));
        celebrityRepository.save(new Celebrity("Egils Levits", "Latvia", 9100));
        celebrityRepository.save(new Celebrity("Ingeborga Dapukaite", "Lithuania", 6800));
        celebrityRepository.save(new Celebrity("Edgars Rinkevics", "Latvia", 16300));
        celebrityRepository.save(new Celebrity("Jonas Mekas", "Lithuania", 12100));
        //20/100
        celebrityRepository.save(new Celebrity("Janis Lusis", "Latvia", 280));
        celebrityRepository.save(new Celebrity("Jonas Valanciunas", "Lithuania", 70200));
        celebrityRepository.save(new Celebrity("Martins Licis", "Latvia", 11300));
        celebrityRepository.save(new Celebrity("Linas Kleiza", "Lithuania", 2900));
        celebrityRepository.save(new Celebrity("Aminata", "Latvia", 7300));
        celebrityRepository.save(new Celebrity("Algirdas Brazauskas", "Lithuania", 2100));
        celebrityRepository.save(new Celebrity("Lina Muze", "Latvia", 1400));
        celebrityRepository.save(new Celebrity("Donatas Banionis", "Lithuania", 2400));
        celebrityRepository.save(new Celebrity("Elina Garanca", "Latvia", 5400));
        celebrityRepository.save(new Celebrity("Emilia Plater", "Lithuania", 16600));
        //30/100
        celebrityRepository.save(new Celebrity("Rutanya Alda", "Latvia", 5200));
        celebrityRepository.save(new Celebrity("Aksana", "Lithuania", 4600));
        celebrityRepository.save(new Celebrity("Agata Muceniece", "Latvia", 7400));
        celebrityRepository.save(new Celebrity("Al Jolson", "Lithuania", 32500));
        celebrityRepository.save(new Celebrity("Inese Galante", "Latvia", 800));
        celebrityRepository.save(new Celebrity("Aleksandras Stulginskis", "Lithuania", 1100));
        celebrityRepository.save(new Celebrity("Jelena Ostapenko", "Latvia", 105000));
        celebrityRepository.save(new Celebrity("George Mikell", "Lithuania", 1400));
        celebrityRepository.save(new Celebrity("Ginta Lapina", "Latvia", 560));
        celebrityRepository.save(new Celebrity("Dom Zeglaitis", "Lithuania", 2200));
        //40/100
        celebrityRepository.save(new Celebrity("DJ Lethal", "Latvia", 7400));
        celebrityRepository.save(new Celebrity("Karolis Inokaitis", "Lithuania", 570));
        celebrityRepository.save(new Celebrity("Mikhail Tal", "Latvia", 31700));
        celebrityRepository.save(new Celebrity("Monika Linkyte", "Lithuania", 10400));
        celebrityRepository.save(new Celebrity("Renars Kaupers", "Latvia", 4400));
        celebrityRepository.save(new Celebrity("Donny Montell", "Lithuania", 1400));
        celebrityRepository.save(new Celebrity("Ksenia Solo", "Latvia", 30400));
        celebrityRepository.save(new Celebrity("Bria Vinaite", "Lithuania", 23300));
        celebrityRepository.save(new Celebrity("Justs Sirmais", "Latvia", 1200));
        celebrityRepository.save(new Celebrity("Ruta Meilutyte", "Lithuania", 4900));
        //50/100

        // Templates (because lazy :p)
        // celebrityRepository.save(new Celebrity("", "Latvia", ));
        // celebrityRepository.save(new Celebrity("", "Lithuania", ));

    }

    public List<Celebrity> getListOfCelebritiesByCountry(String countryCode) {
        return celebrityRepository.findByCountryIgnoreCase(countryCode);
    }
}