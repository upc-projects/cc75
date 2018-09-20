package com.upc.projects.enzoftware.simpsonsquotes.ui.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.upc.projects.enzoftware.simpsonsquotes.R
import com.upc.projects.enzoftware.simpsonsquotes.model.Quote
import com.upc.projects.enzoftware.simpsonsquotes.ui.QuoteDetailActivity
import kotlinx.android.synthetic.main.item_quote.view.*

class QuoteAdapter(val context: Context, var quotes: ArrayList<Quote>): RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        return QuoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_quote, parent, false))
    }

    override fun getItemCount(): Int = quotes.size

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = quotes[position]
        holder.updateFrom(quote)
    }

    class QuoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val characterImage = view.characterImage!!
        val quoteText = view.quoteTextView!!
        val layoutQuote = view.layout_quote!!

        fun updateFrom(quote: Quote){
            quoteText.text = quote.quote
            characterImage.setDefaultImageResId(R.mipmap.ic_launcher)
            characterImage.setErrorImageResId(R.mipmap.ic_launcher)
            characterImage.setImageUrl(quote.image)

            layoutQuote.setOnClickListener {
                val context = it.context
                context.startActivity(Intent(it.context, QuoteDetailActivity::class.java)
                        .putExtras(quote.toBundle()))

            }


        }
    }
}