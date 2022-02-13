package com.automessage.framework

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.automessage.App
import com.automessage.data.datasource.ILocalContacts
import com.automessage.data.datasource.ILocalMessage
import com.automessage.data.repository.ContactRepository
import com.automessage.data.repository.MessageRepository
import com.automessage.framework.database.AppDatabase
import com.automessage.framework.database.MessageDataSource
import com.automessage.ui.common.IViewActivity
import com.automessage.ui.contact.ContactPresenter
import com.automessage.ui.main.MainPresenter
import com.automessage.ui.programming.ProgrammingPresenter
import com.automessage.usecases.GetContacts
import com.automessage.usecases.GetMessages
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