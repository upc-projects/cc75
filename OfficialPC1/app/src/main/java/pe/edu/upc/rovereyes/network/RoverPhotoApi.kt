package pe.edu.upc.rovereyes.network

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import pe.edu.upc.rovereyes.model.APOD
import pe.edu.upc.rovereyes.model.RoverPhoto

class RoverPhotoApi{
    companion object {

        val baseUrl = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos"
        val tag = "NASA ROVER"

        fun requestRover(key: String, responseHandler : (RovertPhotoResponse?) -> Unit, errorHandler : (ANError?) -> Unit){
            AndroidNetworking.get(RoverPhotoApi.baseUrl)
                    .addQueryParameter("sol","1")
                    .addQueryParameter("api_key",key)
                    .setPriority(Priority.IMMEDIATE)
                    .setTag(tag)
                    .build()
                    .getAsObject(RovertPhotoResponse::class.java, object: ParsedRequestListener<RovertPhotoResponse> {
                        override fun onResponse(response: RovertPhotoResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }
    }
}