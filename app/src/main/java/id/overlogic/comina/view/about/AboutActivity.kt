package id.overlogic.comina.view.about

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.overlogic.comina.R
import id.overlogic.comina.databinding.ActivityAboutBinding
import id.overlogic.comina.databinding.ActivityHomeBinding
import id.overlogic.comina.model.Anime
import id.overlogic.comina.view.home.HomeActivity

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}