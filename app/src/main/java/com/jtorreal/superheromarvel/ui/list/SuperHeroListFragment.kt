package com.jtorreal.superheromarvel.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jtorreal.superheromarvel.R
import com.jtorreal.superheromarvel.databinding.FragmentSuperheroListBinding
import com.jtorreal.superheromarvel.domain.model.SuperHeroDomain
import com.jtorreal.superheromarvel.ui.UiResult
import com.jtorreal.superheromarvel.ui.common.showOrHidden
import com.jtorreal.superheromarvel.ui.common.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class SuperHeroListFragment : Fragment(), CoroutineScope {

    companion object {
        const val ID_CHARACTER_SELECTED = "SELECTED_CHARACTER"
    }

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val superHeroListViewModel by viewModels<SuperHeroListViewModel>()

    private var _binding: FragmentSuperheroListBinding? = null

    private lateinit var adapterSuperHeroAdapterList: SuperHeroAdapterList

    var superHeroList = ArrayList<SuperHeroDomain>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSuperheroListBinding.inflate(inflater, container, false)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterSuperHeroAdapterList = SuperHeroAdapterList(superHeroList) {

            val bundle = Bundle()
            bundle.putString(ID_CHARACTER_SELECTED, it.id.toString())
            binding.root.findNavController()
                .navigate(R.id.action_characterListFragment_to_detailFragment, bundle)

        }

        binding.rvMain.adapter = adapterSuperHeroAdapterList

        binding.rvMain.layoutManager = LinearLayoutManager(activity)

        lifecycleScope.launchWhenStarted {
            superHeroListViewModel.progressVisible.collect {
                binding.pbMainScreen.showOrHidden(it)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()

        job = SupervisorJob()


        launch {

            superHeroListViewModel.result.collect { it ->

                if (it.result != UiResult.LOADING) {

                    if (it.result == UiResult.OK && !it.list.isNullOrEmpty()) {

                        superHeroList.clear()
                        superHeroList.addAll(it.list)
                        adapterSuperHeroAdapterList.notifyDataSetChanged()

                    } else {
                        it.errorMessage.let {
                            requireActivity().toast(it ?: "Ha habido un error")
                        }
                    }
                }
            }
        }


    }

    override fun onStop() {
        job.cancel()
        super.onStop()
    }
}