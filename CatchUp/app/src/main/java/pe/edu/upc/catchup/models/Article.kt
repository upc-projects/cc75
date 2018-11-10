package pe.edu.upc.catchup.models

import android.os.Bundle

data class Article(
        val source: Source,
        val author: String?,
        val title: String?,
        val description: String?,
        val url: String?,
        val urlToImage: String?,
        val publishedAt: String?,
        val content: String?) {
    companion object {
        fun from(bundle: Bundle) : Article {
           return Article(
                   Source.from(bundle.getBundle("source")),
                   bundle.getString("author"),
                   bundle.getString("title"),
                   bundle.getString("description"),
                   bundle.getString("url"),
                   bundle.getString("urlToImage"),
                   bundle.getString("publishedAt"),
                   bundle.getString("content")
           )
        }
    }

    fun toBundle() : Bundle {
        val bundle = Bundle()
        with(bundle) {
            putBundle("source", source.toBundle())
            putString("author", author)
            putString("title", title)
            putString("description", description)
            putString("url", url)
            putString("urlToImage", urlToImage)
            putString("publishedAt", publishedAt)
            putString("content", content)
        }
        return bundle
    }
}