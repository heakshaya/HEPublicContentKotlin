package com.example.hepubliccontent.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hepubliccontent.data.model.ApiData.Data
import com.example.hepubliccontent.data.util.Resource
import com.example.hepubliccontent.databinding.FragmentPublicContentNewsTabBinding
import com.example.hepubliccontent.presentation.adapter.PublicContentAdapter
import com.example.hepubliccontent.presentation.viewmodel.PublicContentViewModel
import com.example.hepubliccontent.presentation.viewmodel.PublicContentViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment:Fragment() {
    private lateinit var binding: FragmentPublicContentNewsTabBinding
    private var page=0
    @Inject
    lateinit var factory: PublicContentViewModelFactory
    @Inject
    lateinit var adapter: PublicContentAdapter
    private lateinit var viewModel: PublicContentViewModel
    private lateinit var contentList:List<Data>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPublicContentNewsTabBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this, factory)[PublicContentViewModel::class.java]

        initView()
        return binding.root
    }

    private fun initView() {
        initRecyclerview()
        contentList=ArrayList()
        viewContentList()
    }
    private fun initRecyclerview() {
        binding.recyclerview.apply {
            adapter = adapter
            layoutManager = LinearLayoutManager(activity)
           // addOnScrollListener(this@MainActivity.onScrollListener)
        }
    }

    private fun viewContentList(){
        viewModel.getContentList(page)
        activity?.let { it ->
            viewModel.contentListMutableLiveData.observe(it){ response->
                when(response){
                    is Resource.Success->{
                        response.data?.let {
                            Log.d("PublicContent","PublicContentList:"+it.data.size)
                               adapter.setList(it.data)
                            binding.recyclerview.adapter=adapter
                        }
                    }
                    is Resource.Error->{
                        Log.d("PublicContent","Error PublicContentList:"+response.message)
                    }
                    is Resource.Loading->{

                    }
                }
            }
        }

    }
}
