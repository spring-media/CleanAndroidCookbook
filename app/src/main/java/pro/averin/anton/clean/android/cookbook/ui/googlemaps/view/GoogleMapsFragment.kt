package pro.averin.anton.clean.android.cookbook.ui.googlemaps.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pro.averin.anton.clean.android.cookbook.databinding.FragmentGmapsBinding
import pro.averin.anton.clean.android.cookbook.di.ActivityComponent
import pro.averin.anton.clean.android.cookbook.ui.common.view.BaseFragment


class GoogleMapsFragment : BaseFragment(), GoogleMapsScreenContract {

    companion object Builder {
        @JvmStatic fun create(): GoogleMapsFragment {
            val fragment = GoogleMapsFragment()
            return fragment
        }
    }

    lateinit var binding: FragmentGmapsBinding

    override fun doInjections(activityComponent: ActivityComponent) {
        activityComponent.injectTo(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGmapsBinding.inflate(inflater, container, false)
        return binding.root
    }
}