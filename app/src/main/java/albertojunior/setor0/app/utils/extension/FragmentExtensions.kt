package albertojunior.setor0.app.utils.extension

import albertojunior.setor0.app.interfaces.NavBarController
import androidx.fragment.app.Fragment

fun Fragment.hideNavBar() {
    val navController = requireActivity() as? NavBarController ?: return
    navController.hideBar()
}

fun Fragment.showNavBar() {
    val navController = requireActivity() as? NavBarController ?: return
    navController.showBar()
}