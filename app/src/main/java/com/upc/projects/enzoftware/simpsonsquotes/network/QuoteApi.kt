package com.upc.projects.enzoftware.simpsonsquotes.network

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError

class QuoteApi{

    companion object {

        fun requestQuotes(responseHandler : (QuoteResponse?) -> Unit , errorHandler : (ANError?) -> Unit){
            AndroidNetworking.get()
        }

    }
}