package pe.edu.upc.catchup.viewcontrollers.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import pe.edu.upc.catchup.R

import kotlinx.android.synthetic.main.activity_source.*
import kotlinx.android.synthetic.main.content_source.*
import pe.edu.upc.catchup.models.Source

class SourceActivity : AppCompatActivity() {
    var source: Source? = null
    var isBookmarked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_source)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent = intent?: return
        source =  Source.from(intent.extras)
        isBookmarked = source!!.isBookmarked()
        with(logoImageView) {
            setDefaultImageResId(R.mipmap.ic_launcher)
            setErrorImageResId(R.mipmap.ic_launcher)
            setImageUrl(source!!.urlToLogo())
        }
        nameTextView.text = source!!.name
        descriptionTextView.text = source!!.description
        bookmarkImageButton.setOnClickListener { view ->
            isBookmarked = !isBookmarked
            source!!.setBookmarked(isBookmarked)
            updateBookmarkImage()
        }
        updateBookmarkImage()
    }

    fun updateBookmarkImage() {
        bookmarkImageButton.setImageResource(
                if (isBookmarked) {
                    R.drawable.ic_bookmark_black_24dp
                } else {
                    R.drawable.ic_bookmark_border_black_24dp
                }
        )
    }



}
