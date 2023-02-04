package com.whatsmessage.framework

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.whatsmessage.App
import com.whatsmessage.data.datasource.ILocalContacts
import com.whatsmessage.data.datasource.ILocalMessage
import com.whatsmessage.data.repository.ContactRepository
import com.whatsmessage.data.repository.MessageRepository
import com.whatsmessage.framework.database.AppDatabase
import com.whatsmessage.framework.database.MessageDataSource
import com.whatsmessage.ui.common.IViewActivity
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
        //ayuda a ver posibles errores
        androidLogger()
        androidContext(this@initDependencyInjection)
        modules(listOf(dataModule, useCasesModule, appModule))
    }
}

val appModule = module {
    //app
    single { (iva: IViewActivity) -> ProgrammingPresenter(get(), iva, get()) }
    //single { NotificationApp(androidContext()) }
    factory { ContactPresenter(get()) }
    factory { MainPresenter(get()) }

    //framework
    factory<ILocalContacts> { PhoneContentProvider(androidContext()) }
    factory<ILocalMessage> { MessageDataSource(get(), App.appDb.messageDao()) }
}

val useCasesModule = module {
    //single { SaveDispatch(androidContext(), androidContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager) }
    single { SaveMessage(get()) }
    single { GetContacts(get()) }
    single { GetMessages(get()) }
}

val dataModule = module {
    single { ContactRepository(get()) }
    single { MessageRepository(get()) }
}