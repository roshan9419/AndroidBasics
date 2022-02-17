package com.lpu.cse225ca1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.squareup.picasso.Picasso
import kotlin.math.ceil

class CardDetailActivity : AppCompatActivity() {

    private lateinit var ratingBar: RatingBar
    private lateinit var submitBtn: Button
    private val ratingValues = arrayOf("Very Poor", "Poor", "Average", "Good", "Excellent")
    private var submittedRating: Float = -1.0f
    private var itemId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_detail)

        ratingBar = findViewById(R.id.ratingBar)
        submitBtn = findViewById(R.id.submitBtn)

        loadDetails()

        // RATING BAR IMPLEMENTATION
        ratingBar.setOnRatingBarChangeListener { _, value, _ ->
            val index = ceil(value.toDouble()).toInt() - 1
            findViewById<TextView>(R.id.ratingValueTV).text =
                ratingValues[if (index < 0) 0 else index]
        }

        submitBtn.setOnClickListener {
            submittedRating = ratingBar.rating
            if (submittedRating != -1.0f)
                showCustomToast("Thanks for giving rating!")
        }

        val toolbar = findViewById<Toolbar>(R.id.cardDetailToolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    // SENDING RESPONSE BACK TO MAIN ACTIVITY IMPLEMENTATION
    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("id", itemId)
        intent.putExtra("rating", submittedRating)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }

    // LOADING IMAGE AND SETTING RATING VALUE IF PREVIOUSLY SUBMITTED
    private fun loadDetails() {
        val data = intent.extras!!
        itemId = data.getInt("id", itemId)
        val title = data.getString("title", "Image Detail")
        val url = data.getString("imageUrl", "")
        submittedRating = data.getFloat("rating", -1.0f)

        Picasso.get().load(url).resize(750, 360).centerCrop()
            .into(findViewById<ImageView>(R.id.imageIV))

        if (submittedRating != -1.0f) {
            ratingBar.rating = submittedRating
            val index = ceil(submittedRating.toDouble()).toInt() - 1
            findViewById<TextView>(R.id.ratingValueTV).text =
                ratingValues[if (index < 0) 0 else index]
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.card_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.cardMenuShare -> {
                Toast.makeText(this, "Share", Toast.LENGTH_LONG).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showCustomToast(msg: String) {
        val vg = findViewById<ViewGroup>(R.id.custom_toast)
        val inflater = layoutInflater
        val layout: View = inflater.inflate(R.layout.custom_toast_layout, vg)

        val tv = layout.findViewById<TextView>(R.id.txtVw)
        tv.text = msg

        val toast = Toast(this)
        toast.setGravity(Gravity.BOTTOM, 0, 20)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()
    }
}