package com.upc.projects.enzoftware.simpsonsquotes.network

import com.upc.projects.enzoftware.simpsonsquotes.model.Quote

class QuoteResponse(
    val status : String? = "",
    val quotes : ArrayList<Quote>? = ArrayList())
