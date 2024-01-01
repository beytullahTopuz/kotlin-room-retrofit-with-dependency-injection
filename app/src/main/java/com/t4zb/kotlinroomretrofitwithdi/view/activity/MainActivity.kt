package com.t4zb.kotlinroomretrofitwithdi.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.t4zb.kotlinroomretrofitwithdi.databinding.ActivityMainBinding
import com.t4zb.kotlinroomretrofitwithdi.view.adaptor.LoremAdaptor
import com.t4zb.kotlinroomretrofitwithdi.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initUI()
    }

    fun initUI() {

        val adaptor = LoremAdaptor(this, viewModel.loremLiveData.value)
        mBinding.rvLorem.layoutManager = GridLayoutManager(this, 1)
        mBinding.rvLorem.adapter = adaptor

        viewModel.loremLiveData.observe(this) {
            adaptor.updateLoremData(it)
        }

        mBinding.btnGetRemoteApi.setOnClickListener {
            viewModel.getLoremFromRemoteApi()
        }

        mBinding.btnSaveDb.setOnClickListener {
            viewModel.saveDB()
        }

        mBinding.btnGetRoom.setOnClickListener {
            viewModel.getLocalLorem()
        }
    }
}