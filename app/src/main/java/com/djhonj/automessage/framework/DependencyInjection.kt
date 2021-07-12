package com.djhonj.automessage.framework

import android.app.AlarmManager
import android.app.Application
import android.content.Context
import com.djhonj.automessage.framework.ui.common.IShowMessage
import com.djhonj.automessage.framework.ui.main.MainActivity
import com.djhonj.automessage.framework.ui.programming.ProgrammingActivity
import com.djhonj.automessage.framework.ui.programming.ProgrammingPresenter
import com.djhonj.automessage.usecases.ProgramarEnvio
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun Application.initDependencyInjection() {
    startKoin {
        //ayuda a ver posibles errores
        androidLogger()
        androidContext(this@initDependencyInjection)
        modules(listOf(appModule, useCasesModule))
    }
}

val appModule = module {
    single { (view:IShowMessage) -> ProgrammingPresenter(view, get()) }
}

val useCasesModule = module {
    single { ProgramarEnvio(androidContext().applicationContext,
        androidContext().applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager) }
}