package pe.edu.ulima.pm.cookiemaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.cookiemaker.fragments.AddRecipeFragment
import pe.edu.ulima.pm.cookiemaker.fragments.IngredientsFragment
import pe.edu.ulima.pm.cookiemaker.fragments.RecipesFragment
import pe.edu.ulima.pm.cookiemaker.fragments.ViewRecipeFragment
import pe.edu.ulima.pm.cookiemaker.model.Ingredient
import pe.edu.ulima.pm.cookiemaker.model.Recipe

class MainActivity : AppCompatActivity(),
    RecipesFragment.OnAddClicked,
    AddRecipeFragment.OnAddIngredientSelectedListener,
    IngredientsFragment.OnIngredientsSelectedListener {

    private val fragments = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentData: Bundle? = intent.getBundleExtra("data")

        println(intentData?.getString("name"))

        fragments.add(RecipesFragment()) //0
        fragments.add(IngredientsFragment()) //1
        fragments.add(AddRecipeFragment()) //2
        fragments.add(ViewRecipeFragment()) //3

        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.flaContent, fragments[0])
        ft.commit()
    }

    override fun onAddRecipeClick() {
        //Cambiar a fragment agregar recetas
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent, fragments[2])

        ft.commit()
    }

    override fun onRecipeItemClick(recipe: Recipe) {
        val fragment = fragments[3]
        var args: Bundle = Bundle()
        args.putSerializable("recipe", recipe)
        fragment.arguments = args

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent, fragment)

        ft.commit()
    }

    override fun onIngredientsClick(ingredients: ArrayList<Ingredient>){
        val fragment = fragments[1]
        val args = Bundle()
        args.putSerializable("ingredients", ingredients)
        fragment.arguments = args
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent,fragment)
        ft.commit()
    }

    override fun onIngredient() {
        println("-->")
    }

}