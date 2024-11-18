package com.restaurant.management.controller;

import com.restaurant.management.model.Plat;
import com.restaurant.management.model.Categorie;
import com.restaurant.management.service.PlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/plats")
public class PlatController {

    @Autowired
    private PlatService platService;

    @ModelAttribute("categories")
    public Categorie[] getCategories() {
        return Categorie.values();
    }

    // Formulaire pour créer un plat
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("plat", new Plat());
        return "admin/createPlat"; // Chemin mis à jour pour correspondre au dossier admin
    }

    @PostMapping("/create")
    public String createPlat(@ModelAttribute("plat") Plat plat) {
        platService.createPlat(plat);
        return "redirect:/admin/plats";
    }

    // Formulaire pour modifier un plat
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Plat plat = platService.getPlatById(id).orElseThrow(() -> new RuntimeException("Plat non trouvé"));
        model.addAttribute("plat", plat);
        return "admin/editPlat"; // Chemin mis à jour pour correspondre au dossier admin
    }

    @PostMapping("/{id}/edit")
    public String updatePlat(@PathVariable Long id, @ModelAttribute("plat") Plat plat) {
        plat.setId(id);
        platService.updatePlat(plat);
        return "redirect:/admin/plats";
    }

    // Liste des plats pour la gestion des plats par l'administrateur
    @GetMapping
    public String getAllPlats(Model model) {
        model.addAttribute("plats", platService.getAllPlats());
        return "admin/platsList"; // Chemin mis à jour pour correspondre au dossier admin
    }

    // Afficher le menu pour les utilisateurs, organisé par catégorie
    @GetMapping("/menu")
    public String menu(Model model) {
        List<Plat> plats = platService.getAllPlats();
        Map<Categorie, List<Plat>> platsParCategorie = plats.stream()
                .collect(Collectors.groupingBy(Plat::getCategorie));

        model.addAttribute("platsParCategorie", platsParCategorie);
        return "admin/menu"; // Chemin mis à jour pour correspondre au dossier admin
    }

    // Supprimer un plat
    @DeleteMapping("/{id}")
    public String deletePlat(@PathVariable Long id) {
        platService.deletePlat(id);
        return "redirect:/admin/plats";
    }
}
