package com.tinnovakovic.videostreamer

import androidx.navigation.NamedNavArgument

object NavDirections {

    val Default = object : NavCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destinationRoute = ""

    }

    val home = object : NavCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destinationRoute = Destination.Home.name

    }

    fun lesson(
        lesson: String? = null
    ) = object : NavCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destinationRoute = "${Destination.Lesson.name}/$lesson"
    }

}