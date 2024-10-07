package id.overlogic.comina.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.overlogic.comina.databinding.ItemAnimeBinding
import id.overlogic.comina.model.Anime

class ListAnimeAdapter(private val listAnime: ArrayList<Anime>): RecyclerView.Adapter<ListAnimeAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(val binding: ItemAnimeBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listAnime.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, rating, author, releaseDate ,image, genres, description, episodes, status, duration) = listAnime[position]

        holder.binding.imgImage.setImageResource(image)
        holder.binding.tvTitle.text = title
        holder.binding.tvRating.text = rating.toString()
        holder.binding.tvAuthor.text = author
        holder.binding.tvDescription.text = description

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClick(listAnime[holder.adapterPosition])
        }
    }

    interface OnItemClickCallback {
        fun onItemClick(data: Anime)
    }
}