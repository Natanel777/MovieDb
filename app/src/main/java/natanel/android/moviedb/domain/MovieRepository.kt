package natanel.android.moviedb.domain

import kotlinx.coroutines.flow.Flow
import natanel.android.moviedb.data.database.MovieEntity
import natanel.android.moviedb.data.service.model.movie_details.MovieDetailsResponse
import natanel.android.moviedb.data.service.model.movie_list.Movie
import natanel.android.moviedb.utils.ResultWrapper

interface MovieRepository {

    suspend fun getPopularMovies(
        page: Int
    ) : Flow<ResultWrapper<List<Movie>>>

    suspend fun getNowPlayingMovies(
        page: Int
    ) : Flow<ResultWrapper<List<Movie>>>

    suspend fun getMovieDetails(id: Int) : Flow<ResultWrapper<MovieDetailsResponse>>

    suspend fun addFavoriteMovie(movieEntity: MovieEntity)

    suspend fun deleteFavoriteMovie(movieEntity: MovieEntity)

    fun getAllFavoriteMovies(): Flow<List<MovieEntity>> // Gets it already as a flow from Room
}