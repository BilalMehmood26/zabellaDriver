package com.buzzware.zabelladriver.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.buzzware.zabelladriver.R
import com.buzzware.zabelladriver.databinding.FragmentHomeBinding
import com.buzzware.zabelladriver.ui.activity.YourDestnationActivity
import com.buzzware.zabelladriver.ui.adapter.NewRideAdapter
import com.buzzware.zabelladriver.ui.model.Booking


class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    //private val db = Firebase.firestore
    private lateinit var fragmentContext: Context
    private val newBookingList: ArrayList<Booking> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.apply {
       /*     userNameTv.text = UserSession.user.userName

            if (UserSession.user.image.equals("")) {
                profileIv.setImageResource(R.drawable.dummy_profile)
            } else {
                Glide.with(requireActivity()).load(UserSession.user.image).into(profileIv)
            }
            createVehicle()
            yourProfileBtn.setOnClickListener {
                startActivity(Intent(fragmentContext,EditProfileActivity::class.java))
            }
            getNewRide()*/
        }
        updateAdapter()

        return binding.root
    }

    private fun updateAdapter() {
        binding.apply {
            newRideRV.layoutManager = LinearLayoutManager(fragmentContext)
            newRideRV.adapter = NewRideAdapter(fragmentContext, arrayListOf()) {
                startActivity(Intent(fragmentContext,YourDestnationActivity::class.java))
              /*  val acceptRide = hashMapOf(
                    "driverId" to Firebase.auth.currentUser!!.uid,
                    "driverLat" to UserSession.user.lat,
                    "driverLng" to UserSession.user.lng,
                    "status" to "driverAccepted",
                    "vehicleId" to ""
                )
                db.collection("Bookings").document(booking.id!!).update(acceptRide as Map<String, Any>).addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Toast.makeText(fragmentContext, "Accepted", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(fragmentContext, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }*/
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }
}