package com.example.instagram_v3.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instagram_v3.models.Persona

class PersonasViewModel : ViewModel() {

    private val _listaPersonas = MutableLiveData<List<Persona>>()
    val listaPersonas: LiveData<List<Persona>> = _listaPersonas

    private val _personaActual = MutableLiveData<Persona>()
    val personaActual: LiveData<Persona> = _personaActual

    private val _misLikes = MutableLiveData<List<Persona>>()
    val misLikes: LiveData<List<Persona>> = _misLikes

    init {
        _listaPersonas.value = listOf(
            Persona(
                nombre = "Juan Pérez",
                fotos = listOf(
                    "android.resource://com.example.instagram_v3/drawable/persona1_foto1",
                    "android.resource://com.example.instagram_v3/drawable/persona1_foto2",
                    "android.resource://com.example.instagram_v3/drawable/persona1_foto3"
                )
            ),
            Persona(
                nombre = "Ana Gómez",
                fotos = listOf(
                    "android.resource://com.example.instagram_v3/drawable/persona2_foto1",
                    "android.resource://com.example.instagram_v3/drawable/persona2_foto2",
                    "android.resource://com.example.instagram_v3/drawable/persona2_foto3"
                )
            ),
            Persona(
                nombre = "Yessica Smith",
                fotos = listOf(
                    "android.resource://com.example.instagram_v3/drawable/persona3_foto1",
                    "android.resource://com.example.instagram_v3/drawable/persona3_foto2",
                    "android.resource://com.example.instagram_v3/drawable/persona3_foto3"
                )
            ),
            Persona(
                nombre = "María Martínez",
                fotos = listOf(
                    "android.resource://com.example.instagram_v3/drawable/persona4_foto1",
                    "android.resource://com.example.instagram_v3/drawable/persona4_foto2",
                    "android.resource://com.example.instagram_v3/drawable/persona4_foto3"
                )
            ),
            Persona(
                nombre = "Pedro Rodríguez",
                fotos = listOf(
                    "android.resource://com.example.instagram_v3/drawable/persona5_foto1",
                    "android.resource://com.example.instagram_v3/drawable/persona5_foto2",
                    "android.resource://com.example.instagram_v3/drawable/persona5_foto3"
                )
            ),
            Persona(
                nombre = "Juancito Pinto",
                fotos = listOf(
                    "android.resource://com.example.instagram_v3/drawable/persona6_foto1",
                    "android.resource://com.example.instagram_v3/drawable/persona6_foto2",
                    "android.resource://com.example.instagram_v3/drawable/persona6_foto3"
                )
            ),
            Persona(
                nombre = "Laura Fernández",
                fotos = listOf(
                    "android.resource://com.example.instagram_v3/drawable/persona7_foto1",
                    "android.resource://com.example.instagram_v3/drawable/persona7_foto2",
                    "android.resource://com.example.instagram_v3/drawable/persona7_foto3"
                )
            ),
            Persona(
                nombre = "Carlos López",
                fotos = listOf(
                    "android.resource://com.example.instagram_v3/drawable/persona8_foto1",
                    "android.resource://com.example.instagram_v3/drawable/persona8_foto2",
                    "android.resource://com.example.instagram_v3/drawable/persona8_foto3"
                )
            ),
            Persona(
                nombre = "Sofía Ramírez",
                fotos = listOf(
                    "android.resource://com.example.instagram_v3/drawable/persona9_foto1",
                    "android.resource://com.example.instagram_v3/drawable/persona9_foto2",
                    "android.resource://com.example.instagram_v3/drawable/persona9_foto3"
                )
            ),
            Persona(
                nombre = "Diego Torres",
                fotos = listOf(
                    "android.resource://com.example.instagram_v3/drawable/persona10_foto1",
                    "android.resource://com.example.instagram_v3/drawable/persona10_foto2",
                    "android.resource://com.example.instagram_v3/drawable/persona10_foto3"
                )
            ),
            Persona(
                nombre = "Lucía Morales",
                fotos = listOf(
                    "android.resource://com.example.instagram_v3/drawable/persona11_foto1",
                    "android.resource://com.example.instagram_v3/drawable/persona11_foto2",
                    "android.resource://com.example.instagram_v3/drawable/persona11_foto3"
                )
            ),
            Persona(
                nombre = "Miguel García",
                fotos = listOf(
                    "android.resource://com.example.instagram_v3/drawable/persona12_foto1",
                    "android.resource://com.example.instagram_v3/drawable/persona12_foto2",
                    "android.resource://com.example.instagram_v3/drawable/persona12_foto3"
                )
            ),
            Persona(
                nombre = "Elena Ruiz",
                fotos = listOf(
                    "android.resource://com.example.instagram_v3/drawable/persona13_foto1",
                    "android.resource://com.example.instagram_v3/drawable/persona13_foto2",
                    "android.resource://com.example.instagram_v3/drawable/persona13_foto3"
                )
            ),
            Persona(
                nombre = "Luis Vargas",
                fotos = listOf(
                    "android.resource://com.example.instagram_v3/drawable/persona14_foto1",
                    "android.resource://com.example.instagram_v3/drawable/persona14_foto2",
                    "android.resource://com.example.instagram_v3/drawable/persona14_foto3"
                )
            ),
            Persona(
                nombre = "Valentina Castro",
                fotos = listOf(
                    "android.resource://com.example.instagram_v3/drawable/persona15_foto1",
                    "android.resource://com.example.instagram_v3/drawable/persona15_foto2",
                    "android.resource://com.example.instagram_v3/drawable/persona15_foto3"
                )
            )

        )
        _personaActual.value = _listaPersonas.value?.firstOrNull()
    }

    fun likeActual() {
        val likes = _misLikes.value?.toMutableList() ?: mutableListOf()
        _personaActual.value?.let { likes.add(it) }
        _misLikes.value = likes
        removeCurrentPerson()
    }

    fun dislikeActual() {
        removeCurrentPerson()
    }

    private fun removeCurrentPerson() {
        val currentPersona = _personaActual.value ?: return
        val updatedList = _listaPersonas.value?.toMutableList() ?: return

        updatedList.remove(currentPersona)

        _listaPersonas.value = updatedList

        if (updatedList.isEmpty()) {
            _personaActual.value = null
        } else {
            _personaActual.value = updatedList.firstOrNull()
        }
    }
}
