package br.com.igorbag.meuprimeiroappdio

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AlertDialog
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

        viewbinding.textoLeitura.setMovementMethod(ScrollingMovementMethod())

        viewbinding.btSelecionaIdioma.setOnClickListener {
            showIdioma(this)
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

    private fun showIdioma(contextTela : Context){

        val items: Array<String> = resources.getStringArray(R.array.lista_idiomas)

        if(items.size>0) {
            val builder = AlertDialog.Builder(contextTela!!)
            builder.setTitle("Filtros criados")

            var checkedItem = 0

            builder.setSingleChoiceItems(items, checkedItem,
                DialogInterface.OnClickListener { dialog, which ->
                    checkedItem = which
                }
            )

            builder.setPositiveButton("OK") { dialog, which ->
                when(checkedItem){
                    0 -> selecionarIdioma("pt",this@MainActivity)
                    1 -> selecionarIdioma("en",this@MainActivity)
                    2 -> selecionarIdioma("es",this@MainActivity)
                }
            }

            builder.setNegativeButton("Cancel", null)

            val dialog = builder.create()
            dialog.setCanceledOnTouchOutside(false);

            dialog.show()

        }
    }

}