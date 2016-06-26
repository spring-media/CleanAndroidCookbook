package pro.averin.anton.clean.android.cookbook.ui.googlemaps.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pro.averin.anton.clean.android.cookbook.databinding.FragmentGmapsBinding
import pro.averin.anton.clean.android.cookbook.di.ActivityComponent
import pro.averin.anton.clean.android.cookbook.ui.common.resolution.Resolution
import pro.averin.anton.clean.android.cookbook.ui.common.resolution.UIResolution
import pro.averin.anton.clean.android.cookbook.ui.common.view.BaseFragment
import pro.averin.anton.clean.android.cookbook.ui.googlemaps.presenter.GoogleMapsPresenter
import javax.inject.Inject


class GoogleMapsFragment : BaseFragment(), GoogleMapsScreenContract {

    @Inject lateinit var googleMapViewExtension: GoogleMapViewExtension
    @Inject lateinit var presenter: GoogleMapsPresenter
    @Inject lateinit var uiResolution: UIResolution

    companion object Builder {
        @JvmStatic fun create(): GoogleMapsFragment {
            val fragment = GoogleMapsFragment()
            return fragment
        }
    }

    lateinit var binding: FragmentGmapsBinding

    override fun doInjections(activityComponent: ActivityComponent) {
        activityComponent.injectTo(this)

        //init presenter
        presenter.view = this
        presenter.googleMapViewExtension = googleMapViewExtension

        //init google map view extension
        googleMapViewExtension.setViews(binding.map)
        googleMapViewExtension.eventsDelegate = presenter

        //manage lifecycle
        setMainLifecycleDelegates(presenter, googleMapViewExtension)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGmapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getResolution(): Resolution? {
        return uiResolution
    }
}