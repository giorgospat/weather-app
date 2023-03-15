package com.example.presentation.navigation

internal enum class Screen(val route: String) {
    Home("home"),
    Details("details"),
    AddLocation("add_location")
}

internal fun Screen.toRoute(): String {
    return "/${this.route}"
}

internal fun Screen.toPath(): String {
    return when (this) {
        Screen.Details -> this.toRoute() + "/{${Arguments.id}}"
        else -> this.toRoute()
    }
}

internal fun Screen.toRoute(id: String): String {
    return toRoute() + "/$id"
}