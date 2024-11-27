package natanel.android.moviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import natanel.android.moviedb.presentation.Navigation
import natanel.android.moviedb.ui.theme.MovieDbTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            //val viewModel: MovieViewModel = hiltViewModel()
            //val movies by viewModel.movieListState.collectAsStateWithLifecycle() // Lifecycle to MainActivity

            MovieDbTheme {
                Navigation()
            }
        }
    }
}
