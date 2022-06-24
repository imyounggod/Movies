package com.test.movies.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.movies.R
import com.test.movies.presentation.main.util.RecyclerViewScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    private lateinit var recycler: RecyclerView
    private val filmsAdapter = FilmAdapter()
    private lateinit var loader: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recycler_films)
        loader = findViewById(R.id.loader)

        lifecycleScope.launchWhenCreated {
            mainViewModel.state.collect{
                if(it.error == null){
                    when(it.loading){
                        true ->{
                            loader.visibility = View.VISIBLE
                        }
                        false ->{
                            loader.visibility = View.GONE
                            if (it.listData.isNotEmpty()) {
                                filmsAdapter.addItems(it.listData.last().results)
                            }
                        }
                    }
                }else{
                    showError(it.error)
                }

            }
        }
        initViews()
    }

    private fun initViews() {
        recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = filmsAdapter

        recycler.addOnScrollListener(object : RecyclerViewScrollListener() {
            override fun onScrollToBottom() {
                mainViewModel.loadData()
            }
        })
    }

    private fun showError(error: String){
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
    }

}
