package com.cursoandroid.arch.di

import com.cursoandroid.arch.adapter.NoteAdapter
import com.cursoandroid.arch.db.NoteDatabase
import com.cursoandroid.arch.repository.NoteRepository
import com.cursoandroid.arch.viewmodel.NoteViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.dsl.module

@InternalCoroutinesApi
val dbModule = module {
    single {
        NoteDatabase.getInstance(
            context = get()
        )
    }
    factory { get<NoteDatabase>().noteDao }
}

@InternalCoroutinesApi
val repositoryModule = module {
    single {
        NoteRepository(get())
    }
}
val uiModule = module {
    factory { NoteAdapter() }
    viewModel {
        NoteViewModel(get())
    }
}


