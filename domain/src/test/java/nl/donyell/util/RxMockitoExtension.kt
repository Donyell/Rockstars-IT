package nl.donyell.util

import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.mockito.junit.jupiter.MockitoExtension

/**
 * Custom test extension that remaps Rx schedulers to trampoline, for testing purposes.
 */
class RxMockitoExtension : MockitoExtension() {

    init {
        RxJavaPlugins.reset()
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
    }
}
