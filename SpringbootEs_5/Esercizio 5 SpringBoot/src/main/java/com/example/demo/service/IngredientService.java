package com.example.demo.service;

import com.example.demo.entity.Ingredient;
import com.example.demo.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }
   public void insertIngredient(Ingredient ingredient){
        ingredientRepository.save(ingredient);
}

 public List<Ingredient> ingredientList(){
 return ingredientRepository.findAll();
 }
 public Ingredient findIngredientById(Long id){
return ingredientRepository.getReferenceById(id);
 }
 public void deleteIngredientById(Long id){
       ingredientRepository.deleteById(id);

 }
 public void updateIngredient(Ingredient ingredient){
 ingredientRepository.delete(ingredient);
 ingredientRepository.save(ingredient);
 }

}
