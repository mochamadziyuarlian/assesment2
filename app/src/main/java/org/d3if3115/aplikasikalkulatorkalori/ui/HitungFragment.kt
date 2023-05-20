package org.d3if3115.aplikasikalkulatorkalori.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if3115.aplikasikalkulatorkalori.R
import org.d3if3115.aplikasikalkulatorkalori.databinding.FragmentHitungBinding
import org.d3if3115.aplikasikalkulatorkalori.db.KaloriDb
import org.d3if3115.aplikasikalkulatorkalori.model.KategoriKalori

class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = KaloriDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHitungBinding.inflate((layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { HitungKalori() }
        binding.saranButton.setOnClickListener { viewModel.mulaiNavigasi() }
        binding.shareButton.setOnClickListener { shareData() }
        viewModel.getHasilKategori().observe(requireActivity(), { showResult(it) })
        viewModel.getNavigasi().observe(viewLifecycleOwner) {
            if (it == null) return@observe
            findNavController().navigate(
                HitungFragmentDirections.actionHitungFragmentToSaranFragment(it)
            )
            viewModel.selesaiNavigasi()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_historiFragment
                )
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment
                )
                return true
            }
        }
        return super.onOptionsItemsSelected(item)
    }

    private fun HitungKalori() {
        val berat = binding.beratEditText.toString()
        if (TextUtils.isEmpty(berat)) {
            Toast.makeText(context, "Berat badan tidak boleh kosong", Toast.LENGTH_LONG).show()
            return
        }

        val tinggi = binding.tinggiEditText.text.toString()
        if (TextUtils.isEmpty(tinggi)) {
            Toast.makeText(
                context, "Tinggi badan tidak boleh kosong", Toast.LENGTH_LONG
            ).show()
            return
        }

        val selectedId = binding.radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(context, "Pilih jenias kelamin", Toast.LENGTH_LONG).show()
            return
        }
        viewModel.HitungKalori(
            berat.toFloat(),
            tinggi.toFloat(),
            selectedId == R.id.priaRadioButton
        )
    }


