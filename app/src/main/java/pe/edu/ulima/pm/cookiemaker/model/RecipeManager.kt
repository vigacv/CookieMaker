package pe.edu.ulima.pm.cookiemaker.model

import pe.edu.ulima.pm.cookiemaker.R

class RecipeManager {

    fun getRecipes(): List<Recipe>{
        val recipes = arrayListOf<Recipe>()
        val ingredients: List<Ingredient> = arrayListOf(Ingredient(1,"leche"), Ingredient(2,"harina"), Ingredient(3,"huevos"))
        recipes.add(Recipe(1,"Galleta de chispas de chocolate", "Gabriel", ingredients, R.drawable.galleta4))
        recipes.add(Recipe(2,"Galleta de navidad", "Andy", ingredients, R.drawable.galleta1))
        recipes.add(Recipe(3,"Galleta de naranja", "Cato", ingredients, R.drawable.galleta2))
        recipes.add(Recipe(4,"Galleta de avena", "Gabriel", ingredients, R.drawable.galleta3))
        recipes.add(Recipe(5,"Galleta de lucuma  ", "Gabriel", ingredients, R.drawable.galleta4))

        return recipes
    }
}