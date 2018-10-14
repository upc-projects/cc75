package pe.edu.upc.rovereyes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_about_photo.*
import kotlinx.android.synthetic.main.content_about_photo.*
import pe.edu.upc.rovereyes.model.RoverPhoto

class AboutPhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_photo)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent?:return

        val photo = RoverPhoto.fromBundle(intent.extras)

        with(detailphoto){
            setDefaultImageResId(R.mipmap.ic_launcher)
            setErrorImageResId(R.mipmap.ic_launcher_round)
            setImageUrl(photo.img_src)
        }

        detaildate.text =  photo.earth_date
        detailsol.text = photo.sol
        detailid.text = photo.id

        // ezzzz
    }

}
