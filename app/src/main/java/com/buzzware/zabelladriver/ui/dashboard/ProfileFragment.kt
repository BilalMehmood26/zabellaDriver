package com.buzzware.zabelladriver.ui.dashboard

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.buzzware.zabelladriver.R
import com.buzzware.zabelladriver.databinding.FragmentProfileBinding
import com.buzzware.zabelladriver.ui.activity.DriverInfoActivity
import com.buzzware.zabelladriver.ui.activity.EditAccountActivity
import com.buzzware.zabelladriver.ui.activity.NotificationActivity


class ProfileFragment : Fragment() {

    private val binding: FragmentProfileBinding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    private lateinit var fragmentContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setListener()

        return binding.root
    }

    private fun setListener() {

        binding.apply {

            /*nameTV.text = UserSession.user.userName
            if (UserSession.user.image.equals("")) {
                profileIV.setImageResource(R.drawable.chat_dummy)
            } else {
                Glide.with(requireActivity()).load(UserSession.user.image).into(profileIV)
            }*/

            accountLayout.setOnClickListener {
                startActivity(Intent(fragmentContext, EditAccountActivity::class.java))
                (fragmentContext as Activity).overridePendingTransition(
                    androidx.appcompat.R.anim.abc_fade_in,
                    androidx.appcompat.R.anim.abc_fade_out
                )
            }

            notificationLayout.setOnClickListener {
                startActivity(Intent(fragmentContext, NotificationActivity::class.java))
                (fragmentContext as Activity).overridePendingTransition(
                    androidx.appcompat.R.anim.abc_fade_in,
                    androidx.appcompat.R.anim.abc_fade_out
                )
            }

            driverLayout.setOnClickListener {
                startActivity(Intent(fragmentContext, DriverInfoActivity::class.java))
                (fragmentContext as Activity).overridePendingTransition(
                    androidx.appcompat.R.anim.abc_fade_in,
                    androidx.appcompat.R.anim.abc_fade_out
                )
            }

            availbilityLayout.setOnClickListener {
                showCustomDialog(fragmentContext)
            }

            deleteLayout.setOnClickListener {
              /*  progressBar.visibility = View.VISIBLE
                val user = Firebase.auth.currentUser!!

                user.delete().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        progressBar.visibility = View.GONE
                        requireActivity().finish()
                    } else {
                        progressBar.visibility = View.GONE
                        Log.d("Logger", "setListener: ${task.exception!!.message}")
                        Toast.makeText(
                            fragmentContext,
                            "${task.exception!!.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }*/
            }
        }

    }

    private fun showCustomDialog(context: Context) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_availability, null)
        val button = dialogView.findViewById<TextView>(R.id.set_btn)

        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .create()

        button.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }
}