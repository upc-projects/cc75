package pe.edu.upc.catchup.models

import android.os.Bundle
import pe.edu.upc.catchup.network.LogoApi

data class Source(
        val id: String?,
        val name: String?,
        val description: String? = "",
        val url: String? = "",
        val category: String? = "",
        val language: String? = "",
        val country: String? = "") {

    companion object {
        fun from(bundle: Bundle) : Source {
            return Source(
                    bundle.getString("id"),
                    bundle.getString("name"),
                    bundle.getString("description"),
                    bundle.getString("url"),
                    bundle.getString("category"),
                    bundle.getString("language"),
                    bundle.getString("country")
            )

        }
    }
    fun urlToLogo() : String = LogoApi.urlToLogo(url!!)

    fun toBundle() : Bundle {
        val bundle = Bundle()
        with (bundle) {
            putString("id", id)
            putString("name", name)
            putString("description", description)
            putString("url", url)
            putString("category", category)
            putString("language", language)
            putString("country", country)
        }
        return bundle
    }

    fun isBookmarked() : Boolean {
        return Bookmark.listFor(id!!).isNotEmpty()
    }

    fun setBookmarked(isBookmarked: Boolean) {
        if (isBookmarked == isBookmarked()) {
            return
        }
        if (isBookmarked) {
            val bookmark = Bookmark()
            bookmark.sourceId = id
            bookmark.save()
        } else {
            Bookmark.listFor(id!!).map { bookmark ->
                bookmark.delete()  }
        }

    }

}
