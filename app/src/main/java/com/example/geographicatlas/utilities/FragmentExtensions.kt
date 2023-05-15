package com.example.geographicatlas

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.replaceFragment(
    fragment: Fragment,
    tag: String = fragment::class.java.name
) {
    supportFragmentManager
        .beginTransaction()                 // get an instance of FragmentTransaction from the FragmentManager
        .replace(
            R.id.container,
            fragment,
            tag
        )                                   // replace an existing fragment in a container with an instance of a new fragment class
        .commit()                           // call signals to the FragmentManager that all operations have been added to the transaction
}

fun Fragment.replaceFragment(
    fragment: Fragment,
    @IdRes container: Int = R.id.container,  // the ID of the container ViewGroup where the new Fragment will be placed
    addToBackStack: Boolean = true,          // fragment is only STOPPED and is later RESUMED when the user navigates back
    tag: String = fragment::class.java.name
) {
    requireActivity()
        .supportFragmentManager
        .beginTransaction()
        .replace(container, fragment, tag)
        .apply { if (addToBackStack) addToBackStack(tag) }
        .commit()
}