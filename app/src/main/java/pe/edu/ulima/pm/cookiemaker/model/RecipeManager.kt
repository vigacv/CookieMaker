package pe.edu.ulima.pm.cookiemaker.model

import pe.edu.ulima.pm.cookiemaker.R

class RecipeManager {

    private var recipesMain = arrayListOf<Recipe>()

    init{
        val ingredientsDefault1: List<Ingredient> = arrayListOf(Ingredient(1,"leche"), Ingredient(2,"harina"), Ingredient(3,"huevos"))
        val ingredientsDefault2: List<Ingredient> = arrayListOf(Ingredient(1,"leche"), Ingredient(2,"harina"))
        addRecipe(ingredientsDefault1, "Galleta de chispas de chocolate","Gabriel")
        addRecipe(ingredientsDefault2, "Galleta de navidad","Andy")
    }

    fun getRecipes(): List<Recipe>{
        return recipesMain
    }

    fun getRecipesArray(): ArrayList<Recipe>{
        return recipesMain
    }

    fun addRecipe(ingredients:List<Ingredient>, nameRecipe:String, nameUser:String){
        val newId = recipesMain.size.toLong()+1
        recipesMain.add(Recipe(
            newId,
            nameRecipe,
            nameUser,
            ingredients,
            R.drawable.galleta1
        ))
    }
}