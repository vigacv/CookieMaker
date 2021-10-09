package pe.edu.ulima.pm.cookiemaker.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import pe.edu.ulima.pm.cookiemaker.MainActivity
import pe.edu.ulima.pm.cookiemaker.R
import pe.edu.ulima.pm.cookiemaker.adapter.IngredientsListAdapter
import pe.edu.ulima.pm.cookiemaker.model.Ingredient
import pe.edu.ulima.pm.cookiemaker.model.IngredientsManager
import pe.edu.ulima.pm.cookiemaker.model.Recipe

class AddRecipeFragment: Fragment() {

    interface OnAddIngredientSelectedListener{
        fun onIngredientsClick(ingredients: ArrayList<Ingredient>, name:String)
        fun onSaveClick(ingredients: ArrayList<Ingredient>, name:String)
    }

    private var listener:OnAddIngredientSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnAddIngredientSelectedListener
    }

    private var ingredientsList: ArrayList<Ingredient>? = null
    private var nameRecipe: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ingredientsList = arguments?.getSerializable("ingredients") as? ArrayList<Ingredient>
        nameRecipe = arguments?.getSerializable("nameRecipe") as? String
        return inflater.inflate(R.layout.fragment_addrecipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eteRecipeName = view.findViewById<TextInputLayout>(R.id.eteRecipeName)
        val rviIngredients = view.findViewById<RecyclerView>(R.id.rviIngredients)

        if (ingredientsList != null) {
            eteRecipeName.editText?.setText(nameRecipe)
            rviIngredients.adapter = IngredientsListAdapter(IngredientsManager().getIngredientsRecipe(ingredientsList!!)){ingredient:Ingredient ->
            }
        }else{
            eteRecipeName.editText?.setText("")
        }

        view.findViewById<Button>(R.id.btnIngredients).setOnClickListener { _: View ->
            if (ingredientsList == null) {ingredientsList = ArrayList<Ingredient>()}
            listener?.onIngredientsClick(ingredientsList!!,eteRecipeName.editText?.text.toString())
        }

        view.findViewById<Button>(R.id.btnSave).setOnClickListener {_:View ->
            if ( ingredientsList==null){
                Toast.makeText(getActivity(), "Debe ingresar por lo menos un ingrediente", Toast.LENGTH_LONG).show()
            }else if(eteRecipeName?.editText?.text!!.isBlank()) {
                Toast.makeText(getActivity(), "Debe ingresar el nombre de la receta", Toast.LENGTH_LONG).show()
            }else{
                listener?.onSaveClick(ingredientsList!!,eteRecipeName.editText?.text.toString())
            }
        }

    }
}