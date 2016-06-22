package pro.averin.anton.clean.android.cookbook.ui.common.rx

import pro.averin.anton.clean.android.cookbook.data.common.rx.bus.RxBus
import pro.averin.anton.clean.android.cookbook.di.ActivityScope
import javax.inject.Inject

@ActivityScope
class ActivityBus @Inject constructor() : RxBus()