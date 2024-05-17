package almerti.egline.data.use_case

class ValidateDisplayName {
    fun execute(displayName: String): ValidationResult {
        if (displayName.isBlank() || displayName.length < 2) {
            return ValidationResult(
                successful = false,
                errorMessage = "Username length must be at least 3",
            )
        }

        return ValidationResult(
            successful = true,
        )
    }
}
