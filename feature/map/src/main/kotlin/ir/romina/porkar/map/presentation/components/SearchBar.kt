package ir.romina.porkar.map.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.romina.porkar.currencyconvertor.R
import ir.romina.porkar.designsystem.theme.MahdiPkTheme

@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    onSearch: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color.Transparent)
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChanged,
            placeholder = { Text(stringResource(R.string.search_stations)) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                    focusManager.clearFocus()
                }
            ),
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}
@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    MahdiPkTheme {
        SearchBar(
            query = stringResource(R.string.search_query),
            onQueryChanged = {},
            onSearch = {}
        )
    }
}