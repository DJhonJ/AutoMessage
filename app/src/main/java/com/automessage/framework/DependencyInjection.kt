package com.automessage.framework

import android.app.AlarmManager
import android.app.Application
import android.content.Context
import com.automessage.data.datasource.ILocalContacts
import com.automessage.data.repository.ContactRepository
import com.automessage.framework.data.NotificationApp
import com.automessage.framework.data.PhoneContentProvider
import com.automessage.framework.ui.common.IShowMessage
import com.automessage.framework.ui.contact.ContactPresenter
import com.automessage.framework.ui.main.MainActivity
import com.automessage.framework.ui.programming.IProgrammingView
import com.automessage.framework.ui.programming.ProgrammingActivity
import com.automessage.framework.ui.programming.ProgrammingPresenter
import com.automessage.usecases.GetContacts
import com.automessage.usecases.ProgramarEnvio
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun Application.initDependencyInjection() {
    startKoin {
        //ayuda a ver posibles errores
        androidLogger()
        androidContext(this@initDependencyInjection)
        modules(listOf(dataModule, useCasesModule, appModule))
    }
}

val appModule = module {
    single { (view:IProgrammingView) -> ProgrammingPresenter(view, get()) }
    single { NotificationApp(androidContext().applicationContext) }

    single { ContactPresenter(get()) }
    factory<ILocalContacts> { PhoneContentProvider(androidContext().applicationContext) }
}

val useCasesModule = module {
    single { ProgramarEnvio(androidContext().applicationContext,
        androidContext().applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager) }

    single { GetContacts(get()) }
}

val dataModule = module {
    single { ContactRepository(get()) }
}