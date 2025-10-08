package com.alguien.dijochamba.core.navigation


sealed class Route(val route: String){
    object Login: Route("login_form")
    object Register: Route("register_form")
    object Main: Route("main")
    object ProductDetail: Route("product_detail") {
        const val routeWithArgument = "product_detail/{id}"
        const val argument = "id"
    }

    object Intro1: Route("intro1")
    object Intro2: Route("intro2")

    object RegisterProfile: Route("register_profile/{userName}") {
        const val routeWithArgument = "register_profile/{userName}"
        const val argument = "userName"
    }
}