package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.data.RecipeEntity
import com.example.myapplication.ui.components.OutlineButton
import com.example.myapplication.ui.components.PhotoStub
import com.example.myapplication.ui.components.PrimaryButton
import com.example.myapplication.ui.components.SectionLabel
import com.example.myapplication.ui.viewmodel.RecipeDetailViewModel

@Composable
fun RecipeScreen(
    onBack: () -> Unit,
    onEdit: (Long) -> Unit,
    viewModel: RecipeDetailViewModel = viewModel()
) {
    val recipe by viewModel.recipe.collectAsStateWithLifecycle()
    val colors = MaterialTheme.colorScheme

    Scaffold { innerPadding ->
        val current = recipe
        if (current != null) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlineButton(text = stringResource(R.string.btn_back), onClick = onBack)
                    OutlineButton(
                        text = stringResource(if (current.isFavorite) R.string.fav_remove else R.string.fav_add),
                        onClick = viewModel::toggleFavorite,
                        color = if (current.isFavorite) colors.error else colors.primary
                    )
                }

                PhotoStub(modifier = Modifier.height(210.dp))

                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(
                        text = current.category.uppercase(),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = colors.tertiary
                    )
                    Text(
                        text = current.title,
                        style = MaterialTheme.typography.displaySmall,
                        color = colors.onBackground
                    )
                }

                TimeBox(minutes = current.cookingTime)

                SectionLabel(stringResource(R.string.ingredients))
                IngredientsBox(current)

                SectionLabel(stringResource(R.string.instructions))
                Text(
                    text = current.instructions,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .background(colors.surface)
                        .border(1.dp, colors.outlineVariant, RoundedCornerShape(18.dp))
                        .padding(16.dp),
                    color = colors.onSurface,
                    style = MaterialTheme.typography.bodyLarge
                )

                PrimaryButton(
                    text = stringResource(R.string.btn_edit),
                    onClick = { onEdit(current.id) }
                )
            }
        }
    }
}

@Composable
private fun TimeBox(minutes: Int) {
    val colors = MaterialTheme.colorScheme
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(colors.secondaryContainer)
            .padding(horizontal = 18.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.time_label),
            color = colors.onSecondaryContainer,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = stringResource(R.string.minutes, minutes),
            fontWeight = FontWeight.Bold,
            color = colors.onSecondaryContainer,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
private fun IngredientsBox(recipe: RecipeEntity) {
    val colors = MaterialTheme.colorScheme
    val lines = recipe.ingredients.lines().filter { it.isNotBlank() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(colors.surface)
            .border(1.dp, colors.outlineVariant, RoundedCornerShape(18.dp))
            .padding(horizontal = 16.dp)
    ) {
        lines.forEachIndexed { index, line ->
            val parts = line.split(" — ", limit = 2)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 13.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = parts[0].trim(), color = colors.onSurface)
                if (parts.size > 1) {
                    Text(
                        text = parts[1].trim(),
                        color = colors.onSurfaceVariant,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            if (index < lines.lastIndex) {
                HorizontalDivider(color = colors.outlineVariant)
            }
        }
    }
}
