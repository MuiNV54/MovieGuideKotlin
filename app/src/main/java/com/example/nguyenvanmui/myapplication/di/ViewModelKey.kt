package com.example.nguyenvanmui.myapplication.di

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import java.lang.annotation.*
import java.lang.annotation.Retention
import java.lang.annotation.Target
import kotlin.reflect.KClass

/**
 * Created by nguyen.van.mui on 13/04/2018.
 */
@MustBeDocumented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)