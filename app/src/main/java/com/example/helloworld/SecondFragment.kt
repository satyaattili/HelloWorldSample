package com.example.helloworld

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.helloworld.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val friends = arrayListOf<Friend>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cursor: Cursor? = activity?.let { it1 -> DBHelper(it1, null) }?.getFriendsFromSlamBook()

        cursor!!.moveToFirst()

        while (cursor.moveToNext()) {
            val friend = Friend(
                cursor.getString(cursor.getColumnIndex(DBHelper.FRIEND_NAME)),
                cursor.getString(cursor.getColumnIndex(DBHelper.FRIEND_DOB)),
                cursor.getString(cursor.getColumnIndex(DBHelper.FRIEND_PHONE)),
                cursor.getString(cursor.getColumnIndex(DBHelper.FRIEND_HOBBY))
            )
            friends.add(friend)
        }


        val friendsAdapter = activity?.let { FriendsAdapter(friends, it) }
        binding.friendsList.adapter = friendsAdapter


        cursor.close()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}