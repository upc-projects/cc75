package com.upc.projects.enzoftware.simpsonsquotes.model

import android.os.Bundle

class Quote( val quote : String? = "",
             val character : String? = "",
             val image : String? = "",
             val characterDirection: String? = ""){

    companion object {

        fun from(bundle: Bundle) : Quote{
            val quote: Quote = Quote(
                    bundle.getString("quote"),
                    bundle.getString("character"),
                    bundle.getString("image"),
                    bundle.getString("characterDirection")
            )
            return quote
        }

    }

    fun toBundle():Bundle{
        val bundle = Bundle()

        with(bundle){
            putString("quote",quote)
            putString("character",character)
            putString("image",image)
            putString("characterDirection",characterDirection)
        }
        return bundle
    }



}