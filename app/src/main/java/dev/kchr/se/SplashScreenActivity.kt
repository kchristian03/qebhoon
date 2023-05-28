package dev.kchr.se

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
//import com.google.android.gms.common.GoogleApiAvailability
//import com.google.android.gms.common.GooglePlayServicesNotAvailableException
//import com.google.android.gms.common.GooglePlayServicesRepairableException
//import com.google.android.gms.security.ProviderInstaller
import dev.kchr.se.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menggunakan ActivitySplashScreenBinding untuk menghubungkan tampilan layout dengan activity
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        // Menetapkan tampilan root dari layout yang dihubungkan ke setContentView
        setContentView(binding.root)

        // Menyembunyikan ActionBar
        supportActionBar?.hide()

//        try {
//            ProviderInstaller.installIfNeeded(this)
//        } catch (e: GooglePlayServicesRepairableException) {
//            // Prompt the user to install/update/enable Google Play services.
//            GoogleApiAvailability.getInstance()
//                .showErrorNotification(this, e.connectionStatusCode)
//        } catch (e: GooglePlayServicesNotAvailableException) {
//            // Indicates a non-recoverable error: let the user know.
//        }

        // Mengatur penundaan 3 detik sebelum berpindah ke MainActivity
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
