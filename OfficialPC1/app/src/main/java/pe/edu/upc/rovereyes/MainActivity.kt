package pe.edu.upc.rovereyes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.androidnetworking.error.ANError
import pe.edu.upc.rovereyes.adapter.RoverPhotoAdapter
import pe.edu.upc.rovereyes.model.RoverPhoto
import pe.edu.upc.rovereyes.network.RoverPhotoApi
import pe.edu.upc.rovereyes.network.RovertPhotoResponse

class MainActivity : AppCompatActivity() {

    var photos: ArrayList<RoverPhoto> = ArrayList()
    lateinit var recyclerviewphoto : RecyclerView
    lateinit var adapterphoto : RoverPhotoAdapter
    lateinit var layoutmanagerphoto : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerviewphoto = findViewById(R.id.recyclerView)
        photos.add(RoverPhoto("http://mars.jpl.nasa.gov/msl-raw-images/msss/00001/mcam/0001ML0000001000I1_DXXX.jpg", "2012-08-07"))
        photos.add(RoverPhoto("http://mars.jpl.nasa.gov/msl-raw-images/msss/00001/mcam/0001MR0000001000C0_DXXX.jpg", "2012-08-07"))
        photos.add(RoverPhoto("http://mars.jpl.nasa.gov/msl-raw-images/msss/00001/mcam/0001MR0000001000I1_DXXX.jpg", "2012-08-07"))
        adapterphoto = RoverPhotoAdapter(this, photos)
        layoutmanagerphoto = GridLayoutManager(this,2)
        recyclerviewphoto.adapter = adapterphoto
        recyclerviewphoto.layoutManager = layoutmanagerphoto

        RoverPhotoApi.requestRover(getString(R.string.nasa_api_key),
                {response -> handleResponse(response)},
                {anError -> handleError(anError) })

    }


    private fun handleResponse(response: RovertPhotoResponse?){
        photos = response!!.photos!!
        adapterphoto.photos = this.photos
        adapterphoto.notifyDataSetChanged()
    }


    fun handleError(anError: ANError?){
        Log.e("error tag", anError!!.message)
    }

}
