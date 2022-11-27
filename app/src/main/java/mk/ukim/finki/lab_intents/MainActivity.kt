package mk.ukim.finki.lab_intents

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var btnGoToExplicitActivity: Button
    private lateinit var btnGoToImplicitActivity: Button
    private lateinit var btnSendMessage: Button
    private lateinit var btnSelectImage: Button
    private lateinit var imgView: ImageView
    private var imageUri: Uri? = null

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if ( result.resultCode == Activity.RESULT_OK)
        {
            val data: Intent? = result.data
            textView.text = data?.getStringExtra("textValue")
        }
    }

    var resultLauncher2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            imageUri = data?.data
            imgView.setImageURI(imageUri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.txtView)
        btnGoToExplicitActivity = findViewById(R.id.btnGoToExplicitActivity)
        btnGoToImplicitActivity = findViewById(R.id.btnGoToImplicitActivity)
        btnSendMessage = findViewById(R.id.btnSendMessage)
        btnSelectImage = findViewById(R.id.btnSelectImage)
        imgView = findViewById(R.id.imgView)


        btnGoToExplicitActivity.setOnClickListener(){
            val intent: Intent = Intent(this, ExplicitActivity::class.java)
            resultLauncher.launch(intent)
        }

        btnGoToImplicitActivity.setOnClickListener(){ _ ->
            startActivity(Intent(this, ImplicitActivity::class.java))
        }

        btnSendMessage.setOnClickListener() {
            val emailIntent = Intent(Intent.ACTION_SEND).let { emailIntent ->
                emailIntent.type = "text/plain"

                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "MPiP Send Title")
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Content send from MainActivity")
                startActivity(emailIntent)
            }
        }

        btnSelectImage.setOnClickListener(){
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            resultLauncher2.launch(gallery)
        }

    }
}