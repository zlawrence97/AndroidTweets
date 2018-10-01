package com.example.owner.androidtweets

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.gwu.android.androidtweets.Tweet
import com.owner.example.androidtweets.R
import android.widget.ArrayAdapter
import android.widget.Toast

class TweetsActivity : AppCompatActivity(), TweetsAdapter.OnRowClickListener{
    companion object {
        val INTENT_KEY_LOCATION = "LOCATION_NAME"
    }

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweets)
        val locationName = intent.getStringExtra(INTENT_KEY_LOCATION)
        title = getString(R.string.tweet_title, locationName)
        val tweets: List<Tweet> = listOf(
                Tweet("nickcapurso", "@nickcapurso", "We're learning lists!", "https://...."),
                Tweet("nickcapurso", "@nickcapurso", "We're learning lists!", "https://...."),
                Tweet("nickcapurso", "@nickcapurso", "We're learning lists!", "https://...."),
                Tweet("nickcapurso", "@nickcapurso", "We're learning lists!", "https://....")
        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = TweetsAdapter(getFakeTweets(), this)
    }

    override fun onRowItemClicked(tweet: Tweet){
        // Data
        val choicesList = listOf("A","B","C")

        //Adapts the data to a UI
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice)
        arrayAdapter.addAll(choicesList)

        AlertDialog.Builder(this).setTitle("Choose One").setAdapter(arrayAdapter){
            dialog, index -> Toast.makeText(this, "You picked ${choicesList[index]}", Toast.LENGTH_SHORT).show()
        }.setNegativeButton("CANCEL"){ dialog, id -> dialog.dismiss()}.show()
    }

    private fun getFakeTweets(): List<Tweet> = listOf()

}