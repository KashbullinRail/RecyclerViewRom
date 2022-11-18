package com.example.recyclerviewrom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewrom.databinding.ItemUserBinding
import com.example.recyclerviewrom.model.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    var users: List<User> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class UsersViewHolder(
        val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
       val user = users[position]
        with(holder.binding){
            tvName.text = user.name
            tvCompany.text = user.company
            if (user.photo.isNotBlank()){
                Glide.with(ivPhoto.context)
                    .load(user.photo)
                    .circleCrop()
                    .placeholder(R.drawable.ic_person_24)
                    .error(R.drawable.ic_person_24)
                    .into(ivPhoto)
            } else {
                ivPhoto.setImageResource(R.drawable.ic_person_24)
            }
        }
    }


}