package pe.edu.ulima.pm.cookiemaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.cookiemaker.fragments.IngredientsFragment
import pe.edu.ulima.pm.cookiemaker.fragments.RecipesFragment

class MainActivity : AppCompatActivity() {

    private val fragments = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentData: Bundle? = intent.getBundleExtra("data")

        println(intentData?.getString("name"))

        fragments.add(RecipesFragment())
        fragments.add(IngredientsFragment())

        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.flaContent, fragments[1])
        ft.commit()
    }

    // Para probar
    private fun changeIngredients(){
        println("Ingredientes")
        val fragment = fragments[1]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flaContent,fragment)
        ft.commit()
    }
}