package com.example.spring.boot.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @PostMapping("/recipe")
    public ResponseEntity<Recipe> saveRecipe(@RequestBody Recipe recipe){
       Recipe newRecipe = recipeService.saveRecipe(recipe);
        return ResponseEntity.ok(newRecipe);
    }

    @GetMapping("/recipes")
    public List<Recipe> getAllRecipes(){
        return recipeService.getAllRecipes();
    }

    @GetMapping("/recipes/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id){
        Optional<Recipe> recipe = recipeService.getRecipeById(id);
        return recipe.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("/recipes/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe){
        Recipe updatedRecipe = recipeService.updateRecipe(id, recipe);
        return ResponseEntity.ok(updatedRecipe);
    }

    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id){
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok("Product deleted successfully!");
    }
}
