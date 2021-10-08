package pe.edu.ulima.pm.cookiemaker

import android.app.PendingIntent.getActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import pe.edu.ulima.pm.cookiemaker.fragments.AddRecipeFragment
import pe.edu.ulima.pm.cookiemaker.fragments.IngredientsFragment
import pe.edu.ulima.pm.cookiemaker.fragments.RecipesFragment
import pe.edu.ulima.pm.cookiemaker.fragments.ViewRecipeFragment
import pe.edu.ulima.pm.cookiemaker.model.Ingredient
import pe.edu.ulima.pm.cookiemaker.model.Recipe
import pe.edu.ulima.pm.cookiemaker.model.RecipeManager

class MainActivity : AppCompatActivity(),
    RecipesFragment.OnAddClicked,
    AddRecipeFragment.OnAddIngredientSelectedListener,
    IngredientsFragment.OnIngredientsSelectedListener {

    private val fragments = mutableListOf<Fragment>()
    private var nameUser:String? = null
    private var recipeMain = RecipeManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentData: Bundle? = intent.getBundleExtra("data")
        nameUser = intentData?.getString("name")
        println(nameUser)

        fragments.add(RecipesFragment()) //0
        fragments.add(IngredientsFragment()) //1
        fragments.add(AddRecipeFragment()) //2
        fragments.add(ViewRecipeFragment()) //3

        val fragment = fragments[0]
        val args = Bundle()
        args.putSerializable("recipeMain", recipeMain.getRecipesArray())
        fragment.arguments = args
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent,fragment)
        ft.commit()
    }

    override fun onRecipeItemClick(recipe: Recipe) {
        val fragment = fragments[3]
        var args: Bundle = Bundle()
        args.putSerializable("recipe", recipe)
        fragment.arguments = args

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent, fragment).addToBackStack("recipeList")

        ft.commit()
    }

    override fun onIngredientsClick(ingredients: ArrayList<Ingredient>, name:String){
        val fragment = fragments[1]
        val args = Bundle()
        args.putSerializable("ingredients", ingredients)
        args.putSerializable("nameRecipe", name)
        fragment.arguments = args
        val ft = supportFragmentManager.beginTransaction().addToBackStack("addRecipeToIngredients")
        ft.replace(R.id.flaContent,fragment)
        ft.commit()
    }

    override fun onAddRecipeClick() {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent, fragments[2]).addToBackStack("recipeList")

        ft.commit()
    }

    override fun onIngredient(ingredients: ArrayList<Ingredient>, name:String) {
        if(supportFragmentManager.backStackEntryCount>1){
            supportFragmentManager.popBackStack()
        }
        val fragment = fragments[2]
        val args = Bundle()
        args.putSerializable("ingredients", ingredients)
        args.putSerializable("nameRecipe", name)
        fragment.arguments = args
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent,fragment)
        ft.commit()
    }

    override fun onSaveClick(ingredients: ArrayList<Ingredient>, name:String) {

        val fm: FragmentManager = supportFragmentManager
        for (i in 0 until fm.backStackEntryCount) {
            fm.popBackStack()
        }

        recipeMain.addRecipe(ingredients,name,nameUser!!)
        val fragment = fragments[0]
        val args = Bundle()
        args.putSerializable("recipeMain", recipeMain.getRecipesArray())
        fragment.arguments = args
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent,fragment)
        ft.commit()
    }

}