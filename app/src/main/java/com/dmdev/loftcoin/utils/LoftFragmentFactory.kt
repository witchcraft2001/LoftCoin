package com.dmdev.loftcoin.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject
import javax.inject.Provider

class LoftFragmentFactory @Inject constructor(providers: @JvmSuppressWildcards Map<Class<out Fragment>, Provider<Fragment>>): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return super.instantiate(classLoader, className)
    }
}