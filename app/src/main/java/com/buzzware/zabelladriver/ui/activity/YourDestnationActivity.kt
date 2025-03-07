package com.buzzware.zabelladriver.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.buzzware.zabelladriver.R
import com.buzzware.zabelladriver.databinding.ActivityYourDestnationBinding
import com.buzzware.zabelladriver.ui.model.Booking
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class YourDestnationActivity : AppCompatActivity() {

    private val binding: ActivityYourDestnationBinding by lazy {
        ActivityYourDestnationBinding.inflate(layoutInflater)
    }

    private var myGoogleMap: GoogleMap? = null
    private var driverName = "--"
    private var pickUpLat = 0.0
    private var pickUpLng = 0.0
    private val REQUEST_CODE = 1000

    private var toID = ""
    private var messageId = ""

    private var driverPhoneNumber = ""

    private var rideID: String? = ""
    private var driverCarType: String? = ""
   // private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }

            phoneBtn.setOnClickListener {
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:$driverPhoneNumber")
                if (driverPhoneNumber.isNotEmpty()) {
                    startActivity(dialIntent)
                } else {
                    Toast.makeText(
                        this@YourDestnationActivity,
                        "Phone Number Not Available",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


         /*   db.collection("Bookings").document(booking.id!!).addSnapshotListener { value, error ->

                if(error!=null){
                    Toast.makeText(this@YourDestnationActivity, "${error.message.toString()}", Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }

                val booking = value!!.toObject(Booking::class.java)
                booking?.apply {

                    db.collection("Users").document(userId!!).get().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = task.result.toObject(User::class.java)
                            nameTv.text = user!!.userName
                            ratingTv.text = user.totalRating.toString()
                            driverPhoneNumber = user.phoneNumber!!
                            if (user.image!!.isNotEmpty()) {
                                Glide.with(this@YourDestnationActivity).load(user.image).into(profileIV)
                            } else {
                                profileIV.setImageResource(R.drawable.main_logo)
                            }
                        } else {
                            Log.d("LOGGER", "is Fail")
                            Toast.makeText(
                                this@YourDestnationActivity,
                                task.exception!!.message.toString(),
                                Toast
                                    .LENGTH_SHORT
                            ).show()
                        }
                    }
                    driverCarType = carType
                    timeDateTv.text = formatDateTime(bookingDate!!)
                    distanceTv.text = distance
                    pickUpTv.text = pickUp!!.address
                    dropoffTv.text  = destinations[0].address
                    priceTv.text = "$ $price"
                    statusTv.text = status
                    nameTv.text = driverName

                    when(status){
                        "driverAccepted" ->{
                            markCompleteBtn.setText("Arrived")
                        }

                        "driverReached" ->{
                            markCompleteBtn.setText("Start Ride")
                        }

                        "rideStarted" ->{
                            markCompleteBtn.setText("Complete Ride")
                        }
                    }

                    markCompleteBtn.setOnClickListener {
                        when(status){
                            "driverAccepted" ->{
                                updateRideStatus("driverReached",id!!)
                            }

                            "driverReached" ->{
                                updateRideStatus("rideStarted",id!!)
                            }

                            "rideStarted" ->{
                                updateRideCompletedStatus("rideCompleted",id!!)
                            }
                        }
                    }

                    msgBtn.setOnClickListener {
                        startMessage()
                    }
                }
            }*/
        }
    }


    private fun setIcon(context: Activity, drawableID: Int): BitmapDescriptor {

        val drawable = ActivityCompat.getDrawable(context, drawableID)
        drawable!!.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private fun formatDateTime(millis: Long): String {
        val formatter = SimpleDateFormat("MMMM dd, yyyy 'at' hh:mm a", Locale.ENGLISH)
        val date = Date(millis)
        return formatter.format(date)
    }
}