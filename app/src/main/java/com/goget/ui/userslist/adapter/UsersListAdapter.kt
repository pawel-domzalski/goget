package com.goget.ui.userslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.goget.R
import com.goget.databinding.ItemUserBinding
import com.goget.logic.dataaccess.model.User
import io.reactivex.subjects.PublishSubject


class UsersListAdapter(val longClicks : PublishSubject<User>) : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    private var list : List<User> = ArrayList()


    fun updateModel(model : List<User>) {
        list = model
    }

    class ViewHolder(val binding: ItemUserBinding, val longClicks : PublishSubject<User>) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.item = user

            binding.flClickableRoot.setOnLongClickListener {
                longClicks.onNext(user)
                true
            }

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val dataBinding = DataBindingUtil.inflate<ItemUserBinding>(layoutInflater, R.layout.item_user, parent, false)

        return ViewHolder(dataBinding, longClicks)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}