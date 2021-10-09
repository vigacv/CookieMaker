package pe.edu.ulima.pm.cookiemaker.model

import java.io.Serializable

class Recipe(val id: Long,
             val name: String,
             val creator: String,
             val ingredients: List<Ingredient>,
             val imageId: Int):Serializable{
 }