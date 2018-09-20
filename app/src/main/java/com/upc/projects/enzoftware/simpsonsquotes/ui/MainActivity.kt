package com.upc.projects.enzoftware.simpsonsquotes.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.androidnetworking.error.ANError
import com.upc.projects.enzoftware.simpsonsquotes.R
import com.upc.projects.enzoftware.simpsonsquotes.model.Quote
import com.upc.projects.enzoftware.simpsonsquotes.network.QuoteApi
import com.upc.projects.enzoftware.simpsonsquotes.network.QuoteResponse
import com.upc.projects.enzoftware.simpsonsquotes.ui.adapter.QuoteAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val tag = "SIMPSONS QUOTE"
    private var quotes : ArrayList<Quote> = ArrayList()
    private lateinit var quoteAdapter: QuoteAdapter
    private lateinit var quoteRecyclerView: RecyclerView
    private lateinit var quoteLayoutManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quoteRecyclerView = findViewById(R.id.recyclerView)
        quoteAdapter = QuoteAdapter(this, quotes)
        quoteLayoutManager = GridLayoutManager(this,1)
        quoteRecyclerView.adapter = quoteAdapter
        quoteRecyclerView.layoutManager = quoteLayoutManager

        QuoteApi.requestArrayQuotes(
                { response -> responseHandler(response) },
                { anError -> errorHandler(anError) }
        )
    }


    fun responseHandler(listQuotes: List<Quote>?){

        this.quotes = ArrayList(listQuotes)
        quoteAdapter.quotes = this.quotes
        quoteAdapter.notifyDataSetChanged()

    }

    fun errorHandler(anError: ANError?){
        Log.d(tag,anError!!.message)
    }

}
