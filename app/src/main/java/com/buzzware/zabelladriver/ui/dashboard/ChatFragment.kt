package com.buzzware.zabelladriver.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.buzzware.zabelladriver.R
import com.buzzware.zabelladriver.databinding.FragmentChatBinding
import com.buzzware.zabelladriver.ui.adapter.ChatAdapter
import com.buzzware.zabelladriver.ui.model.ChatModel


class ChatFragment : Fragment() {

    private val binding: FragmentChatBinding by lazy {
        FragmentChatBinding.inflate(layoutInflater)
    }

    //private val db = Firebase.firestore
    private lateinit var fragmentContext: Context
    private val chatList: ArrayList<ChatModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getChatUser()
        setChatAdapter()
        return binding.root
    }

    private fun getChatUser() {
       // binding.progressBar.visibility = View.VISIBLE
       /* db.collection("Chat").get().addOnSuccessListener { queryDocumentSnapshots ->
            for (document in queryDocumentSnapshots) {
                val lastMessage = document.get("lastMessage") as? Map<String, Any>
                val hasCurrentUser = lastMessage?.get("fromID") as? String
                val hasOpponentUser = lastMessage?.get("toID") as? String

                if (hasCurrentUser != null && hasCurrentUser == Firebase.auth.currentUser!!.uid) {
                    val chatModel = ChatModel(hasOpponentUser, hasCurrentUser, document.id)
                    chatList.add(chatModel)
                    Log.d("Logger", "getChatUser: ${chatList.size} ${document.id}")
                }
            }
            binding.progressBar.visibility = View.GONE
            setChatAdapter()
        }*/


    }

    private fun setChatAdapter() {
        Log.d("Logger", "setChatAdapter: adapter: ${chatList.size}")
        binding.chatRV.layoutManager = LinearLayoutManager(fragmentContext)
        binding.chatRV.adapter = ChatAdapter(fragmentContext, arrayListOf())
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }

}