package com.example.youtubeapi.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel>  : AppCompatActivity(){

    lateinit var binding : VB
    protected abstract val viewModel: VM
    abstract fun inflateViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding()
        setContentView(binding.root)

        isConnection()
        initViewModel()
        initView()
        initListener()
    }

    open fun initViewModel(){}
    open fun isConnection(){}
    open fun initView(){}
    open fun initListener(){}
}