package sv.edu.udb.login_firebase

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType {
    BASIC,
    GOOGLE
}

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val msg = savedInstanceState?.getString("email") ?:"no cargo el mensaje"
        Log.d("mensaje", msg)
        val bundle = intent.extras
        val email = bundle?.getString("email") ?:""
        val provider = bundle?.getString("provider") ?:""

        // setup
        setup(email, provider)
    }

    private fun setup(email: String, providerType: String) {
        title = "Inicio"

        val txtEmail = findViewById<TextView>(R.id.emailTextView)
        val txtProvider = findViewById<TextView>(R.id.providerTextView)

        txtEmail.text = email
        txtProvider.text = providerType

        val closeBtn = findViewById<Button>(R.id.closeBtn)
        closeBtn.setOnClickListener() {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }


    }
}