package pe.edu.ulima.pm.cookiemaker.model

class IngredientsManager {
    fun getIngredients():List<Ingredient> {
        val listIngredients = arrayListOf<Ingredient>()
        listIngredients.add(Ingredient(1, "Leche"))
        listIngredients.add(Ingredient(2, "Galletas"))
        listIngredients.add(Ingredient(3, "Limón"))
        listIngredients.add(Ingredient(4, "Mantequilla"))
        listIngredients.add(Ingredient(5, "Naranja"))
        listIngredients.add(Ingredient(6, "Castañas"))
        return listIngredients
    }

    fun getIngredientsRecipe():List<Ingredient> {
        val listIngredients = arrayListOf<Ingredient>()
        listIngredients.add(Ingredient(1, "Leche"))
        listIngredients.add(Ingredient(2, "Galletas"))
        return listIngredients
    }
}