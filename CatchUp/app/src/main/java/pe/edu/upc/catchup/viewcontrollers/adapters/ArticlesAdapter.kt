package pe.edu.upc.catchup.viewcontrollers.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_article.view.*
import pe.edu.upc.catchup.R
import pe.edu.upc.catchup.models.Article
import pe.edu.upc.catchup.viewcontrollers.activities.ArticleActivity

class ArticlesAdapter(var articles: ArrayList<Article>, val context: Context): RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater
                .from(context)
                .inflate(R.layout.item_article, p0, false))
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles.get(position)
        holder.updateFrom(article)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val pictureImageView = view.pictureImageView
        val titleTextView = view.titleTextView
        val articleLayout = view.articleLayout

        fun updateFrom(article: Article) {
            titleTextView.text = article.title
            with(pictureImageView) {
                setDefaultImageResId(R.mipmap.ic_launcher)
                setErrorImageResId(R.mipmap.ic_launcher)
                setImageUrl(article.urlToImage)
            }
            articleLayout.setOnClickListener { view ->
                val context = view.context
                context.startActivity(
                        Intent(context,
                                ArticleActivity::class.java)
                                .putExtras(article.toBundle()))
            }
        }
    }
}

