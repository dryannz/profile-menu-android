package id.ac.stmi.profilemenu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNama = findViewById<TextInputEditText>(R.id.et_nama)
        val etEmail = findViewById<TextInputEditText>(R.id.et_email)
        val etWhatsapp = findViewById<TextInputEditText>(R.id.et_whatsapp)
        val etPortofolio = findViewById<TextInputEditText>(R.id.et_portofolio)
        val etLinekdiIn = findViewById<TextInputEditText>(R.id.et_linkedin)

        val btnLihatKartu = findViewById<Button>(R.id.btn_lihat_kartu)
        btnLihatKartu.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val whatsapp = etWhatsapp.text.toString().trim()
            val portofolio = etPortofolio.text.toString().trim()
            val linkedin = etLinekdiIn.text.toString().trim()

            if (nama.isEmpty() || email.isEmpty() || whatsapp.isEmpty() || portofolio.isEmpty() || linkedin.isEmpty()) {
                Toast.makeText(this, "Mohon lengkapi semua data terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, ProfileActivity::class.java).apply {
                putExtra(EXTRA_NAMA, nama)
                putExtra(EXTRA_EMAIL, email)
                putExtra(EXTRA_WHATSAPP, whatsapp)
                putExtra(EXTRA_PORTOFOLIO, portofolio)
                putExtra(EXTRA_LINKEDIN,linkedin)
            }
            startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_NAMA = "EXTRA_NAMA"
        const val EXTRA_EMAIL = "EXTRA_EMAIL"
        const val EXTRA_WHATSAPP = "EXTRA_WHATSAPP"
        const val EXTRA_PORTOFOLIO = "EXTRA_PORTOFOLIO"
        const val EXTRA_LINKEDIN = "EXTRA_LINKEDIN"
    }
}
