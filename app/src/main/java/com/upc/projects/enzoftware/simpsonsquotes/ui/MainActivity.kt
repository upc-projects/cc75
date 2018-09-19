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
import com.upc.projects.enzoftware.simpsonsquotes.network.QuoteResponse
import com.upc.projects.enzoftware.simpsonsquotes.ui.adapter.QuoteAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    val tag = "SIMPSONS QUOTE"
    lateinit var quotes : ArrayList<Quote>
    lateinit var quoteAdapter: QuoteAdapter
    lateinit var quoteRecyclerView: RecyclerView
    lateinit var quoteLayoutManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quoteRecyclerView = this.recyclerViewQuotes
        quoteAdapter = QuoteAdapter(this, quotes)
        quoteLayoutManager = GridLayoutManager(this,1)
        quoteRecyclerView.adapter = quoteAdapter
        quoteRecyclerView.layoutManager = quoteLayoutManager





        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun responseHandler(quoteResponse: QuoteResponse?){
        if ("error".equals(quoteResponse!!.status)){
            return
        }
        this.quotes = quoteResponse.quotes!!
        quoteAdapter.quotes = this.quotes
        quoteAdapter.notifyDataSetChanged()

    }

    fun errorHandler(anError: ANError){
        Log.d(tag,anError.message)
    }

}
