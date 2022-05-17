package com.jtorreal.superheromarvel.ui.list

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jtorreal.superheromarvel.MainActivity
import com.jtorreal.superheromarvel.R
import com.jtorreal.superheromarvel.databinding.FragmentSuperheroListBinding
import com.jtorreal.superheromarvel.domain.model.SuperHeroDomain
import com.jtorreal.superheromarvel.ui.SharedViewModel
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

    private val sharedViewModel: SharedViewModel by activityViewModels()

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

            //Save ID value in viewmodel state
            sharedViewModel.saveId(it.id)

            binding.root.findNavController()
                .navigate(R.id.action_characterListFragment_to_detailFragment)

        }

        binding.rvMain.adapter = adapterSuperHeroAdapterList

        binding.rvMain.layoutManager = LinearLayoutManager(activity)



        lifecycleScope.launchWhenStarted {
            superHeroListViewModel.progressVisible.collect {
                binding.pbMainScreen.showOrHidden(it)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        if (menu.hasVisibleItems()) {

            val menuItem = menu.findItem(R.id.action_search)
            val search = menuItem.actionView as SearchView

            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(text: String?): Boolean {
                    adapterSuperHeroAdapterList.applyFilter().filter(text)
                    return false;
                }

            })
        }

        return super.onCreateOptionsMenu(menu, inflater)


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

                    val toolbar =
                        (activity as MainActivity?)!!.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_main)
                    toolbar?.showOrHidden(true)

                    setHasOptionsMenu(true)
                }
            }
        }


    }

    override fun onStop() {
        job.cancel()
        super.onStop()
    }
}