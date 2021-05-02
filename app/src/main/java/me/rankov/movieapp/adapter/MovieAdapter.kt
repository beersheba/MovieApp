package me.rankov.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import me.rankov.movieapp.Const.IMAGE_URL
import me.rankov.movieapp.data.Movie
import me.rankov.movieapp.databinding.MovieLayoutAdapterBinding

open class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: MovieLayoutAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var movies: List<Movie>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            MovieLayoutAdapterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentMovie = movies[position]
        holder.binding.apply {
            textView.text = currentMovie.title
            imageView.layoutParams.height = 500
            imageView.layoutParams.width = 350
            imageView.load("$IMAGE_URL${currentMovie.poster_path}") {
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun getItemCount() = movies.size

}





















