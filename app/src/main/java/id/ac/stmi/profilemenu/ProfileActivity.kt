package id.ac.stmi.profilemenu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Data diterima dari MainActivity lewat Explicit Intent
        val nama = intent.getStringExtra(MainActivity.EXTRA_NAMA) ?: ""
        val email = intent.getStringExtra(MainActivity.EXTRA_EMAIL) ?: ""
        val whatsapp = intent.getStringExtra(MainActivity.EXTRA_WHATSAPP) ?: ""
        val portofolio = intent.getStringExtra(MainActivity.EXTRA_PORTOFOLIO) ?: ""
        val linkedin= intent.getStringExtra(MainActivity.EXTRA_LINKEDIN) ?: ""

        findViewById<TextView>(R.id.tv_nama).text = nama
        findViewById<TextView>(R.id.tv_email).text = email
        findViewById<TextView>(R.id.tv_whatsapp).text = whatsapp
        findViewById<TextView>(R.id.tv_portofolio).text = portofolio


        findViewById<ImageButton>(R.id.btn_back).setOnClickListener { finish() }

        // IMPLICIT INTENT 1: Kunjungi Website -> membuka URL di browser
        findViewById<Button>(R.id.btn_website).setOnClickListener {
            var url = portofolio
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://$url"
            }
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }

        // IMPLICIT INTENT 2: Kunjungi linkedIn -> membuka URL di browser
        findViewById<Button>(R.id.btn_linkedin).setOnClickListener {
            var url = linkedin
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://$url"
            }
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }

        // IMPLICIT INTENT 3: Hubungi Saya -> membuka dial pad dengan nomor terisi
        findViewById<Button>(R.id.btn_hubungi).setOnClickListener {
            var url = whatsapp
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                if (url.startsWith("0")) {
                    url = "62" + url.substring(1)
                }
                url = "https://wa.me/$url"
            }
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }

        // IMPLICIT INTENT 4: Bagikan Kartu -> mengirim teks ke aplikasi lain (chooser)
        findViewById<Button>(R.id.btn_bagikan).setOnClickListener {
            val pesan = "Halo, saya $nama\n" +
                    "Saya adalah Mobile Developer.\n\n" +
                    "Email: $email\n" +
                    "WA/HP: $whatsapp\n" +
                    "Portofolio: $portofolio"

            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, pesan)
            }
            startActivity(Intent.createChooser(sendIntent, "Bagikan kartu nama lewat"))
        }
    }
}
