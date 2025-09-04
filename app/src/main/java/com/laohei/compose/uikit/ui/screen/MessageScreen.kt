package com.laohei.compose.uikit.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.laohei.compose.uicore.ActionButtonListItem
import com.laohei.compose.uikit.R
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

private data class Message(
    @DrawableRes val face: Int,
    val name: String,
    val message: String
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalUuidApi::class)
@Composable
fun MessageScreen() {
    val messages = remember {
        val original = listOf(
            Message(R.drawable.user_1, "Rem", "Hi 486"),
            Message(R.drawable.user_2, "Emilia", "Hello 486"),
            Message(R.drawable.user_3, "Rem", "Hi 486"),
            Message(R.drawable.user_4, "Asuka", "Noting!"),
            Message(R.drawable.user_5, "Sister", "Good!!!"),
            Message(R.drawable.user_6, "Younger Sister", "My Brother"),
            Message(R.drawable.user_7, "School girl", "School"),
            Message(R.drawable.user_8, "Chopper", "I'm Chopper"),
        )
        List(3) { original }.flatten()
    }
    val listState = rememberLazyListState()
    var isOpenIndex by remember { mutableStateOf<Int?>(null) }

    fun closeExpandedItem() {
        isOpenIndex = null
    }

    LaunchedEffect(listState.isScrollInProgress) {
        if (listState.isScrollInProgress) {
            closeExpandedItem()
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Message(9)")
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { if (isOpenIndex != null) closeExpandedItem() },
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { Spacer(Modifier) }
            itemsIndexed(
                messages,
                key = { index, item -> "${item.face}-${Uuid.random()}" }) { index, message ->
                var isOpen by remember { mutableStateOf(false) }
                MessageItem(
                    message = message,
                    isOpen = isOpenIndex == index && isOpen,
                    alignment = Alignment.End,
                    onOpenChange = {
                        isOpenIndex = if (it) index else null
                        isOpen = it
                    },
                    onClick = {
                        if (isOpenIndex != null) {
                            closeExpandedItem()
                        } else {
                            // TODO(other click event)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun MessageItem(
    message: Message, isOpen: Boolean, alignment: Alignment.Horizontal,
    onOpenChange: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    ActionButtonListItem(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp)),
        isOpen = isOpen,
        actionAlignment = alignment,
        onOpenChange = onOpenChange,
        onClick = onClick
    ) {
        ListItem(
            modifier = Modifier.clip(RoundedCornerShape(16.dp)),
            leadingContent = {
                Image(
                    painter = painterResource(message.face),
                    contentDescription = "user-${message.face}",
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            },
            headlineContent = {
                Text(
                    text = message.name,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            supportingContent = {
                Text(
                    text = message.message,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.LightGray
                )
            },
            trailingContent = {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                    contentDescription = Icons.AutoMirrored.Rounded.KeyboardArrowRight.name,
                    tint = Color.LightGray
                )
            }
        )
        FilledIconButton(
            onClick = {},
            shape = CircleShape,
            modifier = Modifier
                .size(32.dp)
                .aspectRatio(1f),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = Color(0xff528aff),
                contentColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Share,
                contentDescription = Icons.Rounded.Share.name
            )
        }
        FilledIconButton(
            onClick = {}, modifier = Modifier
                .size(32.dp)
                .aspectRatio(1f),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = Color(0xffe7f62f),
                contentColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Favorite,
                contentDescription = Icons.Rounded.Favorite.name
            )
        }
        FilledIconButton(
            onClick = {}, modifier = Modifier
                .size(32.dp)
                .aspectRatio(1f),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = Color(0xffff1111),
                contentColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = Icons.Rounded.Delete.name
            )
        }
    }
}