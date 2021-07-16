package com.example.helloworld

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.R
import org.w3c.dom.Text
import java.util.*

class UserAdapter(private val mContext: Context?, var mResource: Int, users: ArrayList<User?>?) : ArrayAdapter<User?>(
    mContext!!, mResource, users!!) {
    //getting the view and attach it to the ListView
    private var holder : UserViewHolder? = null;
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val name = getItem(position)!!.name
        val email = getItem(position)!!.email
        val phone = getItem(position)!!.phone
        val id = getItem(position)!!.id
        Integer.toString(id)

        val inflater = LayoutInflater.from(mContext)

        if (convertView == null) {
            convertView = inflater.inflate(mResource, parent, false)
            holder = UserViewHolder(convertView)
            convertView.tag = holder
        } else {
            holder = convertView.tag as UserViewHolder
        }

        val tvName = holder!!.name
        val tvId = holder!!.id
        val tvEmail = holder!!.email
        val tvPhone = holder!!.phone
        val idToSet = "ID: $id"
        tvId.text = idToSet
        val nameToSet = "Name: $name"
        tvName.text = nameToSet
        val emailToSet = "Email: $email"
        tvEmail.text = emailToSet
        val phoneToSet = "Phone: $phone"
        tvPhone.text = phoneToSet

        return convertView!!
    }
}

internal class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var name: TextView
    var id: TextView
    var email : TextView
    var phone : TextView

    init {
        name = view.findViewById<View>(R.id.name) as TextView
        id = view.findViewById<View>(R.id.id) as TextView
        email = view.findViewById<View>(R.id.email) as TextView
        phone = view.findViewById<View>(R.id.phone) as TextView

    }
}