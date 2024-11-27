package natanel.android.moviedb.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import natanel.android.moviedb.presentation.movie_details.MovieDetailsScreenRoot
import natanel.android.moviedb.presentation.movie_list.MovieListScreenRoot
import natanel.android.moviedb.presentation.movie_details.YoutubePlayerScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "movie"
    ){
        composable(route = "movie"){
            MovieListScreenRoot(navController)
        }

        composable(
            route = "movieDetails" + "/{movieId}",
            arguments = listOf(
                navArgument("movieId") {type = NavType.IntType}
            )
        ){ backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            if (movieId != null) {
                MovieDetailsScreenRoot(
                    navController,
                    movieId
                )
            }
        }

        composable(
            route = "youtubePlayerScreen/{youtubeCode}",
            arguments = listOf(navArgument("youtubeCode") { type = NavType.StringType })
        ) {
            val youtubeCode = it.arguments?.getString("youtubeCode") ?: ""
            YoutubePlayerScreen(youtubeCode = youtubeCode)
        }

    }
}