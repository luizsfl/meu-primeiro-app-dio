package br.com.igorbag.meuprimeiroappdio

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import br.com.igorbag.meuprimeiroappdio.databinding.ActivityConfiguraIdiomaBinding
import java.util.Locale

class ConfiguraIdioma : AppCompatActivity() {

    private val viewbinding  by lazy { ActivityConfiguraIdiomaBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewbinding.root)

        val arrayAdapterLinguagens = ArrayAdapter.createFromResource(this,R.array.lista_idiomas,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)

        viewbinding.sppinerLingugagens.adapter = arrayAdapterLinguagens


        viewbinding.sppinerLingugagens.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0 -> selecionarIdioma(Locale.getDefault().toString(),this@ConfiguraIdioma)
                    1 -> selecionarIdioma("pt",this@ConfiguraIdioma)
                    2 -> selecionarIdioma("en",this@ConfiguraIdioma)
                    3 -> selecionarIdioma("es",this@ConfiguraIdioma)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }

    private fun selecionarIdioma(idioma:String,context : Context){

        val localDefault = Locale.getDefault()
        if(!localDefault.toString().equals(idioma)){

            val localidade = Locale(idioma)
            Locale.setDefault(localidade)

            val resorce = context.resources

            val configuration = resorce.configuration
            configuration.setLocale(localidade)

            //Necessário porque o atual esta apresentando problemas : AppCompatDelegate.setApplicationLocales(LocaleListCompat)
            //https://issuetracker.google.com/issues/243457462?pli=1#comment2
            resorce.updateConfiguration(configuration, context.getResources().getDisplayMetrics());

            val intent = Intent(context.applicationContext, MainActivity::class.java)

            startActivity(intent)

        }

   }

}