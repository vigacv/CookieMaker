package pe.edu.ulima.pm.cookiemaker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class LoginActivity: AppCompatActivity() {

    private var tinName: TextInputLayout? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tinName = findViewById(R.id.tinName)

        val butIngresar = findViewById<Button>(R.id.btnIngresar)

        butIngresar.setOnClickListener { _: View ->
            val intent: Intent = Intent()
            intent.setClass(this, MainActivity::class.java)

            val bundle = Bundle()
            bundle.putString("name", tinName?.editText?.text.toString())

            intent.putExtra("data", bundle)

            startActivity(intent)
        }
    }
}