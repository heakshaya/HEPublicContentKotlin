package com.example.hepubliccontent.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.hepubliccontent.R
import com.example.hepubliccontent.data.util.Resource
import com.example.hepubliccontent.databinding.ActivityPublicContentBinding
import com.example.hepubliccontent.presentation.adapter.PublicContentAdapter
import com.example.hepubliccontent.presentation.fragments.PublicContentFragment
import com.example.hepubliccontent.presentation.viewmodel.PublicContentViewModel
import com.example.hepubliccontent.presentation.viewmodel.PublicContentViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PublicContentActivity : AppCompatActivity() {
    var isBrandedApp = false
  /*  private val navigationView: NavigationView? = null
    private val drawerLayout: CustomDrawerLayout? = null
    private val toolbar: MaterialToolbar? = null
    private val signIn: MaterialButton? = null
    private val loginText: TextView? = null
    private val headerLogo: ImageView? = null
    private val headerTitle: TextView? = null
    private val shareIntentData1: ShareIntentData? = null
    private val loginUrl: String? = null
    private val drawerToggle: ActionBarDrawerToggle? = null
    private val publicAppStyle: PublicAppStyle? = null*/
    private lateinit var binding: ActivityPublicContentBinding
    @Inject
    lateinit var factory: PublicContentViewModelFactory
    @Inject
    lateinit var adapter: PublicContentAdapter
    private lateinit var viewModel: PublicContentViewModel
    private var page = 0
    /**
     * HashMap holds all dynamic colors
     */
    private val hmAppColors: HashMap<String, String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPublicContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, factory)[PublicContentViewModel::class.java]

        val publicContentFragment = PublicContentFragment()
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_fragment_container,publicContentFragment).addToBackStack(null).commit()

     //   initRecyclerview()

     //   setAppStyle()
    }
   /* private fun initRecyclerview() {
        binding.recyclerView.apply {
            adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addOnScrollListener(this@MainActivity.onScrollListener)
        }
    }*/
   private fun viewContentList(){
       viewModel.getContentList(page)
       viewModel.contentListMutableLiveData.observe(this){ response->
           when(response){
               is Resource.Success->{
                   response.data?.let {
                       Log.d("PublicContent","PublicContentList:"+it.data.size)
                    //   adapter.setList(it.data)
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