package com.example.settingsfragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.net.Uri
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class homeFragment : Fragment(), OnclickItem {

    val args : homeFragmentArgs by navArgs()
    lateinit var mAdapter: MyAdapter
    lateinit var ctx: Context

    var scountry: String? = ""
    var categories : String? = ""



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        Log.d("HomeFragment","HomeFragment onCreateView")


        val binding = inflater.inflate(R.layout.fragment_home, container, false)




        ctx = requireActivity().applicationContext


        binding.recyclerView.layoutManager = LinearLayoutManager(ctx)        //created a layout manager

        return binding

    }





    @SuppressLint("WrongConstant")
    fun ApiData() {    //to fetch news from api
        val url =
            "http://api.mediastack.com/v1/news?access_key=02022a91b1c662e381c56a896fa422a5&countries=$scountry&categories=$categories"
        Log.d("HomeFragment", url)

        val jsonRequest: String? = null
        val jsonObjectRequest = JsonObjectRequest(url, null,
            Response.Listener {
                Log.d("mainactivty", "array is $it")
                val newsJsonArray = it.getJSONArray("data")

                val newsArray = ArrayList<newsContent>()
                for (i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = newsContent(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("image")
                    )
                    if(newsJsonObject.getString("image") != "null") {
                        newsArray.add(news)
                        Log.d("image","${newsJsonObject.getString("image")}")
                    } //to eliminate news with no image
                }


                    mAdapter.updatedNews(newsArray)
                if(mAdapter.itemCount==0){
                    Oops.visibility = View.VISIBLE
                }


            },
            Response.ErrorListener {
                Log.d("MainActivity", "array is error $it")

            }
        )


//        val rq: RequestQueue = Volley.newRequestQueue(this)
//        rq.add(jsonObjectRequest)
        MySingleton.getInstance(ctx).addToRequestQueue(jsonObjectRequest)




    }



    override fun onClicked(item: newsContent) {   //to open the news url when clicked

        val builder = CustomTabsIntent.Builder();
        val customTabsIntent = builder.build();
        customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        customTabsIntent.launchUrl(ctx, Uri.parse(item.url));
    }




   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       categories="general"


        fab_settings.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)

        }
        loadSettings()
       Log.d("hi","country is $scountry")

            fab_filter.setOnClickListener {
              findNavController().navigate(R.id.action_homeFragment_to_filterFragment)

            }
       categories = args.cCategory
       Log.d("Home Fragment","catergory is $categories")
       mAdapter = MyAdapter(this)
       recyclerView.adapter=mAdapter
       ApiData()
       Log.d("HomeFragment", "ÄpiData()")
      /* mAdapter = MyAdapter(this)
       recyclerView.adapter=mAdapter */
       Log.d("adapter","newl updated")

   }


    private fun loadSettings() { //shared pref

        val sp: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val country: String? = sp.getString("country", "all")
        if(!(country.equals("all"))) {

            scountry = country.toString().substring(0, 2)
        }
    }

}



