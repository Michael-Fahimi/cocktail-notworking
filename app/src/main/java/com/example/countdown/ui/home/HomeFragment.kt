package com.example.countdown.ui.home
import HomeViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countdown.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // init ViewModel
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // intit RecyclerView
        adapter = HomeAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Observers??
        homeViewModel.items.observe(viewLifecycleOwner) { items ->
            adapter.updateItems(items)
        }

// search button
            binding.searchButton.setOnClickListener {
            val letter = binding.inputLetter.text.toString().trim()
            if (letter.isNotEmpty()) {
                homeViewModel.fetchCocktailsByLetter(letter)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}