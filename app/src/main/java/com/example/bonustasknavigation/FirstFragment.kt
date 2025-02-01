package com.example.bonustasknavigation

import Stopwatch
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.bonustasknavigation.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private lateinit var quoteTextView: TextView
    private lateinit var newQuoteButton: Button

    private val quotes = listOf(
        "The best way to predict the future is to create it.",
        "I can't breathe.",
        "Believe in yourself and all that you are.",
        "Success is not the key to happiness. Happiness is the key to success.",
        "You are never too old to set another goal or to dream a new dream."
    )

    private var currentQuote: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentQuote = getRandomQuote()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout correctly
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        // Find views
        quoteTextView = view.findViewById(R.id.quote)
        newQuoteButton = view.findViewById(R.id.start)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            currentQuote = savedInstanceState.getString("QUOTE")
        }
        quoteTextView.text = currentQuote


        // Set button listener
        newQuoteButton.setOnClickListener {
            currentQuote = getRandomQuote()
            quoteTextView.text = currentQuote
        }
    }

    private fun getRandomQuote(): String {
        return quotes.random()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("QUOTE", currentQuote)
    }
}
