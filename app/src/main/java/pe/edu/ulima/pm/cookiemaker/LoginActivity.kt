package pe.edu.ulima.pm.cookiemaker

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import pe.edu.ulima.pm.cookiemaker.model.Recipe
import pe.edu.ulima.pm.cookiemaker.model.RecipeManager
import kotlin.system.exitProcess

class LoginActivity: AppCompatActivity() {

    private var tinName: TextInputLayout? = null
    private var recipesList: ArrayList<Recipe>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title = "Cookie Maker"

        onBackPressedDispatcher

        onBackPressedDispatcher.addCallback(this, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity()
                exitProcess(0)
            }
        })

        tinName = findViewById(R.id.tinName)

        this.recipesList = intent.getBundleExtra("data")?.getSerializable("recipeMain") as? ArrayList<Recipe>

        findViewById<Button>(R.id.btnIngresar).setOnClickListener { _: View ->
            if ( tinName?.editText?.text!!.isBlank()){
                Toast.makeText(this, "Debe ingresar su nombre", Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(this, MainActivity::class.java)
                val bundle = Bundle()
                bundle.putString("name", tinName?.editText?.text.toString())
                println("Login:"+recipesList?.size)
                bundle.putSerializable("recipeMain",recipesList)
                intent.putExtra("data", bundle)
                startActivity(intent)
            }
        }
    }
}