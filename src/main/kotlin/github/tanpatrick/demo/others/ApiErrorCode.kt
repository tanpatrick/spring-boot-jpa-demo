package github.tanpatrick.demo.others

sealed class ApiErrorCode {

    object InvalidParameterType: ApiResponseCode {
        override val messageCode: String
            get() = "invalid_parameter_type"
    }

    object PathNotFound : ApiResponseCode {
        override val messageCode: String
            get() = "path_not_found"
    }

    object RecordNotFound : ApiResponseCode {
        override val messageCode: String
            get() = "record_not_found"
    }

    object InternalServer : ApiResponseCode {
        override val messageCode: String
            get() = "internal_server_error"
    }
}