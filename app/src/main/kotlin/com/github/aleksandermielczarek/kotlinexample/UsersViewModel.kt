package com.github.aleksandermielczarek.kotlinexample

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
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
                        return userRepository.save(listOf(User("Janusz", "http://m.natemat.pl/9fd2a89ef2949d9c721a7a7063d61cb3,640,0,0,0.jpg"),
                                User("Mirek", "http://memytutaj.pl/uploads/2014/02/26/wszystkiego-najlepszego-mirek.jpg"),
                                User("Sebix", "http://x3.cdn03.imgwykop.pl/c3201142/comment_vHe1ElrN73HMSggMr7h07UwGa9ELpWXY.jpg")))
                                .flatMap { Observable.from(it) }
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { users.add(it) }
    }

}

fun User.openDetails(view: View) {
    UserActivity_.intent(view.context)
            .id(id)
            .start()
}