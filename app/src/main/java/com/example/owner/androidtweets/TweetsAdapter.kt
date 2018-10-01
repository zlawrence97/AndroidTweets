package com.example.owner.androidtweets

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gwu.android.androidtweets.Tweet
import com.owner.example.androidtweets.R

// 1. ViewHolder to hold references to views (done)
// 2. Extend the Adapter class (done)
// 3. Implement the Adapter methods
class TweetsAdapter(
        private val tweets: List<Tweet>,
        private val rowClickListener: OnRowClickListener
) : RecyclerView.Adapter<TweetsAdapter.ViewHolder>() {
    interface OnRowClickListener {
        fun onRowItemClicked(tweet: Tweet)
    }
    // RecyclerView wants to render a new row that hasn't been created before
    // Load the XML layout and return a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflater is an object that loads & parses XML, you get it from a Context
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_listitem_tweet, parent, false)
        return ViewHolder(view)
    }
    // How many total rows to render in your list
    override fun getItemCount(): Int {
        return tweets.size
    }
    // List is ready to render a row at position and it gives you the ViewHolder
    // So you just fill it with content
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTweet = tweets[position]
        holder.usernameTextView.text = currentTweet.username
        holder.handleTextView.text = currentTweet.handle
        holder.contentTextView.text = currentTweet.content
        holder.cardView.setOnClickListener {
            rowClickListener.onRowItemClicked(currentTweet)
        }
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: CardView = view.findViewById(R.id.cardView)
        val usernameTextView: TextView = view.findViewById(R.id.username)
        val handleTextView: TextView = view.findViewById(R.id.handle)
        val contentTextView: TextView = view.findViewById(R.id.tweet_content)
        val iconImageView: ImageView = view.findViewById(R.id.icon)
    }
}