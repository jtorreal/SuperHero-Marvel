package com.jtorreal.superheromarvel.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.josetorres.marvel.ui.detail.SuperHeroDetailViewModel
import com.jtorreal.superheromarvel.R
import com.jtorreal.superheromarvel.databinding.FragmentSuperheroDetailBinding
import com.jtorreal.superheromarvel.ui.UiResult
import com.jtorreal.superheromarvel.ui.common.loadUrl
import com.jtorreal.superheromarvel.ui.common.showOrHidden
import com.jtorreal.superheromarvel.ui.common.toast
import com.jtorreal.superheromarvel.ui.list.SuperHeroListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class SuperHeroDetailFragment : Fragment(), CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private var _binding: FragmentSuperheroDetailBinding? = null

    private val superHeroDetailViewModel by viewModels<SuperHeroDetailViewModel>()

    private val binding get() = _binding!!

    private var idSelectedCharacter: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        idSelectedCharacter =
            requireArguments().getString(SuperHeroListFragment.ID_CHARACTER_SELECTED)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSuperheroDetailBinding.inflate(inflater, container, false)

        idSelectedCharacter?.let {

            superHeroDetailViewModel.loadCharacterDetail(idSelectedCharacter)


        } ?: run {
            requireContext().toast(requireContext().resources.getString(R.string.superhero_detail_selected_error))
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            superHeroDetailViewModel.progressVisible.collect {
                delay(500)
                binding.pbDetailScreen.showOrHidden(it)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        job = SupervisorJob()

        launch {

            superHeroDetailViewModel.result.collect { it ->

                if (it.result != UiResult.LOADING) {

                    if (it.result == UiResult.OK && it.superHero != null) {

                        binding.tvTitleCharacterDetail.text = it.superHero.name

                        binding.tvDescriptionCharacterDetail.text = it.superHero.description

                        binding.ivCharacterDetail.loadUrl(it.superHero.thumbnail!!.path + "." + it.superHero.thumbnail!!.extension)

                    } else {
                        it.errorMessage?.let { error ->
                            requireContext().toast(error)
                        }
                    }
                }
            }
        }

    }

    override fun onStop() {
        super.onStop()
        job.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}