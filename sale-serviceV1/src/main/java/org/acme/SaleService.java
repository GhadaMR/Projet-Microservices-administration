package org.acme;

import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Vente;
import org.acme.repository.VenteRepository;
import org.acme.service.VenteService;

@ApplicationScoped
@Startup
public class SaleService {





    @Inject
    VenteRepository venteRepository;
    @Inject
    VenteService venteService;
    public void onStart() {
        // Ajout des produits à la base de données au démarrage
        venteRepository.persist(new Vente(100.1, 3,"produit1"));
        venteRepository.persist(new Vente(250.5, 1,"produit2"));
        venteRepository.persist(new Vente(300, 5,"produit3"));

        // Affichage des produits dans la console
        venteRepository.listAll().forEach(System.out::println);
    }
}
