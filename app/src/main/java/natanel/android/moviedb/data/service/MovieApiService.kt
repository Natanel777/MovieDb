package natanel.android.moviedb.data.service

import natanel.android.moviedb.data.service.model.movie_details.MovieDetailsResponse
import natanel.android.moviedb.data.service.model.movie_list.now_playing.NowPlayingMovieResponse
import natanel.android.moviedb.data.service.model.movie_list.popular.PopularMovieResponse
import natanel.android.moviedb.data.service.model.player.GetVideosResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    //get now playing movies
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int?,
    ): NowPlayingMovieResponse

    //get now popular movies
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int?,
    ): PopularMovieResponse

    // Get movie details by ID
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): MovieDetailsResponse

    @GET("movie/{movieId}/videos")
    suspend fun getVideos(
        @Path("movieId") movieId: Int,
        @Query("language") language: String? = "en-US"
    ): GetVideosResponse

}

// https://api.themoviedb.org/3/movie/popular?api_key= ""