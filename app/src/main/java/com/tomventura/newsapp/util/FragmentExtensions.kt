package com.tomventura.newsapp.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.tomventura.newsapp.R

fun FragmentManager.changeFragment(fragment: Fragment, cleanStack: Boolean = false) {
    val ft = beginTransaction()
    if (cleanStack)
        clearBackStack()
    ft.setCustomAnimations(
        R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit
    )
    ft.replace(R.id.container, fragment)
    ft.addToBackStack(null)
    ft.commit()
}

private fun FragmentManager.clearBackStack() {
    if (backStackEntryCount > 0) {
        val first = getBackStackEntryAt(0)
        popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}