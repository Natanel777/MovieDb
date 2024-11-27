package natanel.android.moviedb.presentation.movie_list

sealed interface MovieListAction {
    data class Paginate(val category: String): MovieListAction // to know which Category to load

    data object PopularClick : MovieListAction
    data object NowPlayingClick : MovieListAction
    data object FavoritesClick : MovieListAction

}
