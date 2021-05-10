package com.britishbroadcast.frindershare.model.data

class FrinderPost(var userId: String, val id: String, var imageUrl: String, var description: String){
    constructor(): this("","","","")
}