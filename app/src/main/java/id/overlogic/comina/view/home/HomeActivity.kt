package id.overlogic.comina.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.overlogic.comina.R
import id.overlogic.comina.adapter.ListAnimeAdapter
import id.overlogic.comina.databinding.ActivityHomeBinding
import id.overlogic.comina.model.Anime
import id.overlogic.comina.view.about.AboutActivity
import id.overlogic.comina.view.detail.DetailActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val listAnime = ArrayList<Anime>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnWatchNow.setOnClickListener {
            Toast.makeText(this, "Sorry this features only for authenticated user only", Toast.LENGTH_SHORT).show()
        }

        binding.rvAnime.setHasFixedSize(true)

        listAnime.addAll(getListAnimes())
        showRecyclerList()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.aboutPage.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getListAnimes(): ArrayList<Anime> {
        val dataTitles = resources.getStringArray(R.array.data_titles)
        val dataRatings = resources.getStringArray(R.array.data_ratings)
        val dataAuthors = resources.getStringArray(R.array.data_authors)
        val dataReleaseDates = resources.getStringArray(R.array.data_release_dates)
        val dataImages = resources.obtainTypedArray(R.array.data_images)
        val dataGenres = resources.getStringArray(R.array.data_genres)
        val dataDescriptions = resources.getStringArray(R.array.data_descriptions)
        val dataEpisodes = resources.getStringArray(R.array.data_episodes)
        val dataStatus = resources.getStringArray(R.array.data_status)
        val dataDuration = resources.getStringArray(R.array.data_durations)

        val dataAnimes = ArrayList<Anime>()

        for(i in dataTitles.indices) {
            val anime = Anime(dataTitles[i], dataRatings[i].toString().toDouble(), dataAuthors[i], dataReleaseDates[i], dataImages.getResourceId(i, -1), dataGenres[i], dataDescriptions[i], dataEpisodes[i].toString().toInt(), dataStatus[i], dataDuration[i])
            dataAnimes.add(anime)
        }

        return dataAnimes
    }

    private fun showRecyclerList() {
        binding.rvAnime.layoutManager = LinearLayoutManager(this)
        val listAnimeAdapter = ListAnimeAdapter(listAnime)
        binding.rvAnime.adapter = listAnimeAdapter

        listAnimeAdapter.setOnItemClickCallback(object: ListAnimeAdapter.OnItemClickCallback {
            override fun onItemClick(data: Anime) {
                val intent = Intent(this@HomeActivity, DetailActivity::class.java)
                Toast.makeText(this@HomeActivity, "Viewing ${data.title}", Toast.LENGTH_SHORT).show()
                intent.putExtra(DetailActivity.EXTRA_ANIME, data)
                startActivity(intent)
            }
        })
    }



}