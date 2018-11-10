package pe.edu.upc.catchup.viewcontrollers.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.error.ANError
import kotlinx.android.synthetic.main.fragment_sources.view.*
import pe.edu.upc.catchup.R
import pe.edu.upc.catchup.models.Source
import pe.edu.upc.catchup.network.NewsApi
import pe.edu.upc.catchup.network.SourcesResponse
import pe.edu.upc.catchup.viewcontrollers.adapters.SourcesAdapter
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 *
 */
class SourcesFragment : Fragment() {
    private lateinit var sourcesRecyclerView: RecyclerView
    private lateinit var sourcesAdapter: SourcesAdapter
    private lateinit var sourcesLayoutManager: RecyclerView.LayoutManager
    private var sources = ArrayList<Source>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_sources, container, false)
        sourcesRecyclerView = view.sourcesRecyclerView
        sourcesAdapter = SourcesAdapter(sources, view.context)
        sourcesLayoutManager = GridLayoutManager(view.context, 2)
        sourcesRecyclerView.adapter = sourcesAdapter
        sourcesRecyclerView.layoutManager = sourcesLayoutManager

        NewsApi.requestSources(getString(R.string.news_api_key),
                { response -> handleResponse(response) },
                { error -> handleError(error) }
        )
        return view
    }

    private fun handleResponse(response: SourcesResponse?) {
        if ("error".equals(response!!.status, true)) {
            Log.d(NewsApi.tag, response.message)
            return
        }
        sources = response.sources!!
        Log.d(NewsApi.tag, "Found ${sources.size} sources")
        sourcesAdapter.sources = sources
        sourcesAdapter.notifyDataSetChanged()
    }

    private fun handleError(anError: ANError?) {
        Log.d(tag, anError!!.message)
    }

}
