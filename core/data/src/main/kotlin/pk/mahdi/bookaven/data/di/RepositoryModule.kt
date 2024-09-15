// di/RepositoryModule.kt

package pk.mahdi.bookaven.data.di


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pk.mahdi.bookaven.data.repository.BookRepository
import pk.mahdi.bookaven.data.repository.BookRepositoryImpl
import pk.mahdi.bookaven.data.repository.GoogleBooksRepository
import pk.mahdi.bookaven.data.repository.GoogleBooksRepositoryImpl
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
