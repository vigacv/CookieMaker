package pe.edu.ulima.pm.cookiemaker.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.cookiemaker.R
import pe.edu.ulima.pm.cookiemaker.adapter.IngredientsListAdapter
import pe.edu.ulima.pm.cookiemaker.model.IngredientsManager
import pe.edu.ulima.pm.cookiemaker.model.Recipe

class ViewRecipeFragment(): Fragment() {



    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    private var recipe: Recipe? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recipe = arguments?.getSerializable("recipe") as Recipe
        return inflater.inflate(R.layout.fragment_view_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tviRecipeDetailsTiltle = view.findViewById<TextView>(R.id.tviRecipeDetailsTiltle)
        val rviRecipeDetailsIngredients = view.findViewById<RecyclerView>(R.id.rviRecipeDetailsIngredients)
        tviRecipeDetailsTiltle.text = recipe?.name

        val adapter = recipe?.ingredients?.let { IngredientsListAdapter(it) }
        rviRecipeDetailsIngredients.adapter = adapter

    }
}