package com.tinnovakovic.videostreamer.data.local_api

import com.google.gson.*
import com.tinnovakovic.videostreamer.data.models.Action
import com.tinnovakovic.videostreamer.data.models.Component
import com.tinnovakovic.videostreamer.data.models.Data
import com.tinnovakovic.videostreamer.data.models.Event
import com.tinnovakovic.videostreamer.data.models.Primer
import com.tinnovakovic.videostreamer.data.models.Screen
import java.lang.reflect.Type

class PrimerTypeAdapter : JsonSerializer<Primer>, JsonDeserializer<Primer> {
    override fun serialize(
        src: Primer,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val jsonObject = JsonObject()
        val screensArray = JsonArray()

        src.screens.forEach { screen ->
            val screenObject = JsonObject()
            screenObject.addProperty("id", screen.id)

            val componentsArray = JsonArray()
            screen.components.forEach { component ->
                componentsArray.add(serializeComponent(component, context))
            }
            screenObject.add("components", componentsArray)

            screensArray.add(screenObject)
        }

        jsonObject.add("screens", screensArray)

        val eventsArray = JsonArray()
        src.events.forEach { event ->
            val eventObject = JsonObject()
            eventObject.addProperty("type", event.type)

            val actionsArray = JsonArray()
            event.actions.forEach { action ->
                val actionObject = JsonObject()
                actionObject.addProperty("type", action.type)

                val dataObject = JsonObject()
                dataObject.addProperty("value", action.data.value)

                actionObject.add("data", dataObject)
                actionsArray.add(actionObject)
            }
            eventObject.add("actions", actionsArray)

            eventsArray.add(eventObject)
        }

        jsonObject.add("events", eventsArray)

        return jsonObject
    }

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Primer {
        val jsonObject = json.asJsonObject

        val screens = mutableListOf<Screen>()
        val screensJsonArray = jsonObject.getAsJsonArray("screens")
        screensJsonArray?.forEach { screenElement ->
            val screenObject = screenElement.asJsonObject
            val id = screenObject.get("id").asString

            val componentsArray = screenObject.getAsJsonArray("components")
            val components = componentsArray.map { deserializeComponent(it, context) }

            screens.add(Screen(id, components))
        }

        val events = mutableListOf<Event>()
        val eventsJsonArray = jsonObject.getAsJsonArray("events")
        eventsJsonArray?.forEach { eventElement ->
            val eventObject = eventElement.asJsonObject
            val eventType = eventObject.get("type").asString

            val actionsArray = eventObject.getAsJsonArray("actions")
            val actions = mutableListOf<Action>()
            actionsArray?.forEach { actionElement ->
                val actionObject = actionElement.asJsonObject
                val actionType = actionObject.get("type").asString

                val dataObject = actionObject.getAsJsonObject("data")
                val value = dataObject.get("value").asString

                actions.add(Action(actionType, Data(value)))
            }
            events.add(Event(eventType, actions))
        }

        return Primer(screens, events)
    }

    private fun serializeComponent(
        component: Component,
        context: JsonSerializationContext?
    ): JsonElement {
        return when (component) {
            is Component.Container -> {
                val jsonObject = JsonObject()
                jsonObject.addProperty("type", component.type)

                val componentsArray = JsonArray()
                component.components.forEach { subComponent ->
                    componentsArray.add(serializeComponent(subComponent, context))
                }
                jsonObject.add("components", componentsArray)

                jsonObject
            }
            is Component.Text -> {
                val jsonObject = JsonObject()
                jsonObject.addProperty("type", component.type)
                jsonObject.addProperty("text", component.text)
                jsonObject
            }
            is Component.Input -> {
                val jsonObject = JsonObject()
                jsonObject.addProperty("id", component.id)
                jsonObject.addProperty("type", component.type)
                jsonObject.addProperty("hint", component.hint)
                jsonObject
            }
            is Component.Button -> {
                val jsonObject = JsonObject()
                jsonObject.addProperty("type", component.type)
                jsonObject.addProperty("text", component.text)
                jsonObject.addProperty("onClick", component.onClick)
                jsonObject
            }
        }
    }

    private fun deserializeComponent(
        jsonElement: JsonElement,
        context: JsonDeserializationContext?
    ): Component {
        val jsonObject = jsonElement.asJsonObject
        val type = jsonObject.get("type").asString

        return when (type) {
            "Container" -> {
                val componentsArray = jsonObject.getAsJsonArray("components")
                val components = componentsArray.map { deserializeComponent(it, context) }
                Component.Container(type, components)
            }
            "Text" -> {
                val text = jsonObject.get("text").asString
                Component.Text(type, text)
            }
            "Input" -> {
                val id = jsonObject.get("id").asString
                val hint = jsonObject.get("hint").asString
                Component.Input(id, type, hint)
            }
            "Button" -> {
                val text = jsonObject.get("text").asString
                val onClick = jsonObject.get("onClick").asString
                Component.Button(type, text, onClick)
            }
            else -> throw JsonParseException("Invalid component type")
        }
    }
}