class InAppReviewsUseCase @Inject constructor(
    private val repository: ConfigurationRepository,
    private val clock: Clock
) {
    
    suspend fun inAppReviewShown() {
        repository.updateInAppReviewTimeStamp(clock.getCurrentTime())
    }


     fun shouldShowInAppReview(): Flow<Boolean> {
        return repository.getInAppReviewTimeStamp().map { lastTimeStamp ->
            val timePassed = clock.getCurrentTime() - lastTimeStamp

            Math.abs(timePassed) > TimeUnit.DAYS.toMillis(30)
        }
    }
}
