package almerti.egline.data.use_case

class ValidatePassword {
    fun execute(password: String, isRegister: Boolean): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password is blank",
            )
        }

        if (password.length < 8 && isRegister) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password length must be at least 8",
            )
        }

        val containsLettersAndDigits = password.any {it.isDigit()}
            && password.any {it.isLetter()}

        if (!containsLettersAndDigits && isRegister) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password must consist of numbers and letters",
            )
        }

        return ValidationResult(
            successful = true,
        )
    }
}
