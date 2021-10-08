package pe.edu.ulima.pm.cookiemaker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.cookiemaker.R
import pe.edu.ulima.pm.cookiemaker.model.Ingredient
import pe.edu.ulima.pm.cookiemaker.model.Recipe

class IngredientsListAdapter(private val ingredientsList:List<Ingredient>,
                             private val listener: (recipe: Ingredient)->Unit) : RecyclerView.Adapter<IngredientsListAdapter.ViewHolder>() {

    class ViewHolder(view: View,
                    val listener:(Ingredient) -> Unit,
                    val ingredientsList:List<Ingredient>):RecyclerView.ViewHolder(view), View.OnClickListener {

        val tviIngredient: TextView

        init{
            tviIngredient = view.findViewById(R.id.tviIngredient)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener(ingredientsList[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredients, parent, false)
        val viewHolder = ViewHolder(view, listener, ingredientsList)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tviIngredient.text = ingredientsList[position].name
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }
}