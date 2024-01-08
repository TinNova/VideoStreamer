package com.tinnovakovic.videostreamer.data.models

data class Primer(
    val screens: List<Screen>,
    val events: List<Event>
)

data class Event(
    val type: String,
    val actions: List<Action>
)

data class Action(
    val type: String,
    val data: Data
)

data class Data(
    val value: String
)

data class Screen(
    val id: String,
    val components: List<Component>
)

sealed class Component {
    data class Container(
        val type: String,
        val components: List<Component>
    ) : Component()

    data class Text(
        val type: String,
        val text: String
    ) : Component()

    data class Input(
        val id: String,
        val type: String,
        val hint: String
    ) : Component()

    data class Button(
        val type: String,
        val text: String,
        val onClick: String
    ) : Component()
}
