package com.automessage.framework

import android.app.Application
import com.automessage.data.datasource.ILocalContacts
import com.automessage.data.repository.ContactRepository
import com.automessage.ui.common.IViewActivity
import com.automessage.ui.contact.ContactPresenter
import com.automessage.ui.programming.ProgrammingPresenter
import com.automessage.usecases.GetContacts
import com.automessage.usecases.SaveMessage
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
    //single { (view:IProgrammingView) -> ProgrammingPresenter(view, get()) }
    //factory<IViewActivity> { ProgrammingActivity() }
    single { (iva: IViewActivity) -> ProgrammingPresenter(get(), iva, get()) }
    single { NotificationApp(androidContext()) }
    single { ContactPresenter(get()) }
    factory<ILocalContacts> { PhoneContentProvider(androidContext()) }
}

val useCasesModule = module {
    //single { SaveDispatch(androidContext(), androidContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager) }
    single { SaveMessage() }
    single { GetContacts(get()) }
}

val dataModule = module {
    single { ContactRepository(get()) }
}