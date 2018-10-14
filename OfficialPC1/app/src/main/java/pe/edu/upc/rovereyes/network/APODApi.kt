package pe.edu.upc.rovereyes.network

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import pe.edu.upc.rovereyes.model.APOD

class APODApi{
    companion object {

        val baseUrl = "https://api.nasa.gov/planetary/apod"
        val tag = "NASA APOD"

        fun requestAPOD(key: String, responseHandler : (APOD?) -> Unit, errorHandler : (ANError?) -> Unit){
            AndroidNetworking.get(APODApi.baseUrl)
                    .addQueryParameter("api_key",key)
                    .setPriority(Priority.IMMEDIATE)
                    .setTag(tag)
                    .build()
                    .getAsObject(APOD::class.java, object: ParsedRequestListener<APOD>{
                        override fun onResponse(response: APOD?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }
    }
}