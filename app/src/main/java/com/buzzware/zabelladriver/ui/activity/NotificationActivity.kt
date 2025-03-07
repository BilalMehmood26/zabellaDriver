package com.buzzware.zabelladriver.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.buzzware.zabelladriver.R
import com.buzzware.zabelladriver.databinding.ActivityNotificationBinding
import com.buzzware.zabelladriver.ui.adapter.NotificationAdapter
import com.buzzware.zabelladriver.ui.model.Notification

class NotificationActivity : AppCompatActivity() {

    private val binding: ActivityNotificationBinding by lazy {
        ActivityNotificationBinding.inflate(layoutInflater)
    }

    //private val db = Firebase.firestore
    private val notificationList : ArrayList<Notification> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
        }
        getNotification()
        setAdapter()

    }

    private fun getNotification(){
        //binding.progressBar.visibility = View.VISIBLE
      /*  db.collection("Notification").get().addOnCompleteListener { task->
            if(task.isSuccessful){
                binding.progressBar.visibility = View.GONE
                val notification = task.result.toObjects(Notification::class.java)
                notification.forEach {
                    if(it.userId.equals(Firebase.auth.currentUser!!.uid)){
                        notificationList.add(it)
                    }
                }
                setAdapter()
            }else{
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }*/
    }

    private fun setAdapter() {
        binding.notificationRv.layoutManager = LinearLayoutManager(this@NotificationActivity)
        binding.notificationRv.adapter = NotificationAdapter(this@NotificationActivity,
            arrayListOf()
        )
    }
}