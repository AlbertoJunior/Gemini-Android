package albertojunior.setor0.app.noticias

import albertojunior.setor0.app.noticias.databinding.ActivityMainBinding
import albertojunior.setor0.app.noticias.interfaces.NavBarController
import albertojunior.setor0.app.noticias.utils.ContextUtils
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavBarController {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        setupNav()
    }

    private fun setupNav() {
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
    }

    override fun hideBar() {
        if (binding.navView.isVisible)
            ContextUtils.animateFadeInOut(this, false, binding.navView)
    }

    override fun showBar() {
        if (!binding.navView.isVisible)
            ContextUtils.animateFadeInOut(this, true, binding.navView)
    }
}