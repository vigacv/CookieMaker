package pe.edu.ulima.pm.cookiemaker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.cookiemaker.R
import pe.edu.ulima.pm.cookiemaker.adapter.IngredientsListAdapter
import pe.edu.ulima.pm.cookiemaker.model.IngredientsManager

class AddRecipeFragment: Fragment() {
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
        rviIngredients.adapter = IngredientsListAdapter(IngredientsManager().getIngredientsRecipe())

    }
}