package com.example.obvious.Views.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.obvious.DI.Models.PodModel
import com.example.obvious.R
import com.example.obvious.Views.ImageDetailsActivity


class ImageDetailsAdapter: RecyclerView.Adapter<ImageDetailsAdapter.ImageViewHolder>() {
    private var pagerItemClickListner: ImageDetailsAdapter.OnPagerItemClickListner?=null
    private var list: ArrayList<PodModel> = ArrayList()  //List of Pods

    fun setList(list: List<PodModel>)
    {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.pod__details_view, parent, false)

        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(list.get(position))
        holder.itemView.setOnClickListener {
            pagerItemClickListner?.onPagerItemClicked()
        }
    }

    fun setItemClickListner(listner: OnPagerItemClickListner) {
        this.pagerItemClickListner=listner
    }

    class ImageViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(podModel: PodModel) {
            val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)

            Glide.with(itemView.context).load(podModel.hdurl).apply(requestOptions)
                .thumbnail(Glide.with(itemView.context).load(podModel.url)).into(podImage)

        }

        private val podImage: ImageView = itemView.findViewById(R.id.imageView)
    }

    interface OnPagerItemClickListner {
        fun onPagerItemClicked()
    }
}

