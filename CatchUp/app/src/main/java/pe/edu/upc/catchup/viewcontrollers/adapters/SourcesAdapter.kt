package pe.edu.upc.catchup.viewcontrollers.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_source.view.*
import pe.edu.upc.catchup.R
import pe.edu.upc.catchup.models.Source
import pe.edu.upc.catchup.viewcontrollers.activities.SourceActivity

class SourcesAdapter(var sources: List<Source>, val context: Context):
RecyclerView.Adapter<SourcesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater
                .from(context)
                .inflate(R.layout.item_source, p0, false))
    }

    override fun getItemCount(): Int {
        return sources.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val source = sources.get(position)
        holder.updateFrom(source)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val logoImageView = view.logoImageView
        val nameTextView = view.nameTextView
        val sourceLayout = view.sourceLayout
        val bookmarkImageView = view.bookmarkImageView

        fun updateFrom(source: Source) {
            logoImageView.setDefaultImageResId(R.mipmap.ic_launcher)
            logoImageView.setErrorImageResId(R.mipmap.ic_launcher)
            logoImageView.setImageUrl(source.urlToLogo())
            Log.d("CatchUp", source.urlToLogo())
            nameTextView.text = source.name
            sourceLayout.setOnClickListener { view ->
                val context = view.context
                context.startActivity(Intent(context, SourceActivity::class.java)
                        .putExtras(source.toBundle()))
            }
            bookmarkImageView.setImageResource(
                    if (source.isBookmarked()) {
                        R.drawable.ic_bookmark_black_24dp
                    } else {
                        R.drawable.ic_bookmark_border_black_24dp
                    }
            )
        }

    }
}

