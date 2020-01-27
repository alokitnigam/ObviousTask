package com.example.obvious.Views.Adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.obvious.DI.Models.PodModel
import com.example.obvious.R
import com.example.obvious.Views.ImageDetailsActivity


class ImageAdapter: RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    var boardItemSize:Int = 0
    private var list: ArrayList<PodModel> = ArrayList()

    fun setList(list: List<PodModel>)
    {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class ImageViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(podModel: PodModel) {
            val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)

            Glide.with(itemView.context).load(podModel.url).apply(requestOptions).into(podImage)
            title.text = podModel.title
            itemView.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context,ImageDetailsActivity::class.java)
                    .putExtra("position",adapterPosition))

            }


        }

        private val podImage: ImageView = itemView.findViewById(R.id.imageView)
        private val title: TextView = itemView.findViewById(R.id.title)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.pod_view, parent, false)
        val lp = view!!.layoutParams
        lp.height = (boardItemSize*1.6  ).toInt()

        view.layoutParams = lp


        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(list.get(position));
    }

    fun setBoardItemSizeInAdapter(boardItemSize: Int) {
        this.boardItemSize = boardItemSize
    }

}

