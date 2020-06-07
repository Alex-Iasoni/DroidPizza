package fr.isen.iasoni.alexdroidpizza

import android.app.TimePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var optionPizza : Spinner
        optionPizza = findViewById(R.id.pizzaSpinner) as Spinner
        val mPickTimeBtn = findViewById<Button>(R.id.pickTimeBtn)
        val textView     = findViewById<TextView>(R.id.time)

        mPickTimeBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                textView.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }


    }

fun VerifCommand(command : Map<String,String>) : ArrayList<String>{
        val erreurs : ArrayList<String> = ArrayList()
for((k,v) in command){

    if(v == null ||v.equals("") || v.equals("Temps")){
     erreurs.add(k)
    }
}
    return erreurs

    }
    fun getArrayErreurs(erreurs : ArrayList<String> ) : String{
        var Problem : String = ""
        for(i in erreurs){
            Problem = Problem + " , " + i.toString()
        }

        return Problem

    }

fun commander(view : View){
    val name = newName.text.toString()
    val surname = newSurName.text.toString()
    val adresse = newadresse.text.toString()
    val phone = newPhone.text.toString()
    val time = time.text.toString()
val pizza = pizzaSpinner.selectedItem.toString()
val command = mapOf<String , String>("Nom" to name,"Prénom" to surname, "Adresse" to adresse,"Téléphone" to phone,"Temps" to time, "Pizza" to pizza)


if(VerifCommand(command).size == 0 ){

    val intent = Intent(this, ValidActivity::class.java)
    intent.putExtra("adresse", adresse);
    intent.putExtra("phone", phone);
    intent.putExtra("time", time);
    intent.putExtra("pizza", pizza);
    startActivity(intent)
    val sharedpreferences = getSharedPreferences("Commande", 0)
    val editor: SharedPreferences.Editor = sharedpreferences.edit()
    editor.putString("Name", name)
    editor.putString("Surname", surname)
    editor.commit()
}
else {
    Toast.makeText(baseContext, "Champs manquant(s): " + getArrayErreurs(VerifCommand(command)),
        Toast.LENGTH_SHORT).show()
}

}

}
