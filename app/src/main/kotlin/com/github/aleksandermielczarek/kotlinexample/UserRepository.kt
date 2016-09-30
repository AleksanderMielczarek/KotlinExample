package com.github.aleksandermielczarek.kotlinexample

import com.github.aleksandermielczarek.realmrepository.RealmRepository
import com.github.aleksandermielczarek.realmrepository.Repository

/**
 * Created by Aleksander Mielczarek on 30.09.2016.
 */
@RealmRepository(autoGenerateId = true)
interface UserRepository : Repository<User, String> {
}