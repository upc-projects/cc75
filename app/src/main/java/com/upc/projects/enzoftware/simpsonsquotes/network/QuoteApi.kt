package com.upc.projects.enzoftware.simpsonsquotes.network

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.upc.projects.enzoftware.simpsonsquotes.model.Quote

class QuoteApi{



    companion object {

        val baseUrl = "https://thesimpsonsquoteapi.glitch.me/quotes"

        fun requestArrayQuotes(responseHandler : (List<Quote>?) -> Unit , errorHandler : (ANError?) -> Unit){
            AndroidNetworking.get(QuoteApi.baseUrl)
                    .setPriority(Priority.HIGH)
                    .addQueryParameter("count","5")
                    .setTag("simpsons 2")
                    .build()
                    .getAsObjectList(Quote::class.java, object : ParsedRequestListener<List<Quote>>{
                        override fun onResponse(response: List<Quote>?) {
                            Log.i("SIZE",response!!.size.toString())
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }

    }
}