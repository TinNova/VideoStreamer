package com.tinnovakovic.videostreamer.data.models

data class ScreenParent(
    val screen: List<Screen>,
    val event: List<Event>
)

data class Screen(
    val id: String,
    val component: List<Components>,
)

sealed class Components {
    data class ComponentsText(
        val type: String,
        val text: String
    ): Components()

    data class ComponentsInput(
        val id: String,
        val type: String,
        val hint: String,
        ): Components()

    data class ComponentsButton(
        val type: String,
        val text: String,
        val onClick: String
    ): Components()

    data class ComponentContainer(
        val type: String,
        val components: List<Components>,
    ): Components()
}

data class Event(
    val type: String,
    val action: List<Action>
)

data class Action(
    val type: String,
    val data: Value
)

data class Value(
    val value: String
)
