package com.emirate.youth.eya.utils.model

data class SkillsCatModel(
    var category:Int=0,
    var skill:String="",
    var parentCat:Int=0,
    var isChecked:Boolean=false
)
