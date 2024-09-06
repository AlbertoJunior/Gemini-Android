package albertojunior.setor0.app.utils.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T1, T2, R> combine(
    one: LiveData<T1>,
    two: LiveData<T2>,
    merge: (a: T1?, b: T2?) -> R
): LiveData<R> {
    return MediatorLiveData<R>().apply {
        addSource(one) { value = merge.invoke(it, two.value) }
        addSource(two) { value = merge.invoke(one.value, it) }
    }
}

fun <T1, T2, T3, R> combine(
    one: LiveData<T1>,
    two: LiveData<T2>,
    three: LiveData<T3>,
    merge: (a: T1?, b: T2?, c: T3?) -> R
): LiveData<R> {
    return MediatorLiveData<R>().apply {
        addSource(one) { value = merge.invoke(it, two.value, three.value) }
        addSource(two) { value = merge.invoke(one.value, it, three.value) }
        addSource(three) { value = merge.invoke(one.value, two.value, it) }
    }
}