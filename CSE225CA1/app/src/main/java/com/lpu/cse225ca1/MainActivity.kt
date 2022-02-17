package com.lpu.cse225ca1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.lpu.cse225ca1.helpers.DataProvider
import com.lpu.cse225ca1.models.CardItem
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var listContainer: LinearLayout

    private var cardsList: ArrayList<CardItem> = ArrayList()

    private val dataProvider: DataProvider = DataProvider()

    private lateinit var cardActivityWithResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listContainer = findViewById(R.id.listContainer)
        progressBar = findViewById(R.id.homeProgressBar)

        // PROGRESS BAR IMPLEMENTATION
        Handler(Looper.getMainLooper()).postDelayed(
            {
                progressBar.visibility = ViewGroup.GONE
                loadList()
            }, 3000
        )

        // SETTING TOOLBAR AS ACTION BAR
        setSupportActionBar(findViewById(R.id.homeToolbar))

        // INTENT RESULT IMPLEMENTATION
        cardActivityWithResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result: ActivityResult? ->
                if (result?.resultCode == RESULT_OK) {
                    val itemId = result.data?.getIntExtra("id", -1)
                    val item = cardsList.find { cardItem -> cardItem.id == itemId }
                        ?: return@registerForActivityResult
                    item.rating = result.data?.getFloatExtra("rating", -1.0f)!!
                }
            }
    }

    private fun loadImage(view: ImageView, url: String) {
        // PICASSO LIBRARY IS USED TO LOAD IMAGES USING URL
        Picasso.get().load(url).resize(700, 300).centerCrop().into(view)
    }

    // IMAGE ITEMS INSIDE SCROLLVIEW IMPLEMENTATION
    private fun loadList() {
        cardsList = dataProvider.getRandomCardItemList()

        for (item in cardsList) {
            val iv = ImageView(this)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, 10, 10)
            iv.layoutParams = params
            iv.setOnClickListener {
                onCardItemTap(item)
            }

            listContainer.addView(iv)
            loadImage(iv, item.imageUrl)
        }
    }

    // ON IMAGE TAP LAUNCHING INTENT IMPLEMENTATION
    private fun onCardItemTap(item: CardItem) {
        val intent = Intent(this, CardDetailActivity::class.java)
        intent.putExtra("id", item.id)
        intent.putExtra("title", item.title)
        intent.putExtra("imageUrl", item.imageUrl)
        intent.putExtra("rating", item.rating)
        cardActivityWithResult.launch(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    // TOOLBAR MENU IMPLEMENTATION
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_notifications -> {
                showCustomToast()
                return true
            }
            R.id.menu_support -> {
                Toast.makeText(this, "Contact Support", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.menu_share -> {
                Toast.makeText(this, "Share", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.menu_settings -> {
                Toast.makeText(this, "Settings", Toast.LENGTH_LONG).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // CUSTOM TOAST IMPLEMENTATION
    private fun showCustomToast() {
        val vg = findViewById<ViewGroup>(R.id.custom_toast)
        val inflater = layoutInflater
        val layout: View = inflater.inflate(R.layout.custom_toast_layout, vg)

        val tv = layout.findViewById<TextView>(R.id.txtVw)
        tv.text = "You don't have any notifications"

        val toast = Toast(this)
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100)
        toast.duration = Toast.LENGTH_LONG
        toast.view = layout
        toast.show()
    }
}

/*
Options
-----
1. Splash screen [DONE]
2. Toolbar [DONE]
3. Rating bar [DONE]
4. Alarm manager
5. Receiver
6. Progress bar [DONE]
7. Custom Toast [DONE]
8. Scroll view [DONE]
9. Intent [DONE]
10. Pending intent
*/