package almerti.egline.data.di

import almerti.egline.data.use_case.ValidateDisplayName
import almerti.egline.data.use_case.ValidateEmail
import almerti.egline.data.use_case.ValidatePassword
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideValidateEmail(): ValidateEmail {
        return ValidateEmail()
    }

    @Provides
    fun provideValidatePassword(): ValidatePassword {
        return ValidatePassword()
    }

    @Provides
    fun provideValidateDisplayName(): ValidateDisplayName {
        return ValidateDisplayName()
    }

}
