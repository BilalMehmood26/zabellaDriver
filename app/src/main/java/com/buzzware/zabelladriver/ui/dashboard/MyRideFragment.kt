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
import com.buzzware.zabelladriver.R
import com.buzzware.zabelladriver.databinding.FragmentMyRideBinding
import com.buzzware.zabelladriver.ui.activity.YourDestnationActivity
import com.buzzware.zabelladriver.ui.adapter.NewRideAdapter
import com.buzzware.zabelladriver.ui.model.Booking
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MyRideFragment : Fragment() {

    private val binding: FragmentMyRideBinding by lazy {
        FragmentMyRideBinding.inflate(layoutInflater)
    }

    //private val db = Firebase.firestore
    private lateinit var fragmentContext: Context
    private val rideAcceptedList: ArrayList<Booking> = arrayListOf()
    private val rideCompletedList: ArrayList<Booking> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.apply {
            getNewRide()
            setListener()
            rideAcceptAdapter()
        }

        return binding.root
    }

    private fun rideAcceptAdapter() {
        binding.apply {
            rideRV.layoutManager = LinearLayoutManager(fragmentContext)
            rideRV.adapter = NewRideAdapter(fragmentContext, arrayListOf()) {

                val intent  = Intent(fragmentContext, YourDestnationActivity::class.java)
                //intent.putExtra("booking", booking)
                startActivity(intent)
            }
        }
    }

    private fun rideCompletedAdapter() {
        binding.apply {
            rideRV.layoutManager = LinearLayoutManager(fragmentContext)
            rideRV.adapter = NewRideAdapter(fragmentContext, arrayListOf()) {

            }
        }
    }

    private fun getNewRide() {
      //  binding.progressBar.visibility = View.VISIBLE
     /*   db.collection("Bookings").addSnapshotListener { value, error ->

            if (error != null) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(fragmentContext, "${error!!.message}", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            rideAcceptedList.clear()
            rideCompletedList.clear()
            value!!.forEach { booking ->
                val bookings = booking.toObject(Booking::class.java)
                if (bookings.status != "booked" && bookings.status != "rideCompleted" && bookings.status != "dispute" && bookings.status != "rated") {
                    rideAcceptedList.add(bookings)
                }else if(bookings.status == "rideCompleted" || bookings.status == "rated"){
                    rideCompletedList.add(bookings)
                }
            }
            binding.progressBar.visibility = View.GONE
            rideAcceptAdapter()
        }*/
    }

    private fun setListener(){

        binding.apply {
            rideAccept.setOnClickListener {
                rideAccept.setBackgroundResource(R.drawable.gradient_button)
                completeRide.setBackgroundResource(0)
                rideAcceptAdapter()
            }

            completeRide.setOnClickListener {
                completeRide.setBackgroundResource(R.drawable.gradient_button)
                rideAccept.setBackgroundResource(0)
                rideCompletedAdapter()
            }
        }
    }

    private fun formatDateTime(millis: Long): String {
        val formatter = SimpleDateFormat("MMMM dd, yyyy 'at' hh:mm a", Locale.ENGLISH)
        val date = Date(millis)
        return formatter.format(date)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }

}