package pe.edu.ulima.pm.cookiemaker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import pe.edu.ulima.pm.cookiemaker.model.Recipe
import pe.edu.ulima.pm.cookiemaker.model.RecipeManager

class LoginActivity: AppCompatActivity() {

    private var tinName: TextInputLayout? = null
    private var recipesList: ArrayList<Recipe>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tinName = findViewById(R.id.tinName)

        this.recipesList = intent.getBundleExtra("data")?.getSerializable("recipeMain") as? ArrayList<Recipe>

        findViewById<Button>(R.id.btnIngresar).setOnClickListener { _: View ->
            val intent = Intent(this, MainActivity::class.java)
            val bundle = Bundle()
            bundle.putString("name", tinName?.editText?.text.toString())
            println("Login:"+recipesList?.size)
            bundle.putSerializable("recipeMain",recipesList)
            intent.putExtra("data", bundle)
            startActivity(intent)
            finish()
        }
    }
}