package pe.edu.ulima.pm.cookiemaker

import android.app.PendingIntent.getActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.cookiemaker.adapter.RecipesListAdapter
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
    private var recipesList: ArrayList<Recipe>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Recetas"
        onBackPressedDispatcher.addCallback(this, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                when {
                    fragments[0].isVisible -> {
                        title = "Cookie Maker"
                    }
                    fragments[1].isVisible -> {
                        title = "Nueva Receta"
                    }
                    fragments[2].isVisible -> {
                        title = "Recetas"
                    }
                    fragments[3].isVisible -> {
                        title = "Recetas"
                    }
                    else -> {
                        title = "Cookie Maker"
                    }
                }
                if(fragments[0].isVisible){
                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    val bundle = Bundle()
                    bundle.putSerializable("recipeMain",recipeMain.getRecipesArray())
                    intent.putExtra("data",bundle)
                    startActivity(intent)
                }else{
                    isEnabled = false
                    onBackPressed()
                }
            }
        })

        setContentView(R.layout.activity_main)

        // Recibo de data
        val intentData: Bundle? = intent.getBundleExtra("data")
        nameUser = intentData?.getString("name")
        recipesList = intentData?.getSerializable("recipeMain") as? ArrayList<Recipe>
        println("Main:"+recipesList?.size)
        if (recipesList != null) {
            recipeMain.setRecipeArray(recipesList!!)
        }
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
        setTitle("Vista Receta")
        val fragment = fragments[3]
        var args: Bundle = Bundle()
        args.putSerializable("recipe", recipe)
        fragment.arguments = args

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent, fragment).addToBackStack("recipeList")

        ft.commit()
    }

    override fun onIngredientsClick(ingredients: ArrayList<Ingredient>, name:String){
        setTitle("Agregar Ingrediente")
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
        setTitle("Nueva Receta")
        val fragment = fragments[2]
        val args = Bundle()
        args.putSerializable("ingredients",null)
        args.putSerializable("nameRecipe","")
        fragment.arguments = args
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent, fragment).addToBackStack("recipeList")
        ft.commit()
    }

    override fun onIngredient(ingredients: ArrayList<Ingredient>, name:String) {
        setTitle("Nueva Receta")
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
        setTitle("Recetas")
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