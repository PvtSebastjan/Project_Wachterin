package com.sebastjanjernejjapelj.project_wachterin

sealed class Screens (val screen:String){


    data object Home:Screens("home")
    data object FindMe:Screens("findMe")
    data object Notification:Screens("notification")
    data object User:Screens("user")
    data object CardFull:Screens("cardFull")
}