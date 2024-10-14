package com.example.instagram_v3.ui.fragments

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.marginStart
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.instagram_v3.R
import com.example.instagram_v3.databinding.FragmentListaPersonasBinding
import com.example.instagram_v3.ui.adapters.FotosPagerAdapter
import com.example.instagram_v3.ui.viewmodels.PersonasViewModel
import com.google.android.material.tabs.TabLayoutMediator

class ListaPersonasFragment : Fragment() {

    private var _binding: FragmentListaPersonasBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PersonasViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaPersonasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val fotosAdapter = FotosPagerAdapter(this)// Configurando el ViewPager con las fotos
        binding.viewPager.adapter = fotosAdapter

        viewModel.listaPersonas.observe(viewLifecycleOwner) { personas ->
            fotosAdapter.updateFotos(personas[0].fotos, personas[0].nombre) // Actualizando el adapter con las fotos de la primera persona
            setupIndicator(personas[0].fotos.size)
        }

        binding.btnMisLikes.setOnClickListener {
            // navegar al fragmento MisLikes
            findNavController().navigate(R.id.misLikesFragment)
        }

        // Configurando los botones like y dislike
        binding.btnLike.setOnClickListener {
            viewModel.likeActual()  // Añade la persona a la lista de likes
        }

        binding.btnDislike.setOnClickListener {
            viewModel.dislikeActual()
        }


        viewModel.personaActual.observe(viewLifecycleOwner) { persona ->  // Observando el cambio de persona actual
            fotosAdapter.updateFotos(persona.fotos, persona.nombre)
            setupIndicator(persona.fotos.size)
        }

        // cambio de página en el ViewPager
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicator(position)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupIndicator(count: Int) {
        val indicatorLayout = binding.indicator
        indicatorLayout.removeAllViews()

        val indicatorWidth = Resources.getSystem().displayMetrics.widthPixels
        val squareWidth = indicatorWidth / count // Ancho proporcional

        // creamos un cuadrado por cada imagen
        for (i in 0 until count) {
            val square = View(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    squareWidth,
                    20
                ).apply {
                    marginStart = 4
                    marginEnd = 4
                }
                setBackgroundColor(Color.GRAY)
            }
            indicatorLayout.addView(square)
        }
    }

    private fun updateIndicator(selectedPosition: Int) {
        val indicatorLayout = binding.indicator
        for (i in 0 until indicatorLayout.childCount) {
            val square = indicatorLayout.getChildAt(i)
            square.setBackgroundColor(if (i == selectedPosition) Color.BLACK else Color.GRAY)
        }
    }
}
