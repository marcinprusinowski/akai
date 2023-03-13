import java.time.Instant
import java.time.format.DateTimeParseException

data class MappingException(
    val errors: List<*>,
) : RuntimeException()

enum class MappingErrorType {
    NOT_FOUND,
    INVALID_TYPE
}

data class FieldNotFoundError(
    val name: String,
    val type: MappingErrorType = MappingErrorType.NOT_FOUND,
)

data class InvalidFieldValueError(
    val field: String,
    val value: String,
    val type: MappingErrorType = MappingErrorType.INVALID_TYPE,
)

class MappingType(
    private val inputMap: Map<*, *>,
    private val errors: MutableList<Any>,
) {

    private fun isFieldInvalid(
        required: Boolean,
        name: String,
    ): Boolean {

        return if (required && inputMap.containsKey(name).not()) {
            errors.add(
                FieldNotFoundError(
                    name = name
                )
            )
            return true
        } else required.not() and inputMap.containsKey(name).not()

    }

    fun boolean(
        name: String,
        required: Boolean = true,
    ): Boolean? {

        if (isFieldInvalid(required, name)) return null

        return try {
            inputMap[name].toString().toBooleanStrict()
        } catch (e: IllegalArgumentException) {
            errors.add(
                InvalidFieldValueError(
                    field = name,
                    value = inputMap[name].toString()
                )
            )
            null
        }
    }

    fun string(
        name: String,
        required: Boolean = true,
    ): String? {
        if (isFieldInvalid(required, name)) return ""


        return try {
            inputMap[name].toString()
        } catch (e: IllegalArgumentException) {
            errors.add(
                InvalidFieldValueError(
                    field = name,
                    value = inputMap[name].toString()
                )
            )
            ""
        }
    }

    fun int(
        name: String,
        required: Boolean = true,
    ): Int? {
        if (isFieldInvalid(required, name)) return -1


        return try {
            inputMap[name].toString().toInt()
        } catch (e: NumberFormatException) {
            errors.add(
                InvalidFieldValueError(
                    field = name,
                    value = inputMap[name].toString()
                )
            )
            -1
        }
    }

    fun double(
        name: String,
        required: Boolean = true,
    ): Double? {

        if (isFieldInvalid(required, name)) return -1.0

        return try {
            inputMap[name].toString().toDouble()
        } catch (e: NumberFormatException) {
            errors.add(
                InvalidFieldValueError(
                    field = name,
                    value = inputMap[name].toString()
                )
            )
            -1.0
        }
    }

    fun float(
        name: String,
        required: Boolean = true,
    ): Float? {
        if (isFieldInvalid(required, name)) return -1f
        return try {
            inputMap[name].toString().toFloat()
        } catch (e: NumberFormatException) {
            errors.add(
                InvalidFieldValueError(
                    field = name,
                    value = inputMap[name].toString()
                )
            )
            -1f
        }
    }

    fun short(
        name: String,
        required: Boolean = true,
    ): Short? {
        if (isFieldInvalid(required, name)) return -1
        return try {
            inputMap[name].toString().toShort()
        } catch (e: NumberFormatException) {
            errors.add(
                InvalidFieldValueError(
                    field = name,
                    value = inputMap[name].toString()
                )
            )
            -1
        }
    }

    fun date(
        name: String,
        required: Boolean = true,
    ): Instant? {

        if (isFieldInvalid(required, name)) return null

        return try {
            val dateString = inputMap[name].toString()
            Instant.parse(dateString)
        } catch (e: DateTimeParseException) {
            errors.add(
                InvalidFieldValueError(
                    field = name,
                    value = inputMap[name].toString()
                )
            )
            null
        }
    }

    fun <T> obj(
        name: String,
        required: Boolean = true,
        func: MappingType.() -> T,
    ): T {

        val newMap = inputMap[name] as Map<*, *>

        val mappingType = MappingType(
            inputMap = newMap,
            errors = errors
        )

        return func.invoke(mappingType)

    }

}

fun <T> map(inputMap: Map<String, Any>, func: MappingType.() -> T): T {
    val errors = mutableListOf<Any>()

    val mappingType = MappingType(
        inputMap = inputMap,
        errors = errors
    )

    val result = func.invoke(mappingType)

    if (errors.isNotEmpty()) throw MappingException(errors)

    return result
}
