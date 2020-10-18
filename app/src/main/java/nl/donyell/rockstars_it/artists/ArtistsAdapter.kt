package nl.donyell.rockstars_it.artists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_artist.view.*
import nl.donyell.presentation.artists.ArtistUIModel
import nl.donyell.rockstars_it.R

class ArtistsAdapter: ListAdapter<ArtistUIModel, RecyclerView.ViewHolder>(ArtistDiffCallback()) {

    var onArtistClick: ((ArtistUIModel) -> Unit?)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item_artist, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val artist = getItem(position)
        (holder as? ViewHolder)?.bind(artist)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(artist: ArtistUIModel) {
            itemView.run {
                list_item_artist_name.text = artist.name
                setOnClickListener { onArtistClick?.invoke(artist) }
            }
        }
    }

}

private class ArtistDiffCallback: DiffUtil.ItemCallback<ArtistUIModel>() {
    override fun areItemsTheSame(oldItem: ArtistUIModel, newItem: ArtistUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ArtistUIModel, newItem: ArtistUIModel): Boolean {
        return oldItem == newItem
    }

}
