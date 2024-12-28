package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import org.acme.model.Vente;
import org.acme.repository.VenteRepository;


@ApplicationScoped
public class VenteService {

    @Inject
    VenteRepository venteRepository;

    public List<Vente> findAll() {
        return venteRepository.listAll();
    }

    public Vente findById(Long id) {
        return venteRepository.findById(id);
    }

    @Transactional
    public Vente create(Vente vente) {
        venteRepository.persist(vente);
        return vente;
    }

    @Transactional
    public Vente update(Long id, Vente updatedVente) {
        Vente existingVente = venteRepository.findById(id);
        if (existingVente != null) {
            existingVente.setProduit(updatedVente.getProduit());
            existingVente.setQuantite(updatedVente.getQuantite());
            existingVente.setPrix(updatedVente.getPrix());
        }
        return existingVente;
    }

    @Transactional
    public void delete(Long id) {
        venteRepository.deleteById(id);
    }
}
