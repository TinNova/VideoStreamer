package com.tinnovakovic.videostreamer.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

object UiElement {

    @Composable
    fun CircularItem(
        modifier: Modifier = Modifier,
        googleIcon: ImageVector = Icons.Default.Add,
        text: String,
        onClick: (() -> Unit)
    ) {
        Column(
            modifier = modifier.clickable { onClick.invoke() },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                // Google icon
                Icon(
                    imageVector = googleIcon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(8.dp)
                )
            }

            // Text below the image
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LargeTopAppBar(
        label: String,
        upIcon: ImageVector,
        upIconAction: (() -> Unit)
    ) {
        LargeTopAppBar(
            title = {
                Text(text = label)
            },
            navigationIcon = {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    androidx.compose.material3.Icon(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .clickable { upIconAction.invoke() },
                        imageVector = upIcon,
                        contentDescription = null
                    )
                }
            }
        )
    }

}