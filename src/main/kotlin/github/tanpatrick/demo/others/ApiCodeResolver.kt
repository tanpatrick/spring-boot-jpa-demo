package github.tanpatrick.demo.others

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Component

@Component
class ApiCodeResolver {

    @Autowired
    fun setMessageSource(source: MessageSource) {
        messageSource = source
    }

    companion object {
        private lateinit var messageSource: MessageSource

        fun getMessage(code: ApiResponseCode) : String {
            val defaultMessage = "Default message"
            return messageSource.getMessage(code.messageCode, null, defaultMessage, LocaleContextHolder.getLocale()) ?: "Not so default message"
        }
    }
}

