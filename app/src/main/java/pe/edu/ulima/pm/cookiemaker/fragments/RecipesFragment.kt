package pe.edu.ulima.pm.cookiemaker.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.cookiemaker.R
import pe.edu.ulima.pm.cookiemaker.adapter.RecipesListAdapter
import pe.edu.ulima.pm.cookiemaker.model.Ingredient
import pe.edu.ulima.pm.cookiemaker.model.Recipe
import pe.edu.ulima.pm.cookiemaker.model.RecipeManager

class RecipesFragment(): Fragment(){

    interface OnAddClicked{
        fun onAddRecipeClick()
        fun onRecipeItemClick(recipe: Recipe)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as OnAddClicked
        if(listener == null){
            Log.e("RecipesFragment", "Activity no implementa la interface")
        }
    }

    private var recipesList: ArrayList<Recipe>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recipesList = arguments?.getSerializable("recipeMain") as? ArrayList<Recipe>
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    private var listener: OnAddClicked? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rviRecipes = view.findViewById<RecyclerView>(R.id.rviRecipes)
        val adapter = RecipesListAdapter(recipesList!!)
        rviRecipes.adapter = adapter

        adapter.setOnItemClickListener(object: RecipesListAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                listener?.onRecipeItemClick(adapter.recipeList[position])
            }
        })

        view.findViewById<Button>(R.id.btnAgregar).setOnClickListener{_: View ->
            listener?.onAddRecipeClick()
        }
    }


}
