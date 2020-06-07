package fr.isen.iasoni.alexdroidpizza

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_valid.*


class ValidActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_valid)

        val sharedpreferences = getSharedPreferences(
            "Commande",
            Context.MODE_PRIVATE
        )
        if (sharedpreferences.contains("Name")) {
            namerecap.setText(sharedpreferences.getString("Name", ""))
        }
        if (sharedpreferences.contains("Surname")) {
            surnamerecap.setText(sharedpreferences.getString("Surname", ""))
        }
        val extras = intent.extras

        if (extras != null) {
            phonerecap.text = extras.getString("phone")
            pizzarecap.text = extras.getString("pizza")
            timerecap.text = extras.getString("time")
            adresserecap.text = extras.getString("adresse")
        }

        returnHome.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
        mailbtn.setOnClickListener{
            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:marc.mollinari@gmail.com"))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Confirmation commande ")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Votre commande a bien été enregistrée")

            startActivity(Intent.createChooser(emailIntent, "Email Commande"))
        }
    }
}
