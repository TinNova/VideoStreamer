package com.tinnovakovic.videostreamer

import com.tinnovakovic.videostreamer.NavDirections.Default
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * NavManager must be a singleton instance. That way we can ensure that every class communicating
 * with the NavManager is referencing the same instance.
 */
class NavManager {

    // The commands reference can be observed where the NavHost is initialised in order to facilitate the navigation
    var commands: MutableStateFlow<NavCommand> = MutableStateFlow(value = Default)

    // The navigate function can be used to trigger a navigation from a ViewModel
    fun navigate(
        directions: NavCommand
    ) {
        commands.value = directions
    }

}