package albertojunior.setor0.app.establishment.di

import albertojunior.setor0.app.establishment.use_case.EstablishmentGeneratorUseCase
import albertojunior.setor0.app.establishment.use_case.SelectBadTraitsUseCase
import albertojunior.setor0.app.establishment.use_case.SelectGoodTraitsUseCase
import albertojunior.setor0.app.establishment.use_case.SelectTypeUseCase
import albertojunior.setor0.app.establishment.use_case.TreasureCalculateUseCase
import albertojunior.setor0.app.establishment.use_case.UpdateCharacteristicsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EstablishmentModule {
    @Provides
    @Singleton
    internal fun provideEstablishmentGeneratorUseCase() = EstablishmentGeneratorUseCase(
        SelectTypeUseCase(),
        TreasureCalculateUseCase(),
        SelectGoodTraitsUseCase(),
        SelectBadTraitsUseCase(),
        UpdateCharacteristicsUseCase()
    )
}