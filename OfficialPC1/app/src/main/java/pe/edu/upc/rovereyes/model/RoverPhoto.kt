package pe.edu.upc.rovereyes.model

import android.os.Bundle

data class RoverPhoto(val img_src : String?= "",
                      val earth_date : String? = "",
                      val id: String?="",
                      val  sol: String?=""){


    companion object {
        fun fromBundle(bundle: Bundle):RoverPhoto{
            val roverPhoto = RoverPhoto(
                    bundle.getString("img_src"),
                    bundle.getString("earth_date"),
                    bundle.getString("id"),
                    bundle.getString("sol")
            )
            return roverPhoto
        }
    }

    fun toBundle() : Bundle{
        val bundle = Bundle()
        with(bundle){
            putString("sol",sol)
            putString("id",id)
            putString("img_src",img_src)
            putString("earth_date",earth_date)
        }
        return bundle
    }
}