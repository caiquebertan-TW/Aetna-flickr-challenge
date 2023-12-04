package com.caique.aetnatestflickr.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.caique.aetnatestflickr.R
import com.caique.aetnatestflickr.ui.design.AppIcons
import com.caique.aetnatestflickr.ui.design.AppTheme

@Composable
fun SearchToolbar(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    suggestions: List<String> = emptyList(),
//    onSearchQueryChanged: (String) -> Unit,
    onSearchTriggered: (String) -> Unit,
) {
    Box(
        modifier = modifier.padding(horizontal = 16.dp
    )) {
        SearchTextField(
//            onSearchQueryChanged = onSearchQueryChanged,
            onSearchTriggered = onSearchTriggered,
            searchQuery = searchQuery,
        )
//        DropdownMenu(
//            expanded = true, //TODO: how to search and keep track here to update suggestions?
//            onDismissRequest = {  },
//            modifier = Modifier
//                .padding(horizontal = 16.dp)
//                .fillMaxWidth()
//            ,
//        ) {
//            suggestions.forEach { label ->
//                DropdownMenuItem(
//                    text = {
//                        Text(text = label)
//                    },
//                    onClick = {
//                        onSearchTriggered(label)
//                    }
//                )
//
//
//            }
//        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchTextField(
//    onSearchQueryChanged: (String) -> Unit,
    searchQuery: String,
    onSearchTriggered: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchedQuery by remember {
        mutableStateOf(TextFieldValue(text = searchQuery))
    }

    val onSearchExplicitlyTriggered = {
        keyboardController?.hide()
        onSearchTriggered(searchedQuery.text)
    }

    OutlinedTextField(
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            Icon(
                imageVector = AppIcons.Search,
                contentDescription = stringResource(
                    id = R.string.search,
                ),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        },
        trailingIcon = {
            if (searchedQuery.text.isNotEmpty()) {
                IconButton(
                    onClick = {
                        searchedQuery = TextFieldValue("")
                        onSearchExplicitlyTriggered()
                    },
                ) {
                    Icon(
                        imageVector = AppIcons.Close,
                        contentDescription = stringResource(
                            id = R.string.clear_search_text_content_desc,
                        ),
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        value = searchedQuery,
        onValueChange = {
            searchedQuery = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .testTag("searchTextField"),
//        shape = RoundedCornerShape(32.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onSearchExplicitlyTriggered()
            },
        ),
        maxLines = 1,
        singleLine = true,
    )
}

@Preview
@Composable
private fun SearchScreenPreview() {
    AppTheme {
        SearchToolbar(
//            onSearchQueryChanged = {},
            onSearchTriggered = {}
        )
    }
}
