package com.example.hepubliccontent.presentation.adapter

import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hepubliccontent.data.model.ApiData.Data
import com.example.hepubliccontent.databinding.ItemPublicContentNewsBinding

class PublicContentAdapter : RecyclerView.Adapter<PublicContentAdapter.PublicContentViewHolder>() {
    private val contentList = ArrayList<Data>()

    fun setList(content: List<Data>) {
        contentList.clear()
        contentList.addAll(content)
    }

    inner class PublicContentViewHolder(private val binding: ItemPublicContentNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(content: Data){
                binding.apply {
                    try {
                        if (content.image != null) {
                            if (content.image.imageUrl != null) {
                                imageCardView.visibility = View.VISIBLE
                                Glide.with(image.context)
                                    .load(content.image.imageUrl)
                                    .into(image)
                            }
                            playBtn.visibility = View.GONE
                        } else if (content.video != null) {
                            if (content.video.thumbnailUrl != null) {
                                imageCardView.visibility = View.VISIBLE
                                Glide.with(image.context)
                                    .load(content.video.thumbnailUrl)
                                    .into(image)
                                playBtn.visibility = View.VISIBLE
                            }
                        } else {
                            imageCardView.visibility = View.GONE
                            playBtn.visibility = View.GONE
                        }
                        title.text = content.title
                      /*  var date = ""
                        if (!TextUtils.isEmpty(content.getPublishedOn())) {
                            date = DateUtils.parseIso8602DateTimeForPublicContent(
                                content.getPublishedOn(),
                                DateUtils.DATE_FORMAT_POST_PUBLIC_DISPLAY
                            )
                        }
                        date.setText(date)*/
                    } catch (e: Exception) {
                        Log.d("PublicContent", "Error: " + e.message)
                    }
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicContentViewHolder {
        val binding =
            ItemPublicContentNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PublicContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PublicContentViewHolder, position: Int) {
        val content = contentList[position]
        holder.bind(content)
    }

    override fun getItemCount(): Int {
        return contentList.size
    }
}