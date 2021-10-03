package pe.edu.ulima.pm.cookiemaker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.cookiemaker.R
import pe.edu.ulima.pm.cookiemaker.adapter.RecipesListAdapter.ViewHolder
import pe.edu.ulima.pm.cookiemaker.model.Recipe

class RecipesListAdapter(private val recipeList: List<Recipe>): RecyclerView.Adapter<RecipesListAdapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val iviRecipe: ImageView
        val tviRecipeName: TextView
        val tviRecipeUserName: TextView

        init{
            iviRecipe = view.findViewById(R.id.iviRecipe)
            tviRecipeName = view.findViewById(R.id.tviRecipeName)
            tviRecipeUserName = view.findViewById(R.id.tviRecipeUserName)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tviRecipeName.text = recipeList[position].name
        holder.tviRecipeUserName.text = recipeList[position].creator
        holder.iviRecipe.setImageResource(recipeList[position].imageId)
    }

    override fun getItemCount(): Int {
        return recipeList.count()
    }
}