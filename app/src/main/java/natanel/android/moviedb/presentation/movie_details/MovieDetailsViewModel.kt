package natanel.android.moviedb.presentation.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import natanel.android.moviedb.domain.MovieRepository
import natanel.android.moviedb.data.service.model.movie_details.MovieDetailsResponse
import natanel.android.moviedb.data.service.model.movie_details.mapToMovieEntity
import natanel.android.moviedb.utils.ResultWrapper
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movieDetailsState = MutableStateFlow<MovieDetailsResponse?>(null)
    val movieDetailsState = _movieDetailsState.asStateFlow()

    private var _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    // Preventing race conditions or multiple database updates from over clicking.
    private val mutex = Mutex() // For debouncing

    fun fetchMovieDetails(id: Int) {
        viewModelScope.launch {
        repository.getMovieDetails(id).collectLatest { result ->
            when (result) {
                is ResultWrapper.Success -> {
                    _movieDetailsState.value = result.data
                    checkIfFavorite(id)
                }
                is ResultWrapper.Error -> {
                    _errorMessage.value = result.message
                }

                else -> {}
            }
        }
        }
    }

    private fun checkIfFavorite(movieId: Int) {
        viewModelScope.launch {
            repository.getAllFavoriteMovies().collect { favoriteMovies ->
                _isFavorite.value = favoriteMovies.any { it.id == movieId }
            }
        }
    }

    fun toggleFavorite(movieDetails: MovieDetailsResponse) {
        viewModelScope.launch {
            if (mutex.tryLock()) {
                try {
                    val movieEntity = movieDetails.mapToMovieEntity()
                    if (_isFavorite.value) {
                        repository.deleteFavoriteMovie(movieEntity)
                    } else {
                        repository.addFavoriteMovie(movieEntity)
                    }
                    _isFavorite.value = !_isFavorite.value
                } finally {
                    mutex.unlock()
                }
            }
        }
    }
}
