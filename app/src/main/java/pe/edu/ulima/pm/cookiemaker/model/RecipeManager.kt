package pe.edu.ulima.pm.cookiemaker.model

import pe.edu.ulima.pm.cookiemaker.R
import java.io.Serializable

class RecipeManager {

    private var recipesMain = arrayListOf<Recipe>()
    private var imgList = arrayListOf<Int>()

    init{
        imgList.add(R.drawable.galleta1)
        imgList.add(R.drawable.galleta2)
        imgList.add(R.drawable.galleta3)
        imgList.add(R.drawable.galleta4)
        val ingredientsDefault1: List<Ingredient> = arrayListOf(Ingredient(1,"leche"), Ingredient(2,"harina"), Ingredient(3,"huevos"))
        val ingredientsDefault2: List<Ingredient> = arrayListOf(Ingredient(1,"leche"), Ingredient(2,"harina"))
        addRecipe(ingredientsDefault1, "Galleta de chispas de chocolate","Gabriel")
        addRecipe(ingredientsDefault2, "Galleta de navidad","Andy")

    }

    fun getRecipesArray(): ArrayList<Recipe>{
        return recipesMain
    }

    fun setRecipeArray(newList:ArrayList<Recipe>){
        recipesMain = newList
    }

    fun addRecipe(ingredients:List<Ingredient>, nameRecipe:String, nameUser:String){
        val newId = recipesMain.size.toLong()+1
        val imgRand = (0 until 4).random()
        recipesMain.add(Recipe(
            newId,
            nameRecipe,
            nameUser,
            ingredients,
            imgList[imgRand]
        ))
    }
}