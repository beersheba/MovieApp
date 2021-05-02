package me.rankov.movieapp.adapter

import coil.load
import me.rankov.movieapp.Const

class NowPlayingAdapter: MovieAdapter() {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentMovie = movies[position]
        holder.binding.apply {
            textView.text = currentMovie.title
            imageView.layoutParams.height = 500
            imageView.layoutParams.width = 900
            imageView.load("${Const.IMAGE_URL}${currentMovie.backdrop_path}") {
                crossfade(true)
                crossfade(1000)
            }
        }
    }
}