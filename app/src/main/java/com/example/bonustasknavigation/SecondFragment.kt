package com.example.bonustasknavigation
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bonustasknavigation.R
import com.example.bonustasknavigation.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        // Restore the count if it was saved
        savedInstanceState?.let {
            count = it.getInt("COUNTER_KEY", 0) // Default value is 0 if not saved
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the initial count value
        binding.number.text = count.toString()

        binding.counter.setOnClickListener {
            count++
            binding.number.text = count.toString()
        }

        binding.clear.setOnClickListener {
            count = 0
            binding.number.text = count.toString()
        }
    }

    // Save the count when the fragment's state is saved
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("COUNTER_KEY", count)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
