package com.github.aleksandermielczarek.kotlinexample

import android.app.Activity
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import me.tatarka.bindingcollectionadapter.ItemView
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Aleksander Mielczarek on 30.09.2016.
 */
class UsersViewModel @Inject constructor(userRepository: UserRepository) {

    val users: ObservableList<User> = ObservableArrayList()
    val userView: ItemView = ItemView.of(BR.viewModel, R.layout.recycler_user)

    init {
        userRepository.count()
                .flatMap(fun(count: Long): Observable<User> {
                    if (count > 0) {
                        return userRepository.findAll()
                    } else {
                        return userRepository.save(listOf(User("Janusz", "http://m.natemat.pl/9fd2a89ef2949d9c721a7a7063d61cb3,640,0,0,0.jpg", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut sodales vestibulum felis. Suspendisse a ultrices nulla. Nulla enim sapien, convallis sit amet feugiat vel, consequat eget mauris. Suspendisse vel risus nec nisi semper vestibulum. Ut tempus mauris a euismod dictum. Nulla porta finibus tellus quis imperdiet. Aliquam fermentum bibendum scelerisque. Cras laoreet id lorem non ornare. Sed ac dignissim lacus, vel semper ante. Quisque nec augue viverra, consequat dui id, finibus ipsum. Mauris lacinia, orci sed volutpat sodales, odio velit scelerisque turpis, ac posuere mauris enim ac nisl."),
                                User("Mirek", "http://memytutaj.pl/uploads/2014/02/26/wszystkiego-najlepszego-mirek.jpg", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut sodales vestibulum felis. Suspendisse a ultrices nulla. Nulla enim sapien, convallis sit amet feugiat vel, consequat eget mauris. Suspendisse vel risus nec nisi semper vestibulum. Ut tempus mauris a euismod dictum. Nulla porta finibus tellus quis imperdiet. Aliquam fermentum bibendum scelerisque. Cras laoreet id lorem non ornare. Sed ac dignissim lacus, vel semper ante. Quisque nec augue viverra, consequat dui id, finibus ipsum. Mauris lacinia, orci sed volutpat sodales, odio velit scelerisque turpis, ac posuere mauris enim ac nisl."),
                                User("Sebix", "http://x3.cdn03.imgwykop.pl/c3201142/comment_vHe1ElrN73HMSggMr7h07UwGa9ELpWXY.jpg", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut sodales vestibulum felis. Suspendisse a ultrices nulla. Nulla enim sapien, convallis sit amet feugiat vel, consequat eget mauris. Suspendisse vel risus nec nisi semper vestibulum. Ut tempus mauris a euismod dictum. Nulla porta finibus tellus quis imperdiet. Aliquam fermentum bibendum scelerisque. Cras laoreet id lorem non ornare. Sed ac dignissim lacus, vel semper ante. Quisque nec augue viverra, consequat dui id, finibus ipsum. Mauris lacinia, orci sed volutpat sodales, odio velit scelerisque turpis, ac posuere mauris enim ac nisl.")))
                                .flatMap { Observable.from(it) }
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { users.add(it) }
    }

}

fun User.openDetails(view: View) {
    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(view.context as Activity, view.findViewById(R.id.avatar), "avatar")
    UserActivity_.intent(view.context)
            .id(id)
            .withOptions(options.toBundle())
            .start()
}