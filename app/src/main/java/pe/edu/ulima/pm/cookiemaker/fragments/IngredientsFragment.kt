package pe.edu.ulima.pm.cookiemaker.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.cookiemaker.R
import pe.edu.ulima.pm.cookiemaker.adapter.IngredientsListAdapter
import pe.edu.ulima.pm.cookiemaker.model.Ingredient
import pe.edu.ulima.pm.cookiemaker.model.IngredientsManager
import pe.edu.ulima.pm.cookiemaker.model.Recipe

class IngredientsFragment: Fragment() {


    interface OnIngredientsSelectedListener{
        fun onIngredient(ingredients: ArrayList<Ingredient>, name:String)
    }

    private var listener:OnIngredientsSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnIngredientsSelectedListener
    }

    private var ingredientsList: ArrayList<Ingredient>? = null
    private var nameRecipe: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ingredientsList = arguments?.getSerializable("ingredients") as ArrayList<Ingredient>
        nameRecipe = arguments?.getSerializable("nameRecipe") as String
        return inflater.inflate(R.layout.fragment_ingredients, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rviIngredients = view.findViewById<RecyclerView>(R.id.rviIngredients)
        rviIngredients.adapter = IngredientsListAdapter(IngredientsManager().getIngredients()){ingredient: Ingredient ->
            //val tviIngredient = view.findViewById<TextView>(R.id.tviIngredient)
            Log.i("Ingredients Fragment",ingredient.name)
            IngredientsManager().addIngredientToRecipe(ingredientsList!!, ingredient.name)
            listener?.onIngredient(ingredientsList!!, nameRecipe!!)
        }
    }
}