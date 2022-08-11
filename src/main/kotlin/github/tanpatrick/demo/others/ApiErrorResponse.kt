package github.tanpatrick.demo.others

sealed class ApiErrorResponse : ErrorResponseDTO {

    private constructor(code: ApiResponseCode) :
            super(code = code.messageCode, message = ApiCodeResolver.getMessage(code))

    object InvalidParameterType : ApiErrorResponse(ApiErrorCode.InvalidParameterType)

    object InternalServerError : ApiErrorResponse(ApiErrorCode.InternalServer)

    object PathNotFound : ApiErrorResponse(ApiErrorCode.PathNotFound)

    object RecordNotFound : ApiErrorResponse(ApiErrorCode.RecordNotFound)
}