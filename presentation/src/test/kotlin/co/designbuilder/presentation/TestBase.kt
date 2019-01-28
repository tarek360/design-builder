package co.designbuilder.presentation

import android.app.Activity
import android.content.Intent
import android.os.Build
import org.junit.After
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext
import org.koin.test.KoinTest
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@Config(application = TestRobolectricApplication::class, sdk = [(Build.VERSION_CODES.LOLLIPOP)])
@RunWith(RobolectricTestRunner::class)
abstract class RobolectricBase

abstract class ActivityRobolectricBase<A : Activity> : RobolectricBase() {
    private var a: A? = null
    private var controller: ActivityController<A>? = null

    val activity: A by lazy {
        a!!
    }

    open fun startActivity(activityClass: Class<A>, intent: Intent) {
        controller = Robolectric.buildActivity(activityClass, intent).create()
                .start()
                .postCreate(null)
                .resume()
                .visible()
        a = controller?.get()
    }

    @After
    fun tearDownActivityRobolectricBase() {
        // Destroy activity after every test
        controller
                ?.pause()
                ?.stop()
                ?.destroy()
    }
}

abstract class KoinRobolectricBase<A : Activity> : ActivityRobolectricBase<A>(), KoinTest {
    @After
    fun tearDownKoinRobolectricBase() {
        StandAloneContext.stopKoin()
    }
}

