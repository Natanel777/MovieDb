package natanel.android.moviedb.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import natanel.android.moviedb.ui.theme.MovieDbGray

fun mapPopularityToCategory(popularity: Double?): String {
    return when {
        popularity == null -> "Unknown"
        popularity >= 3000 -> "Very Popular"
        popularity >= 1500 -> "Popular"
        popularity >= 200 -> "Fairly Popular"
        popularity >= 0 -> "Currently Not Very Popular"
        else -> "---"
    }
}

 fun getPopularityColor(category: String): Color {
    return when (category) {
        "Very Popular" -> Color(0xFF4CAF50)
        "Popular" -> Color(0xFF8BC34A)
        "Fairly Popular" -> Color(0xFFFF9800)
        "Currently Not Very Popular" -> Color(0xFFF44336)
        "Unknown" -> MovieDbGray
        else -> Color.White
    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    starsModifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow,
) {

    val filledStars = kotlin.math.floor(rating).toInt()
    val unfilledStars = (stars - kotlin.math.ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(
                modifier = starsModifier,
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = starsColor
            )
        }
        if (halfStar) {
            Icon(
                modifier = starsModifier,
                imageVector = Icons.AutoMirrored.Filled.StarHalf,
                contentDescription = null,
                tint = starsColor
            )
        }
        repeat(unfilledStars) {
            Icon(
                modifier = starsModifier,
                imageVector = Icons.Filled.StarOutline,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}