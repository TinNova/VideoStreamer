package com.tinnovakovic.videostreamer.data.models

data class ScreenData(
    val screens: List<Screen>,
    val events: List<Event>
)

data class Screen(
    val id: String,
    val component: List<Component>
)

data class Event(
    val type: String,
    val actions: List<Action>
)

data class Action(
    val type: String,
    val data: Data,
)

data class Data(
    val value: String
)

sealed class Component {

    data class Container(val type: String, val component: List<Component>): Component()

    data class Text(val type: String, val text: String): Component()

    //my own val to hold tha value to be sent)
    data class Input(val id: String, val type: String, val hint: String, val value: String = ""): Component()

    data class Button(val type: String, val text: String, val onClick: String): Component()
}
