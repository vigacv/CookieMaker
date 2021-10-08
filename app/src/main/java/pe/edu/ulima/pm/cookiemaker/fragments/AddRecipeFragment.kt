package pe.edu.ulima.pm.cookiemaker.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.cookiemaker.MainActivity
import pe.edu.ulima.pm.cookiemaker.R
import pe.edu.ulima.pm.cookiemaker.adapter.IngredientsListAdapter
import pe.edu.ulima.pm.cookiemaker.model.Ingredient
import pe.edu.ulima.pm.cookiemaker.model.IngredientsManager
import pe.edu.ulima.pm.cookiemaker.model.Recipe

class AddRecipeFragment: Fragment() {

    interface OnAddIngredientSelectedListener{
        fun onIngredientsClick(ingredients: ArrayList<Ingredient>)
    }

    private var listener:OnAddIngredientSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnAddIngredientSelectedListener
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_addrecipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rviIngredients = view.findViewById<RecyclerView>(R.id.rviIngredients)
        rviIngredients.adapter = IngredientsListAdapter(IngredientsManager().getIngredientsRecipe()){ ingredient: Ingredient ->
            Log.i("Ingredient Fragment", ingredient.name)
        }

        view.findViewById<Button>(R.id.btnIngredients).setOnClickListener { _: View ->
            val arrayIngredients = arrayListOf<Ingredient>()
            listener?.onIngredientsClick(arrayIngredients)
        }
        /*
        view.findViewById<Button>(R.id.btnIngredient).setOnClickListener { _: View ->
            Log.i("Add Recipe Fragment","OK")
        }
        */

    }
}