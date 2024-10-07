package id.overlogic.comina.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime (
    val title: String,
    val rating: Double,
    val author: String,
    val releaseDate: String,
    val image: Int,
    val genres: String,
    val description: String,
    val episodes: Int,
    val status: String,
    val duration: String,
): Parcelable
