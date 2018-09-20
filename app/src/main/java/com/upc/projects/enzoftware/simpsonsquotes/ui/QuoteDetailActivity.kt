package com.upc.projects.enzoftware.simpsonsquotes.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.upc.projects.enzoftware.simpsonsquotes.R
import com.upc.projects.enzoftware.simpsonsquotes.model.Quote

import kotlinx.android.synthetic.main.activity_quote_detail.*
import kotlinx.android.synthetic.main.content_quote_detail.*

class QuoteDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent?:return
        val quote = Quote.from(intent.extras)

        with(detailQuoteImageView){
            setDefaultImageResId(R.mipmap.ic_launcher)
            setErrorImageResId(R.mipmap.ic_launcher_round)
            setImageUrl(quote.image)
        }

        characterDetail.text = quote.character
        characterDirectionDetail.text = quote.characterDirection
        quoteDetail.text = quote.quote

    }

}
