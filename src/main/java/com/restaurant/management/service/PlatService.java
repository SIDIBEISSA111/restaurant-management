package com.restaurant.management.service;

import com.restaurant.management.model.Plat;
import com.restaurant.management.model.Categorie;
import com.restaurant.management.repository.PlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatService {

    @Autowired
    private PlatRepository platRepository;

    // Récupère tous les plats
    public List<Plat> getAllPlats() {
        return platRepository.findAll();
    }

    // Récupère un plat par son identifiant
    public Optional<Plat> getPlatById(Long id) {
        return platRepository.findById(id);
    }

    // Crée un nouveau plat
    public Plat createPlat(Plat plat) {
        return platRepository.save(plat);
    }

    // Met à jour un plat existant
    public Plat updatePlat(Plat plat) {
        return platRepository.save(plat);
    }

    // Supprime un plat par son identifiant
    public void deletePlat(Long id) {
        platRepository.deleteById(id);
    }

    // Récupère les plats par catégorie
    public List<Plat> getPlatsByCategorie(Categorie categorie) {
        return platRepository.findByCategorie(categorie);
    }
}
