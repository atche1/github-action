package com.example.spring.boot.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    public Recipe updateRecipe(Long id, Recipe updateRecipe) {
        Optional<Recipe> existingRecipe = recipeRepository.findById(id);
        if (existingRecipe.isPresent()) {
            Recipe recipe = existingRecipe.get();
            recipe.setName(updateRecipe.getName());
            recipe.setIngredient(updateRecipe.getIngredient());
            recipe.setCookingTime(updateRecipe.getCookingTime());
            return recipeRepository.save(recipe);
        } else {
            throw new RuntimeException("Recipe not found");
        }
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}
