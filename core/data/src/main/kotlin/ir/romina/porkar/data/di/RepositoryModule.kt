// di/RepositoryModule.kt

package ir.romina.porkar.data.di


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.romina.porkar.data.repository.BookRepository
import ir.romina.porkar.data.repository.BookRepositoryImpl
import ir.romina.porkar.data.repository.GoogleBooksRepository
import ir.romina.porkar.data.repository.GoogleBooksRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGoogleBooksRepository(
        googleBooksRepositoryImpl: GoogleBooksRepositoryImpl
    ): GoogleBooksRepository

    @Binds
    @Singleton
    abstract fun bindBookRepository(
        bookRepositoryImpl: BookRepositoryImpl
    ): BookRepository


}
