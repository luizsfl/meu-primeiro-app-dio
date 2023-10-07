package br.com.igorbag.meuprimeiroappdio

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.com.igorbag.meuprimeiroappdio.databinding.ActivityMainBinding
import java.util.Locale


//O desafio sera criar uma valor dentro do string.xml
// E trocar o texto do xml e tornar internacional (Ingles, Espanhol, etc...)

class MainActivity : AppCompatActivity() {

    private val viewbinding  by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewbinding.root)

        getSupportActionBar()?.hide();

        val arrayAdapterLinguagens = ArrayAdapter.createFromResource(this,R.array.lista_idiomas,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)

        viewbinding.sppinerLingugagens.adapter = arrayAdapterLinguagens

        viewbinding.textoLeitura.setMovementMethod(ScrollingMovementMethod())
        0
        viewbinding.sppinerLingugagens.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0 -> selecionarIdioma("pt",this@MainActivity)
                    1 -> selecionarIdioma("en",this@MainActivity)
                    2 -> selecionarIdioma("es",this@MainActivity)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }

    private fun selecionarIdioma(idioma:String,context : MainActivity){

        val localDefault = Locale.getDefault()
        if(!localDefault.toString().equals(idioma)){

            val localidade = Locale(idioma)
            Locale.setDefault(localidade)

            val resorce = context.resources

            val configuration = resorce.configuration
            configuration.setLocale(localidade)

            resorce.updateConfiguration(configuration, context.getResources().getDisplayMetrics());

            this.recreate()
        }

    }
}