package com.restaurant.management.repository;

import com.restaurant.management.model.Plat;
import com.restaurant.management.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatRepository extends JpaRepository<Plat, Long> {
    // Cette méthode trouvera tous les plats dans une catégorie spécifique
    List<Plat> findByCategorie(Categorie categorie);
}
