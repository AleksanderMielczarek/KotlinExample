package com.github.aleksandermielczarek.kotlinexample

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.aleksandermielczarek.hamburgerarrownavigation.HamburgerArrowNavigation
import com.github.aleksandermielczarek.hamburgerarrownavigation.HamburgerArrowNavigator
import com.github.aleksandermielczarek.kotlinexample.databinding.ActivityUsersBinding
import com.github.aleksandermielczarek.napkin.Napkin
import kotlinx.android.synthetic.main.activity_users.*
import org.androidannotations.annotations.EActivity
import javax.inject.Inject

/**
 * Created by Aleksander Mielczarek on 30.09.2016.
 */
@EActivity
open class UsersActivity : AppCompatActivity() {

    @Inject
    protected lateinit var usersViewModel: UsersViewModel

    private lateinit var navigator: HamburgerArrowNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Napkin.provideComponent<AppComponent>(this).inject(this)
        val binding: ActivityUsersBinding = DataBindingUtil.setContentView(this, R.layout.activity_users)
        setSupportActionBar(toolbar)
        navigator = HamburgerArrowNavigation.getDefault().getHamburgerArrowNavigator(this)
        navigator.setupWithToolbar(toolbar)
        binding.viewModel = usersViewModel
    }

    override fun onStart() {
        super.onStart()
        navigator.animateToHamburger()
    }
}