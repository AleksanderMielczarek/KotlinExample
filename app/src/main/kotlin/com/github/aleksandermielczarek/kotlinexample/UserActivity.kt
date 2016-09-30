package com.github.aleksandermielczarek.kotlinexample

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.aleksandermielczarek.hamburgerarrownavigation.HamburgerArrowNavigation
import com.github.aleksandermielczarek.hamburgerarrownavigation.HamburgerArrowNavigator
import com.github.aleksandermielczarek.kotlinexample.databinding.ActivityUserBinding
import com.github.aleksandermielczarek.napkin.Napkin
import kotlinx.android.synthetic.main.activity_user.*
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.Extra
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Aleksander Mielczarek on 30.09.2016.
 */
@EActivity
open class UserActivity : AppCompatActivity() {

    @Extra
    protected lateinit var id: String

    @Inject
    protected lateinit var userRepository: UserRepository

    private lateinit var navigator: HamburgerArrowNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Napkin.provideComponent<AppComponent>(this).inject(this)
        val binding: ActivityUserBinding = DataBindingUtil.setContentView(this, R.layout.activity_user)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        navigator = HamburgerArrowNavigation.getDefault().getHamburgerArrowNavigator(this)
        navigator.setupWithToolbar(toolbar)
        userRepository.getOne(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { binding.viewModel = it }
    }

    override fun onStart() {
        super.onStart()
        navigator.animateToArrow()
    }
}