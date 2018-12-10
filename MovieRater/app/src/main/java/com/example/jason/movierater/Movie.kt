package com.example.jason.movierater

class Movie(Title: String, Description: String, ReleaseDate: String, Language: String, Suitable: Boolean, Violence: Boolean, LanguageUsed: Boolean)  {
    var title: String
    var description: String
    var releaseDate: String
    var language: String
    var suitable: Boolean
    var violence: Boolean
    var languageUsed: Boolean

    init {
        this.title = Title
        this.description = Description
        this.releaseDate = ReleaseDate
        this.language = Language
        this.suitable = Suitable
        this.violence = Violence
        this.languageUsed = LanguageUsed
    }


}