package pe.edu.upc.catchup.network

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import pe.edu.upc.catchup.R

class NewsApi {
    companion object {
        private val baseUrl = "https://newsapi.org"
        val topHeadlinesUrl = "$baseUrl/v2/top-headlines"
        val everythingUrl = "$baseUrl/v2/everything"
        val sourcesUrl = "$baseUrl/v2/sources"
        val tag = "CatchUp"

        fun requestHeadlines(key: String, responseHandler: (ArticlesResponse?) -> Unit, errorHandler: (ANError?) -> Unit  ) {
            AndroidNetworking.get(NewsApi.topHeadlinesUrl)
                    .addQueryParameter("apiKey", key)
                    .addQueryParameter("country", "us")
                    .setPriority(Priority.LOW)
                    .setTag(tag)
                    .build()
                    .getAsObject(ArticlesResponse::class.java, object : ParsedRequestListener<ArticlesResponse> {
                        override fun onResponse(response: ArticlesResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }

                    })
        }
        fun requestEverythingFromSources(key: String, sourceIds: String, responseHandler: (ArticlesResponse?) -> Unit, errorHandler: (ANError?) -> Unit  ) {
            AndroidNetworking.get(NewsApi.everythingUrl)
                    .addQueryParameter("apiKey", key)
                    .addQueryParameter("sources", sourceIds)
                    .setPriority(Priority.LOW)
                    .setTag(tag)
                    .build()
                    .getAsObject(ArticlesResponse::class.java, object : ParsedRequestListener<ArticlesResponse> {
                        override fun onResponse(response: ArticlesResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }

                    })
        }
        fun requestSources(key: String, responseHandler: (SourcesResponse?) -> Unit, errorHandler: (ANError?) -> Unit  ) {
            AndroidNetworking.get(NewsApi.sourcesUrl)
                    .addQueryParameter("apiKey", key)
                    .setPriority(Priority.LOW)
                    .setTag(tag)
                    .build()
                    .getAsObject(SourcesResponse::class.java, object : ParsedRequestListener<SourcesResponse> {
                        override fun onResponse(response: SourcesResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })

        }
    }

}