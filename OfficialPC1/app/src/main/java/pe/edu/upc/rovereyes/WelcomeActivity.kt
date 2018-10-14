package pe.edu.upc.rovereyes

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.androidnetworking.error.ANError
import com.androidnetworking.widget.ANImageView

import kotlinx.android.synthetic.main.activity_welcome.*
import pe.edu.upc.rovereyes.model.APOD
import pe.edu.upc.rovereyes.network.APODApi

class WelcomeActivity : AppCompatActivity() {

    var apod = APOD()
    lateinit var imagewelcome : ANImageView
    lateinit var titleWelcome : TextView
    lateinit var explanationWelcome : TextView
    lateinit var copyrightWelcome : TextView
    lateinit var dateWelcome : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        imagewelcome = findViewById(R.id.imageviewWelcome)
        titleWelcome = findViewById(R.id.titlewelcome)
        explanationWelcome = findViewById(R.id.explanationwelcome)
        copyrightWelcome = findViewById(R.id.copyrightwelcome)
        dateWelcome = findViewById(R.id.datewelcome)

        APODApi.requestAPOD(getString(R.string.nasa_api_key),
                {response -> handleResponse(response)},
                {anError -> handleError(anError) })

        layoutWelcome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }


    private fun handleResponse(resposne:APOD?){
        apod = resposne!!
        imagewelcome.setDefaultImageResId(R.mipmap.ic_launcher)
        imagewelcome.setErrorImageResId(R.mipmap.ic_launcher_round)
        imagewelcome.setImageUrl(apod.url)

        titleWelcome.text = apod.title
        explanationWelcome.text = apod.explanation
        copyrightWelcome.text = apod.copyright
        dateWelcome.text = apod.date
    }

    fun handleError(anError: ANError?){
        Log.e("error tag", anError!!.message)
    }


}
