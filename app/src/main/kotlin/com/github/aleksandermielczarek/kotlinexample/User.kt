package com.github.aleksandermielczarek.kotlinexample

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

/**
 * Created by Aleksander Mielczarek on 30.09.2016.
 */
open class User(var name: String? = "", var avatarUrl: String? = "", @PrimaryKey var id: String? = "") : RealmObject(), Serializable {

}