package com.whatsmessage

import android.app.Application
import com.whatsmessage.framework.datasource.ILocalContacts
import com.whatsmessage.framework.datasource.ILocalMessage
import com.whatsmessage.data.repository.ContactRepository
import com.whatsmessage.data.repository.MessageRepository
import com.whatsmessage.framework.providers.PhoneContentProvider
import com.whatsmessage.framework.database.MessageDataSource
import com.whatsmessage.framework.services.scheduler.SchedulerService
import com.whatsmessage.ui.common.IActivityView
import com.whatsmessage.ui.component.PermissionsRequest
import com.whatsmessage.ui.contact.ContactPresenter
import com.whatsmessage.ui.main.MainPresenter
import com.whatsmessage.ui.programming.ProgrammingPresenter
import com.whatsmessage.usecases.GetContacts
import com.whatsmessage.usecases.GetMessages
import com.whatsmessage.usecases.SaveMessage
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun Application.initDependencyInjection() {
    startKoin {
        //helps to see errors
        androidLogger()
        androidContext(this@initDependencyInjection)
        modules(listOf(dataModule, useCasesModule, appModule))
    }
}

val appModule = module {
    //app
    single  { (iActivityView: IActivityView) -> ProgrammingPresenter(get(), iActivityView, get()) }
    factory { ContactPresenter(get()) }
    factory { MainPresenter(get()) }
    factory { (iActivityView: IActivityView) -> PermissionsRequest(iActivityView) }

    //framework
    factory<ILocalContacts> { PhoneContentProvider(androidContext()) }
    factory<ILocalMessage> { MessageDataSource(App.appDb.messageDao(), SchedulerService()) }
}

val useCasesModule = module {
    single { SaveMessage(get()) }
    single { GetContacts(get()) }
    single { GetMessages(get()) }
}

val dataModule = module {
    single { ContactRepository(get()) }
    single { MessageRepository(get()) }
}