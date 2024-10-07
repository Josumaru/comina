package id.overlogic.comina.view.detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.overlogic.comina.R
import id.overlogic.comina.databinding.ActivityDetailBinding
import id.overlogic.comina.model.Anime

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_ANIME = "extra_anime"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)

        val anime = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Anime>(EXTRA_ANIME, Anime::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Anime>(EXTRA_ANIME)
        }

        if(anime != null) {
            binding.ivBackground.setImageResource(anime.image)
            binding.ivImageCard.setImageResource(anime.image)
            binding.tvTitle.text = anime.title
            binding.tvRating.text = "${anime.rating.toString()}"
            binding.tvGenres.text = anime.genres
            val status = "${anime.episodes} Episodes (${anime.status} - ${anime.duration} Min)"
            binding.tvEpisodesStatus.text = status
            binding.tvReleaseDate.text = anime.releaseDate
            binding.tvDescription.text = anime.description
            binding.tvAuthor.text = anime.author
        }


        binding.actionBack.setOnClickListener(this)
        binding.actionShare.setOnClickListener(this)

        setContentView(binding.root)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.action_back -> finish()
            R.id.action_share -> {
                val anime = intent.getParcelableExtra<Anime>(EXTRA_ANIME)
                if (anime != null) {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.type = "text/plain"

                    val shareText = """
                    Anime Title: ${anime.title}
                    Rating: ${anime.rating}
                    Genres: ${anime.genres}
                    Episodes: ${anime.episodes} (${anime.status})
                    Release Date: ${anime.releaseDate}
                    Description: ${anime.description}
                """.trimIndent()

                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this anime!")
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareText)

                    // Mulai intent share
                    startActivity(Intent.createChooser(shareIntent, "Share Anime via"))
                }
            }
        }
    }
}