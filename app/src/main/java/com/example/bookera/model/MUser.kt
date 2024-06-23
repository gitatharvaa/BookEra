package com.example.bookera.model

data class MUser(val id: String?,
    val userID: String,
    val displayName: String,
    val avatarUrl: String,
    val quote: String,
    val profession: String){//create an object of user/ parameters

    //It will just convert the user/object here, into map
    //all of its properties
fun toMap(): MutableMap<String, Any>{
    return mutableMapOf(
        "user_id" to this.userID,//keys and values
        "display_name" to this.displayName,
        "avatar_url" to this.avatarUrl,
        "quote" to this.quote,
        "profession" to this.profession
    )
}
}
