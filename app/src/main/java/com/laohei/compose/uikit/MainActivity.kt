package com.laohei.compose.uikit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laohei.compose.uicore.NotchedBottomBar
import com.laohei.compose.uikit.ui.theme.Common_ui_kitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Common_ui_kitTheme {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotchedBottomBarPreview() {
    Common_ui_kitTheme {
        var selectedIndex by remember { mutableIntStateOf(0) }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(
                36.dp,
                Alignment.Bottom
            )
        ) {
            NotchedBottomBar(
                modifier = Modifier
                    .height(50.dp),
                icons = listOf(
                    R.drawable.btn_1,
                    R.drawable.btn_2,
                    R.drawable.btn_3,
                    R.drawable.btn_4,
                ),
                selectedIndex = selectedIndex,
                fabIcon = R.drawable.float_icon,
                fabSize = 50.dp,
                fabIconSize = 22.dp,
                onFabClick = {},
                onIconClick = { selectedIndex = it }
            )
            NotchedBottomBar(
                modifier = Modifier
                    .height(50.dp),
                icons = listOf(
                    Icons.Default.Home,
                    Icons.Default.Search,
                    Icons.Default.Person,
                    Icons.Default.Settings
                ),
                selectedIndex = selectedIndex,
                fabIcon = Icons.Default.Add,
                fabSize = 50.dp,
                onFabClick = {},
                onIconClick = { selectedIndex = it }
            )
        }
    }
}
