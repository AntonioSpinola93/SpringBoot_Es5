package com.example.demo.controller;

import com.example.demo.entity.Ingredient;
import com.example.demo.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Ingredient")
public class IngredientController {
    private IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addIngredient(@RequestBody Ingredient ingredient) {
        try {
            ingredientService.insertIngredient(ingredient);
            return ResponseEntity.ok("Ingrediente aggiunto");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/ingredients")
    public ResponseEntity<List<Ingredient>> getIngredient() {
        return ResponseEntity.ok(ingredientService.ingredientList());
    }

    @GetMapping(value = "/{id}")
    public Ingredient getIngredientById(@PathVariable Long id) {
        Ingredient ingredient = ingredientService.findIngredientById(id);
        return new Ingredient(ingredient.getId(),
                ingredient.getName(),
                ingredient.getVegetarian(),
                ingredient.getVegan(),
                ingredient.getGlutenFree(),
                ingredient.getLactoseFree());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteIngredientById(@PathVariable Long id) {
        ingredientService.deleteIngredientById(id);
        return ResponseEntity.ok("Ingrediente eliminato");

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateIngredientById(@PathVariable Long id, @RequestBody Ingredient updatedIngredient) {

        Ingredient existingIngredient = ingredientService.findIngredientById(id);
        if (existingIngredient == null) {
            return ResponseEntity.notFound().build();
        } else {
            existingIngredient.setName(updatedIngredient.getName());
            existingIngredient.setVegetarian(updatedIngredient.getVegetarian());
            existingIngredient.setVegan(updatedIngredient.getVegan());
            existingIngredient.setGlutenFree(updatedIngredient.getGlutenFree());
            existingIngredient.setLactoseFree(updatedIngredient.getLactoseFree());
            ingredientService.updateIngredient(existingIngredient);
            return ResponseEntity.ok("Ingrediente aggiornato");


        }


    }
}
