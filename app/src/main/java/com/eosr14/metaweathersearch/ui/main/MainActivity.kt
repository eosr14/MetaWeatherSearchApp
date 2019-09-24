package com.eosr14.metaweathersearch.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eosr14.metaweathersearch.R
import com.eosr14.metaweathersearch.common.base.BaseActivity
import com.eosr14.metaweathersearch.common.base.BaseRecyclerViewAdapter
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
            layoutManager = LinearLayoutManager(context).apply { orientation = RecyclerView.VERTICAL }
            adapter = MainListAdapter(object : BaseRecyclerViewAdapter.OnItemClickListener {
                override fun onItemClick(view: View, position: Int, adapter: BaseRecyclerViewAdapter<*, *>) {
                    Toast.makeText(context, "테스트 포지션 = $position", Toast.LENGTH_SHORT).show()
                }
            })
        }

        mainViewModel.isProgress.observe(this@MainActivity, Observer {
            it?.let { isProgress ->
                when (isProgress) {
                    true -> progressDialog.show()
                    false -> progressDialog.cancel()
                }
            }
        })
    }

    // [-- MainViewModelInterface
    override fun showErrorToast() = showNetworkErrorToast()
    // --] MainViewModelInterface

}
