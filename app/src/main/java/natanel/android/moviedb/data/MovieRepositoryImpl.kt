package natanel.android.moviedb.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import natanel.android.moviedb.data.database.MovieDao
import natanel.android.moviedb.data.database.MovieEntity
import natanel.android.moviedb.domain.MovieRepository
import natanel.android.moviedb.data.service.MovieApiService
import natanel.android.moviedb.data.service.model.movie_details.MovieDetailsResponse
import natanel.android.moviedb.data.service.model.movie_list.Movie
import natanel.android.moviedb.utils.ResultWrapper
import safeApiCall
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: MovieApiService,
    private val movieDao: MovieDao
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): Flow<ResultWrapper<List<Movie>>> {
        return flow {
            emit(ResultWrapper.Loading(true))
            emit(safeApiCall { apiService.getPopularMovies(page).movies })
        }
    }

    override suspend fun getNowPlayingMovies(page: Int): Flow<ResultWrapper<List<Movie>>> {
        return flow {
            emit(ResultWrapper.Loading(true))
            emit(safeApiCall { apiService.getNowPlayingMovies(page).movies })
        }
    }

    override suspend fun getMovieDetails(id: Int): Flow<ResultWrapper<MovieDetailsResponse>> {
        return flow {
            emit(ResultWrapper.Loading(true))
            emit(safeApiCall { apiService.getMovieDetails(id) })
        }
    }

    override suspend fun addFavoriteMovie(movieEntity: MovieEntity) {
        movieDao.addMovie(movieEntity)
    }

    override suspend fun deleteFavoriteMovie(movieEntity: MovieEntity) {
        movieDao.deleteMovie(movieEntity)
    }

    override fun getAllFavoriteMovies(): Flow<List<MovieEntity>> {
        return movieDao.getFavoriteMovies()
    }
}