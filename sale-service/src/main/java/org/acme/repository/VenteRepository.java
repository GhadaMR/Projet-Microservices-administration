package org.acme.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Vente;

@ApplicationScoped
public class VenteRepository implements PanacheRepository<Vente> {
    // Les opérations CRUD sont déjà disponibles grâce à Panache
}
