package com.example.core.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.core.di.DaggerApplication
import com.example.core.di.provider.CoreProvider
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseFragment : Fragment() {

    val coreComponent: CoreProvider by lazy {
        (requireActivity().applicationContext as DaggerApplication).getApplicationProvider()
    }

    private val MY_PERMISSIONS_REQUEST_LOCATION = 99

    protected abstract val layoutRes : Int

    private val renderDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutRes, container, false)

    override fun onDestroy() {
        renderDisposable.dispose()
        super.onDestroy()
    }

    fun Disposable.connectToRenderDisposable() {
        renderDisposable.add(this)
    }

    fun setTitle(title: String)  {
        getActionBar()?.title = title
    }

    protected fun getActionBar(): ActionBar? = (activity as AppCompatActivity).supportActionBar

    abstract fun injectDependencies()

    override fun onAttach(context: Context) {
        injectDependencies()
        super.onAttach(context)
    }

    protected fun showError(message: String) {
        Snackbar.make(this.view!!, message, Snackbar.LENGTH_SHORT).show()
    }

    protected fun checkLocationPermission(): Boolean {
        return if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_PERMISSIONS_REQUEST_LOCATION
            )

            false
        } else {
            true
        }
    }
}