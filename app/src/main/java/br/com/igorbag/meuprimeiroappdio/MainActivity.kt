package br.com.igorbag.meuprimeiroappdio

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.igorbag.meuprimeiroappdio.databinding.ActivityMainBinding

//O desafio sera criar uma valor dentro do string.xml
// E trocar o texto do xml e tornar internacional (Ingles, Espanhol, etc...)

class MainActivity : AppCompatActivity() {

    private val viewbinding  by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewbinding.root)

        viewbinding.selecionarIdioma.setOnClickListener {
            val intent = Intent(this, ConfiguraIdioma::class.java)
            startActivity(intent)
        }
    }

}