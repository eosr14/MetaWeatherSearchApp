package com.eosr14.metaweathersearch.ui.main

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eosr14.metaweathersearch.R
import com.eosr14.metaweathersearch.common.base.BaseActivity
import com.eosr14.metaweathersearch.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainViewModelInterface {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(
            this@MainActivity,
            R.layout.activity_main
        ).apply {
            mainViewModel = MainViewModel(this@MainActivity)
            viewModel = mainViewModel
            bindView()
            lifecycleOwner = this@MainActivity
        }

    }

    private fun bindView() {
        recyclerview_main.run {
            recyclerview_main.run {
                layoutManager = LinearLayoutManager(context).apply { orientation = RecyclerView.VERTICAL }
            }
        }
    }

}
