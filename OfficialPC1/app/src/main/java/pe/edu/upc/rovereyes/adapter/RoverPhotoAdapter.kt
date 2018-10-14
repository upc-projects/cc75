package pe.edu.upc.rovereyes.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_photo.view.*
import pe.edu.upc.rovereyes.AboutPhotoActivity
import pe.edu.upc.rovereyes.R
import pe.edu.upc.rovereyes.model.RoverPhoto

class RoverPhotoAdapter(val context: Context, var photos : ArrayList<RoverPhoto>):RecyclerView.Adapter<RoverPhotoAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_photo, p0, false))
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val photo = photos[p1]
        p0.updateFrom(photo)
    }


    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val itemPhoto = view.item_photo!!
        val itemlayout = view.item_layout!!

        fun updateFrom(roverPhoto: RoverPhoto){
            itemPhoto.setDefaultImageResId(R.mipmap.ic_launcher_round)
            itemPhoto.setErrorImageResId(R.mipmap.ic_launcher)
            itemPhoto.setImageUrl(roverPhoto.img_src)

            itemlayout.setOnClickListener {
                val context = it.context
                context.startActivity(Intent(it.context,AboutPhotoActivity::class.java)
                        .putExtras(roverPhoto.toBundle()))
            }

        }
    }
}